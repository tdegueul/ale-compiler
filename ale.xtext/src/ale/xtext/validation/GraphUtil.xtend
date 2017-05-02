package ale.xtext.validation

import ale.xtext.validation.Graph.GraphNode
import ale.utils.AleEcoreUtil
import ale.xtext.ale.AleClass
import ale.xtext.ale.Root
import java.util.Collection
import java.util.HashSet
import java.util.List
import java.util.Set
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil
import ale.xtext.ale.Method

class GraphUtil {

	private ResourceSet resSet
	private AleEcoreUtil aleEcoreUtil;

	new(ResourceSet resSet) {
		this.resSet = resSet;
		aleEcoreUtil = new AleEcoreUtil
	}

	public def Graph<EClass> buildGraph(EPackage ePackage) {
		this.buildGraph(newArrayList(ePackage))
	}

	public def Graph<EClass> buildGraph(List<EPackage> ePackages) {
		val graph1 = new Graph<EClass>()
		val visitedPackages = newHashSet()
		ePackages.forEach[it.visitPackages(visitedPackages, graph1)]
		graph1
	}

	private def void visitPackages(EPackage ePackage, HashSet<EPackage> visitedpackage, Graph<EClass> graph1) {
		if(ePackage != null) visitedpackage.add(ePackage)
		val allEClasses = ePackage.allEClasses
		allEClasses.forEach[e|addParents(graph1, e)]
		allEClasses.forEach[e|e.EReferences.directlyRelatedTypes.forEach[f|addParents(graph1, f)]]

		val notYetVisited = graph1.nodes.filter[it?.elem?.name != null].sortBy[it.elem.name].map[it.elem.EPackage].toSet.filter [ 
			!visitedpackage.contains(it)
		]
		notYetVisited.forEach[it.visitPackages(visitedpackage, graph1)]
	}

	private def List<EClass> allEClasses(EPackage ePackage) {
		val allContent = ePackage.eAllContents.toList
		allContent.filter[it instanceof EClass].map[EcoreUtil.resolve(it, resSet)].map[it as EClass].toList
	}

	private def void addParents(Graph<EClass> graph1, EClass e) {
		val node = graph1.addNode(e)
		e.ESuperTypes.forEach [ f |
			val node2 = graph1.addNode(f)
			graph1.addEdge(node, node2)
			addParents(graph1, f)
		]
	}

	private def List<EClass> getDirectlyRelatedTypes(EList<EReference> list) {
		list.map[f|f.EType].filter[z|z instanceof EClass].map[q|q as EClass].filter [ x |
			x?.EPackage?.name != "ecore"
		].toList
	}

	private def Set<EPackage> getAllDirectPackagesByReference(Iterable<GraphNode> nodes, List<EPackage> ePackages) {
		nodes.map[e|e.elem.EReferences].map[e|e.directlyRelatedTypes].flatten.map[e|e.EPackage]
		//.filter [ e | !ePackages.contains(e)]
		.toSet
	}

	private def Set<EPackage> getAllDirectPackagesByReference(Iterable<GraphNode> nodes, EPackage ePackage) {
		nodes.getAllDirectPackagesByReference(newArrayList(ePackage))
	}

	public def List<EPackage> allDirectPackages(Iterable<GraphNode> nodes, EPackage ePackage) {
		this.allDirectPackages(nodes, newArrayList(ePackage))
	}

	public def List<EPackage> allDirectPackages(Iterable<GraphNode> nodes, List<EPackage> ePackages) {
		val allDirectPackagesByInheritance = nodes.getDirectPackageByInheritance(ePackages)
		val allDirectPackageByReference = nodes.getAllDirectPackagesByReference(ePackages)
		allDirectPackagesByInheritance.addAll(allDirectPackageByReference)
		allDirectPackagesByInheritance.addAll(ePackages)
		allDirectPackagesByInheritance.toSet.toList.sortBy[name]
	}

	private def Set<EPackage> getDirectPackageByInheritance(Iterable<GraphNode> nodes, List<EPackage> ePackages) {
		nodes.map[e|e.outgoing].flatten.map[e|e.elem.EPackage]
		//.filter[!ePackages.contains(it)]
		.toSet
	}

	private def Set<EPackage> getDirectPackageByInheritance(Iterable<GraphNode> nodes, EPackage ePackage) {
		nodes.getDirectPackageByInheritance(newArrayList(ePackage))
	}

	public def List<EClass> getListAllClasses(List<EPackage> ePackages) {
		val graph = ePackages.buildGraph
		graph.nodes.map[elem].toList
	}

	private def List<EClass> getListAllClasses(EPackage ePackage) {
		this.getListAllClasses(newArrayList(ePackage))
	}

	public def allClassesRec(EPackage e) {
		val graph = e.buildGraph
		graph.nodes.map[elem].toList.sortBy[name]
	}
	
	public def allClassesRec(Root root) {
		val List<EPackage> ePackages = root.importsEcore.map[aleEcoreUtil.loadEPackageByEcorePath(it.ref, resSet)].toList
		val graph = ePackages.buildGraph
		graph.nodes.map[elem].toList.sortBy[name]
	}

	public def Collection<EClass> ancestors(EClass clazz) {
		val ret = newHashSet()
		if (!clazz.ESuperTypes.empty) {
			clazz.ESuperTypes.forEach [ st |
				ret.add(st)
				ret.addAll(st.ancestors)
			]
		}

		ret
	}
	
	public def String operationInterfacePath(EClass clazz, String aleName) {
		'''«aleName».revisitor.operation.«aleName.toFirstUpper»«clazz.name.toFirstUpper»Operation'''
	}
	
	def EClass getEClass(AleClass aleClass, List<EPackage> ePackages) {
		val classes = ePackages.listAllClasses
		return classes.filter[c | c.name == aleClass.name].head;
	}
	
	def findAleClass(EClass clazz, Root root, List<EPackage> epackages) {
		
		

	}
	
	
	public def List<Method> methodsRec(AleClass aleClass, boolean includeSelf) {
		
		val List<Method> ret = if(includeSelf) newArrayList(aleClass.methods) else newArrayList()
		for(AleClass parent: aleClass.superClass) {
			// getAll parents methods
			val tmp = parent.methodsRec(true)
			for(Method tmpM: tmp) {
				
				// only add missing ones
				if(!ret.exists[it.name == tmpM.name && it.params.size == tmpM.params.size]) {
					ret.add(tmpM)
				}		
			}
		}
		
		// find syntactic hierarchy for implicit import of parents
//		val EClass eClass = aleClass.getEClass(ePackages)
		
//		for(EClass parent: eClass.ESuperTypes) {
//			parent.
//		}
		ret
	}
}
