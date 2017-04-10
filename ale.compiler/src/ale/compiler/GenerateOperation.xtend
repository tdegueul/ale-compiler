package ale.compiler

import fr.inria.diverse.objectalgebragenerator.GenerateAlgebra
import java.io.FileWriter
import java.io.IOException
import org.eclipse.core.resources.IProject
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import ale.xtext.ale.AleClass
import java.util.List

class GenerateOperation {

	def generate(EClass clazz, IProject project, String dslName, AleClass behavior, EPackage ePackage, List<EPackage> dependencies) {

		val fileContent = new GenerateAlgebra().generateOperation(clazz,  dslName, behavior, ePackage, dependencies)

		val directoryAlgebra = project.getLocation().append("src").append(dslName).append("algebra").append(
			"operation").append(clazz.EPackage.name);
		directoryAlgebra.toFile().mkdirs();
		
		val fileName = dslName.toFirstUpper + clazz.name.toFirstUpper+"Operation";
		val fileJavaAlgebra = directoryAlgebra.append(fileName).addFileExtension("java");

		try {
			val FileWriter fileWriter = new FileWriter(fileJavaAlgebra.toFile());
			fileWriter.write(fileContent);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
