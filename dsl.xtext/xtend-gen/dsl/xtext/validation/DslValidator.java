/**
 * generated by Xtext 2.10.0
 */
package dsl.xtext.validation;

import ale.xtext.AleStandaloneSetup;
import ale.xtext.ale.AlePackage;
import ale.xtext.ale.Root;
import com.google.common.base.Objects;
import com.google.inject.Injector;
import dsl.xtext.dsl.Behavior;
import dsl.xtext.dsl.DSL;
import dsl.xtext.dsl.DslPackage;
import dsl.xtext.dsl.Syntax;
import dsl.xtext.validation.DslBehaviorValidator;
import dsl.xtext.validation.DslTypeValidator;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

/**
 * This class contains custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
@SuppressWarnings("all")
public class DslValidator extends DslTypeValidator {
  private String BEHAVIOURS_URI_NOT_FOUND = "behaviours.uri.not.found";
  
  private String BEHAVIOR_NOT_FOUND = "behavior.not.found";
  
  @Override
  public List<EPackage> getEPackages() {
    return CollectionLiterals.<EPackage>newArrayList(DslPackage.eINSTANCE, AlePackage.eINSTANCE);
  }
  
  @Check
  public void checkValidSyntax(final Syntax syntax) {
    boolean _containsKey = EPackage.Registry.INSTANCE.containsKey(EcorePackage.eNS_URI);
    boolean _not = (!_containsKey);
    if (_not) {
      EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
    }
    Map<String, Object> _extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    XMIResourceFactoryImpl _xMIResourceFactoryImpl = new XMIResourceFactoryImpl();
    _extensionToFactoryMap.put("ecore", _xMIResourceFactoryImpl);
    final ResourceSetImpl rs = new ResourceSetImpl();
    EPackage.Registry _packageRegistry = rs.getPackageRegistry();
    String _value = syntax.getValue();
    EPackage _ePackage = _packageRegistry.getEPackage(_value);
    boolean _equals = Objects.equal(_ePackage, null);
    if (_equals) {
      this.error(
        "Package URI can\'t be resolve", syntax, 
        DslPackage.Literals.SYNTAX__VALUE, 
        this.BEHAVIOURS_URI_NOT_FOUND);
    }
  }
  
  @Check
  public void checkDsl(final DSL dsl) {
    Map<String, Object> _extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    XMIResourceFactoryImpl _xMIResourceFactoryImpl = new XMIResourceFactoryImpl();
    _extensionToFactoryMap.put("dsl", _xMIResourceFactoryImpl);
    Map<String, Object> _extensionToFactoryMap_1 = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    XMIResourceFactoryImpl _xMIResourceFactoryImpl_1 = new XMIResourceFactoryImpl();
    _extensionToFactoryMap_1.put("ale", _xMIResourceFactoryImpl_1);
    AleStandaloneSetup _aleStandaloneSetup = new AleStandaloneSetup();
    final Injector injector2 = _aleStandaloneSetup.createInjectorAndDoEMFRegistration();
    final XtextResourceSet resourceSet2 = injector2.<XtextResourceSet>getInstance(XtextResourceSet.class);
    resourceSet2.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
    EList<Behavior> _behaviours = dsl.getBehaviours();
    final Function1<Behavior, Root> _function = (Behavior b) -> {
      Root _xblockexpression = null;
      {
        String _value = b.getValue();
        final URI createURI = URI.createURI(_value);
        Resource resource2 = null;
        try {
          Resource _resource = resourceSet2.getResource(createURI, true);
          resource2 = _resource;
        } catch (final Throwable _t) {
          if (_t instanceof InvocationTargetException) {
            final InvocationTargetException e = (InvocationTargetException)_t;
            this.error("ale file reference cannot be resolved", b, DslPackage.Literals.SYNTAX__VALUE, this.BEHAVIOR_NOT_FOUND);
          } else if (_t instanceof Exception) {
            final Exception e_1 = (Exception)_t;
            this.error("ale file reference cannot be resolved", b, DslPackage.Literals.SYNTAX__VALUE, this.BEHAVIOR_NOT_FOUND);
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
        EList<EObject> _contents = resource2.getContents();
        EObject _head = IterableExtensions.<EObject>head(_contents);
        _xblockexpression = ((Root) _head);
      }
      return _xblockexpression;
    };
    List<Root> _map = ListExtensions.<Behavior, Root>map(_behaviours, _function);
    final List<Root> behaviors = IterableExtensions.<Root>toList(_map);
    final ResourceSetImpl resSet = new ResourceSetImpl();
    EList<Syntax> _syntaxes = dsl.getSyntaxes();
    final Function1<Syntax, EPackage> _function_1 = (Syntax stx) -> {
      boolean _containsKey = EPackage.Registry.INSTANCE.containsKey(EcorePackage.eNS_URI);
      boolean _not = (!_containsKey);
      if (_not) {
        EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
      }
      Map<String, Object> _extensionToFactoryMap_2 = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
      XMIResourceFactoryImpl _xMIResourceFactoryImpl_2 = new XMIResourceFactoryImpl();
      _extensionToFactoryMap_2.put("ecore", _xMIResourceFactoryImpl_2);
      EPackage.Registry _packageRegistry = resSet.getPackageRegistry();
      String _value = stx.getValue();
      String _replaceAll = _value.replaceAll("\"", "");
      EPackage _ePackage = _packageRegistry.getEPackage(_replaceAll);
      return ((EPackage) _ePackage);
    };
    List<EPackage> _map_1 = ListExtensions.<Syntax, EPackage>map(_syntaxes, _function_1);
    final List<EPackage> syntx = IterableExtensions.<EPackage>toList(_map_1);
    DslBehaviorValidator _dslBehaviorValidator = new DslBehaviorValidator(behaviors, syntx, dsl, this);
    _dslBehaviorValidator.validate();
  }
  
  public void errorB(final String message, final EObject source, final EStructuralFeature feature) {
    this.error(message, source, feature);
  }
}