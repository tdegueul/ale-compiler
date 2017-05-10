package ale.compiler.generator

import ale.compiler.generator.util.DollarGeneratorUtil
import ale.compiler.generator.util.EcoreUtils
import ale.xtext.ale.Root
import java.util.List
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.ResourceSet

class GenerateRevisitorInterfaceXtend {
	extension GraphUtil graphUtil
	extension JavaPathUtil = new JavaPathUtil()
	extension EcoreUtils = new EcoreUtils()

	new(ResourceSet rs) {
		this.graphUtil = new GraphUtil(rs)
	}

	def String generate(String name, List<EPackage> ePackages, List<GenModel> genmodels, List<Root> parentRoots, Boolean generateMethods) {
		// 1 - gather all classes
		val graph = ePackages.buildGraph

		// 2 - gather all directly referenced packages
		val allMethods = graph.nodes.filter[ePackages.contains(elem.EPackage)].filter[!elem.abstract].sortBy[elem.name]
		val allDirectPackages = if(generateMethods) allMethods.allDirectPackages(ePackages).filter[!ePackages.contains(it)] else allMethods.allDirectPackages(ePackages) 
		val allClasses = ePackages.getListAllClasses
		val classPlusItsChildren =
			allClasses
			.map[currentParent |
				currentParent -> {
					val tmp = allClasses.filter[ac | currentParent.isSuperTypeOf(ac)].toList
					new DollarGeneratorUtil().sortForDollars(tmp)
					tmp
				}
			]

		val sep = if (allDirectPackages.empty) ' extends ' else ', \n\t\t'

		return '''
		package «name».revisitor;

		public interface «name.toPackageName»«
		»«FOR clazz : graph.nodes.sortBy[elem.name] BEFORE '<' SEPARATOR ', ' AFTER '>'»«clazz.elem.genericType(true)»«ENDFOR»«
		»«FOR ePp : allDirectPackages.sortBy[name] BEFORE '\n\textends ' SEPARATOR ',\n\t\t'»«
			»«ePp.name.revisitorInterfaceJavaPath»«
			»«FOR x : ePp.allClassesRec BEFORE '<' SEPARATOR ', ' AFTER '>'»«
				»«x.genericType(false)»«
			»«ENDFOR»«
		»«ENDFOR»«
		»«FOR ePp : parentRoots BEFORE sep SEPARATOR ',\n\t\t'»
			«ePp.name.revisitorInterfaceJavaPath»«FOR x : ePp.allClassesRec BEFORE '<' SEPARATOR ', ' AFTER '>'»«
				»«x.genericType(false)»«
			»«ENDFOR»«
		»«ENDFOR» {

			«IF generateMethods»
			// Concrete factory methods to be implemented in revisitor implementations
			«FOR clazzNode : allMethods.filter[!elem.abstract]»
				«clazzNode.elem.genericType(false)» «clazzNode.elem.name.toFirstLower»(final «clazzNode.elem.javaFullPath» «clazzNode.elem.name.toFirstLower»);
				«FOR parent: clazzNode.elem.ancestors»
					«parent.genericType(false)» «parent.name.toFirstLower»_«clazzNode.elem.name.toFirstLower»(final «clazzNode.elem.javaFullPath» «clazzNode.elem.name.toFirstLower»);
				«ENDFOR»
			«ENDFOR»
			«ENDIF»

			// Default dispatch methods
			«FOR dollarRoot : classPlusItsChildren»
			default «dollarRoot.key.genericType(false)» $(final «dollarRoot.key.javaFullPath» self) {
				«FOR subClass: dollarRoot.value.filter[it != dollarRoot.key].filter[!abstract]»
					«val genCls = subClass.getGenClass(genmodels)»
					«val genClsID = genCls.genPackage.qualifiedPackageInterfaceName + "." + genCls.classifierID»
					«IF subClass.ESuperTypes.size <= 1»
						if(self.eClass().getClassifierID() == «genClsID»
							&& self.eClass().getEPackage() == «subClass.EPackage.name».«subClass.EPackage.name.toFirstUpper»Package.eINSTANCE)
							return «subClass.name.toFirstLower»((«subClass.javaFullPath») self);
					«ELSE»
						if(self.eClass().getClassifierID() == «genClsID»
							&& self.eClass().getEPackage() == «subClass.EPackage.name».«subClass.EPackage.name.toFirstUpper»Package.eINSTANCE)
							return «dollarRoot.key.name.toFirstLower»_«subClass.name.toFirstLower»((«subClass.javaFullPath») self);
					«ENDIF»
				«ENDFOR»
				«IF dollarRoot.key.abstract»
					return null;
				«ELSE»
					return «dollarRoot.key.name.toFirstLower»(self);
				«ENDIF»	
			}

			«ENDFOR»
		}
		'''
	}

	def String generate(EPackage ePackage, GenModel gm) {
		return this.generate(ePackage.name, newArrayList(ePackage), newArrayList(gm), newArrayList(), true)
	}

	private def revisitorInterfaceJavaPath(String name)
		'''«name».revisitor.«name.toPackageName»'''

	private def String genericType(EClass clazz, boolean extend)
		'''«clazz.EPackage.name.replaceAll("\\.","").toFirstUpper»__«clazz.name»T«IF clazz.ESuperTypes.size == 1 && extend» extends «clazz.ESuperTypes.head.genericType(false)»«ENDIF»'''

	private def static toPackageName(String name) '''«name.toClassName»Revisitor'''

	private def static String toClassName(String name) {
		return name.split("\\.").map[toFirstUpper].join
	}
}
