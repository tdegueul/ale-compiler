package dsl.xtext.validation;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import ale.xtext.ale.AleClass;
import ale.xtext.ale.Root;
import dsl.xtext.dsl.DSL;
import dsl.xtext.dsl.DslPackage;

public class DslBehaviorValidator {

	private List<Root> roots;
	private DslValidator validator;
	private List<EPackage> syntaxes;
	private DSL dsl;

	DslBehaviorValidator(List<Root> roots, List<EPackage> syntaxes, DSL dsl, DslValidator validator) {
		this.roots = roots;
		this.dsl = dsl;
		this.syntaxes = syntaxes;
		this.validator = validator;
	}

	void validate() {
		this.oneClassPerOpenClass();
		this.oneClassPerSyntaxElem();
	}

	private void oneClassPerSyntaxElem() {
		Stream<EClass> strm = this.syntaxes.stream().flatMap (x -> {
			Iterator<EObject> itt = x.eAllContents();
			Iterable<EObject> it = () -> itt;
			Stream<EObject> res = StreamSupport.stream(it.spliterator(), false);
			return res.filter(y -> y instanceof EClass).map(y -> (EClass) y);
		});
		Map<String, Long> collect = strm.collect(Collectors.groupingBy(x -> x.getName(), Collectors.counting()));
		collect.entrySet().forEach(e -> {
			if(e.getValue() > 1) {
				this.validator.errorB("Cannot reference two classes with the same name", this.dsl, DslPackage.Literals.DSL__SYNTAXES);
			}
		});
		
	}

	private void oneClassPerOpenClass() {
		Stream<AleClass> strm = this.roots.stream().flatMap(r -> r.getClasses().stream());
		Map<String, Long> collect = strm.collect(Collectors.groupingBy(x -> x.getName(), Collectors.counting()));
		collect.entrySet().forEach(e -> {
			if (e.getValue() > 1) {
				this.validator.errorB("Cannot reference two classes with the same name", this.dsl,
						DslPackage.Literals.DSL__BEHAVIOURS);
			}
		});
	}

}
