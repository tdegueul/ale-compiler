package ale.xtext.validation;

import ale.xtext.AleType;
import ale.xtext.ale.DefMethod;
import ale.xtext.ale.NewClass;
import ale.xtext.ale.OpenClass;
import ale.xtext.ale.OverrideMethod;
import ale.xtext.ale.Root;
import com.google.inject.Inject;
import it.xsemantics.runtime.validation.XsemanticsValidatorErrorGenerator;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;

@SuppressWarnings("all")
public class AleTypeValidator extends AbstractDeclarativeValidator {
  @Inject
  protected XsemanticsValidatorErrorGenerator errorGenerator;
  
  @Inject
  protected AleType xsemanticsSystem;
  
  protected AleType getXsemanticsSystem() {
    return this.xsemanticsSystem;
  }
  
  @Check
  public void noCyclicOpenClassHierarchy(final OpenClass clazz) {
    errorGenerator.generateErrors(this,
    	getXsemanticsSystem().noCyclicOpenClassHierarchy(clazz),
    		clazz);
  }
  
  @Check
  public void defMethodDoesNotAlreadyExists(final DefMethod method) {
    errorGenerator.generateErrors(this,
    	getXsemanticsSystem().defMethodDoesNotAlreadyExists(method),
    		method);
  }
  
  @Check
  public void overrideMethodDoesMustExists(final OverrideMethod method) {
    errorGenerator.generateErrors(this,
    	getXsemanticsSystem().overrideMethodDoesMustExists(method),
    		method);
  }
  
  @Check
  public void noCyclicBehaviorImport(final Root root) {
    errorGenerator.generateErrors(this,
    	getXsemanticsSystem().noCyclicBehaviorImport(root),
    		root);
  }
  
  @Check
  public void noCyclicNewClassHierarchy(final NewClass clazz) {
    errorGenerator.generateErrors(this,
    	getXsemanticsSystem().noCyclicNewClassHierarchy(clazz),
    		clazz);
  }
  
  @Check
  public void checkClassNames(final Root root) {
    errorGenerator.generateErrors(this,
    	getXsemanticsSystem().checkClassNames(root),
    		root);
  }
  
  @Check
  public void checkFieldName(final ale.xtext.ale.Class clazz) {
    errorGenerator.generateErrors(this,
    	getXsemanticsSystem().checkFieldName(clazz),
    		clazz);
  }
}