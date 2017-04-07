package dsl.xtext;

import ale.xtext.AleStandaloneSetup;
import ale.xtext.ale.AleClass;
import ale.xtext.ale.AleFactory;
import ale.xtext.ale.AlePackage;
import ale.xtext.ale.Block;
import ale.xtext.ale.BooleanLiteral;
import ale.xtext.ale.BooleanOrOperation;
import ale.xtext.ale.BooleanTypeT;
import ale.xtext.ale.ChainedCall;
import ale.xtext.ale.ClassTypeT;
import ale.xtext.ale.DefMethod;
import ale.xtext.ale.EqualityOperation;
import ale.xtext.ale.Expression;
import ale.xtext.ale.Field;
import ale.xtext.ale.ForLoop;
import ale.xtext.ale.Import;
import ale.xtext.ale.IntLiteral;
import ale.xtext.ale.IntRange;
import ale.xtext.ale.IntTypeT;
import ale.xtext.ale.LiteralType;
import ale.xtext.ale.Method;
import ale.xtext.ale.NewClass;
import ale.xtext.ale.NotInfixOperation;
import ale.xtext.ale.NullLiteral;
import ale.xtext.ale.NullTypeT;
import ale.xtext.ale.OpenClass;
import ale.xtext.ale.OperationCallOperation;
import ale.xtext.ale.OverrideMethod;
import ale.xtext.ale.Param;
import ale.xtext.ale.RealLiteral;
import ale.xtext.ale.RealTypeT;
import ale.xtext.ale.ReturnStatement;
import ale.xtext.ale.Root;
import ale.xtext.ale.SelfRef;
import ale.xtext.ale.SequenceTypeT;
import ale.xtext.ale.Statement;
import ale.xtext.ale.StringLiteral;
import ale.xtext.ale.StringTypeT;
import ale.xtext.ale.Type;
import ale.xtext.ale.TypeSystem;
import ale.xtext.ale.VarAssign;
import ale.xtext.ale.VarRef;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Injector;
import com.google.inject.Provider;
import dsl.xtext.dsl.Behavior;
import dsl.xtext.dsl.DSL;
import it.xsemantics.runtime.ErrorInformation;
import it.xsemantics.runtime.Result;
import it.xsemantics.runtime.RuleApplicationTrace;
import it.xsemantics.runtime.RuleEnvironment;
import it.xsemantics.runtime.RuleFailedException;
import it.xsemantics.runtime.XsemanticsRuntimeSystem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.PolymorphicDispatcher;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.MapExtensions;

@SuppressWarnings("all")
public class DslType extends XsemanticsRuntimeSystem {
  public final static String SUPERCLASSES = "dsl.xtext.SuperClasses";
  
  public final static String IMPORTED = "dsl.xtext.Imported";
  
  public final static String CLASSTYPE = "dsl.xtext.ClassType";
  
  public final static String BOOLEANLITERAL = "dsl.xtext.BooleanLiteral";
  
  public final static String STRINGLITERAL = "dsl.xtext.StringLiteral";
  
  public final static String INTLITERAL = "dsl.xtext.IntLiteral";
  
  public final static String REALLITERAL = "dsl.xtext.RealLiteral";
  
  public final static String NULLLITERALT = "dsl.xtext.NullLiteralt";
  
  public final static String INTRANGELITERAL = "dsl.xtext.IntRangeLiteral";
  
  public final static String SELFREF = "dsl.xtext.SelfRef";
  
  public final static String RETURNSTATEMENT = "dsl.xtext.ReturnStatement";
  
  public final static String VARREF = "dsl.xtext.VarRef";
  
  public final static String CHAINEDCALL = "dsl.xtext.ChainedCall";
  
  public final static String NOTINFIXOPERATIONRULE = "dsl.xtext.NotInfixOperationRule";
  
  public final static String OPERATIONCALLOPERATION = "dsl.xtext.OperationCallOperation";
  
  public final static String BOOLEANOROPERATION = "dsl.xtext.BooleanOrOperation";
  
  public final static String LITERALTYPE = "dsl.xtext.LiteralType";
  
  public final static String EQUALITYOPERATION = "dsl.xtext.EqualityOperation";
  
  public final static String VARASSIGN = "dsl.xtext.VarAssign";
  
  public final static String FORLOOP = "dsl.xtext.ForLoop";
  
  private PolymorphicDispatcher<Boolean> superClassesDispatcher;
  
  private PolymorphicDispatcher<Boolean> importedDispatcher;
  
  private PolymorphicDispatcher<Result<TypeSystem>> typeDispatcher;
  
  private PolymorphicDispatcher<Result<TypeSystem>> staticTypeDispatcher;
  
  private PolymorphicDispatcher<Result<TypeSystem>> classTypeDispatcher;
  
  public DslType() {
    init();
  }
  
  public void init() {
    typeDispatcher = buildPolymorphicDispatcher1(
    	"typeImpl", 3, "|-", ":");
    staticTypeDispatcher = buildPolymorphicDispatcher1(
    	"staticTypeImpl", 3, "|=", ":");
    classTypeDispatcher = buildPolymorphicDispatcher1(
    	"classTypeImpl", 3, "|>", ":");
    superClassesDispatcher = buildPolymorphicDispatcher(
    	"superClassesImpl", 3);
    importedDispatcher = buildPolymorphicDispatcher(
    	"importedImpl", 3);
  }
  
  public Boolean superClasses(final AleClass openClass, final List<AleClass> existing) throws RuleFailedException {
    return superClasses(null, openClass, existing);
  }
  
  public Boolean superClasses(final RuleApplicationTrace _trace_, final AleClass openClass, final List<AleClass> existing) throws RuleFailedException {
    try {
    	return superClassesInternal(_trace_, openClass, existing);
    } catch (Exception _e_superClasses) {
    	throw extractRuleFailedException(_e_superClasses);
    }
  }
  
  public Boolean imported(final Root root, final List<Root> roots) throws RuleFailedException {
    return imported(null, root, roots);
  }
  
  public Boolean imported(final RuleApplicationTrace _trace_, final Root root, final List<Root> roots) throws RuleFailedException {
    try {
    	return importedInternal(_trace_, root, roots);
    } catch (Exception _e_imported) {
    	throw extractRuleFailedException(_e_imported);
    }
  }
  
  public Result<TypeSystem> type(final Statement statement) {
    return type(new RuleEnvironment(), null, statement);
  }
  
  public Result<TypeSystem> type(final RuleEnvironment _environment_, final Statement statement) {
    return type(_environment_, null, statement);
  }
  
  public Result<TypeSystem> type(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final Statement statement) {
    try {
    	return typeInternal(_environment_, _trace_, statement);
    } catch (Exception _e_type) {
    	return resultForFailure(_e_type);
    }
  }
  
  public Result<TypeSystem> staticType(final Type type) {
    return staticType(new RuleEnvironment(), null, type);
  }
  
  public Result<TypeSystem> staticType(final RuleEnvironment _environment_, final Type type) {
    return staticType(_environment_, null, type);
  }
  
  public Result<TypeSystem> staticType(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final Type type) {
    try {
    	return staticTypeInternal(_environment_, _trace_, type);
    } catch (Exception _e_staticType) {
    	return resultForFailure(_e_staticType);
    }
  }
  
  public Result<TypeSystem> classType(final AleClass clazz) {
    return classType(new RuleEnvironment(), null, clazz);
  }
  
  public Result<TypeSystem> classType(final RuleEnvironment _environment_, final AleClass clazz) {
    return classType(_environment_, null, clazz);
  }
  
  public Result<TypeSystem> classType(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final AleClass clazz) {
    try {
    	return classTypeInternal(_environment_, _trace_, clazz);
    } catch (Exception _e_classType) {
    	return resultForFailure(_e_classType);
    }
  }
  
  public Result<Boolean> noCyclicOpenClassHierarchy(final OpenClass clazz) {
    return noCyclicOpenClassHierarchy(null, clazz);
  }
  
  public Result<Boolean> noCyclicOpenClassHierarchy(final RuleApplicationTrace _trace_, final OpenClass clazz) {
    try {
    	return noCyclicOpenClassHierarchyInternal(_trace_, clazz);
    } catch (Exception _e_NoCyclicOpenClassHierarchy) {
    	return resultForFailure(_e_NoCyclicOpenClassHierarchy);
    }
  }
  
  protected Result<Boolean> noCyclicOpenClassHierarchyInternal(final RuleApplicationTrace _trace_, final OpenClass clazz) throws RuleFailedException {
    final ArrayList<AleClass> ext = CollectionLiterals.<AleClass>newArrayList();
    Boolean _superClasses = this.superClassesInternal(_trace_, clazz, ext);
    /* superClasses(clazz, ext) */
    if (!_superClasses) {
      sneakyThrowRuleFailedException("superClasses(clazz, ext)");
    }
    boolean _contains = ext.contains(clazz);
    if (_contains) {
      /* fail error "Cyclic dependency" source clazz feature AlePackage::eINSTANCE.aleClass_SuperClass */
      String error = "Cyclic dependency";
      EObject source = clazz;
      EReference _aleClass_SuperClass = AlePackage.eINSTANCE.getAleClass_SuperClass();
      EStructuralFeature feature = _aleClass_SuperClass;
      throwForExplicitFail(error, new ErrorInformation(source, feature));
    }
    return new Result<Boolean>(true);
  }
  
  public Result<Boolean> defMethodDoesNotAlreadyExists(final DefMethod method) {
    return defMethodDoesNotAlreadyExists(null, method);
  }
  
  public Result<Boolean> defMethodDoesNotAlreadyExists(final RuleApplicationTrace _trace_, final DefMethod method) {
    try {
    	return defMethodDoesNotAlreadyExistsInternal(_trace_, method);
    } catch (Exception _e_defMethodDoesNotAlreadyExists) {
    	return resultForFailure(_e_defMethodDoesNotAlreadyExists);
    }
  }
  
  protected Result<Boolean> defMethodDoesNotAlreadyExistsInternal(final RuleApplicationTrace _trace_, final DefMethod method) throws RuleFailedException {
    final ArrayList<AleClass> classes = CollectionLiterals.<AleClass>newArrayList();
    EObject _eContainer = method.eContainer();
    Boolean _superClasses = this.superClassesInternal(_trace_, ((AleClass) _eContainer), classes);
    /* superClasses(method.eContainer as AleClass, classes) */
    if (!_superClasses) {
      sneakyThrowRuleFailedException("superClasses(method.eContainer as AleClass, classes)");
    }
    final Function1<AleClass, Boolean> _function = (AleClass c) -> {
      EList<Method> _methods = c.getMethods();
      final Function1<Method, Boolean> _function_1 = (Method it) -> {
        String _name = it.getName();
        String _name_1 = method.getName();
        return Boolean.valueOf(Objects.equal(_name, _name_1));
      };
      Iterable<Method> _filter = IterableExtensions.<Method>filter(_methods, _function_1);
      boolean _isEmpty = IterableExtensions.isEmpty(_filter);
      return Boolean.valueOf((!_isEmpty));
    };
    Iterable<AleClass> _filter = IterableExtensions.<AleClass>filter(classes, _function);
    boolean _isEmpty = IterableExtensions.isEmpty(_filter);
    boolean _not = (!_isEmpty);
    if (_not) {
      /* fail error "Method already defined" source method feature AlePackage::eINSTANCE.method_Name */
      String error = "Method already defined";
      EObject source = method;
      EAttribute _method_Name = AlePackage.eINSTANCE.getMethod_Name();
      EStructuralFeature feature = _method_Name;
      throwForExplicitFail(error, new ErrorInformation(source, feature));
    }
    return new Result<Boolean>(true);
  }
  
  public Result<Boolean> overrideMethodDoesMustExists(final OverrideMethod method) {
    return overrideMethodDoesMustExists(null, method);
  }
  
  public Result<Boolean> overrideMethodDoesMustExists(final RuleApplicationTrace _trace_, final OverrideMethod method) {
    try {
    	return overrideMethodDoesMustExistsInternal(_trace_, method);
    } catch (Exception _e_overrideMethodDoesMustExists) {
    	return resultForFailure(_e_overrideMethodDoesMustExists);
    }
  }
  
  protected Result<Boolean> overrideMethodDoesMustExistsInternal(final RuleApplicationTrace _trace_, final OverrideMethod method) throws RuleFailedException {
    final ArrayList<AleClass> classes = CollectionLiterals.<AleClass>newArrayList();
    EObject _eContainer = method.eContainer();
    Boolean _superClasses = this.superClassesInternal(_trace_, ((AleClass) _eContainer), classes);
    /* superClasses(method.eContainer as AleClass, classes) */
    if (!_superClasses) {
      sneakyThrowRuleFailedException("superClasses(method.eContainer as AleClass, classes)");
    }
    final Function1<AleClass, Boolean> _function = (AleClass c) -> {
      EList<Method> _methods = c.getMethods();
      final Function1<Method, Boolean> _function_1 = (Method it) -> {
        String _name = it.getName();
        String _name_1 = method.getName();
        return Boolean.valueOf(Objects.equal(_name, _name_1));
      };
      Iterable<Method> _filter = IterableExtensions.<Method>filter(_methods, _function_1);
      boolean _isEmpty = IterableExtensions.isEmpty(_filter);
      return Boolean.valueOf((!_isEmpty));
    };
    Iterable<AleClass> _filter = IterableExtensions.<AleClass>filter(classes, _function);
    boolean _isEmpty = IterableExtensions.isEmpty(_filter);
    if (_isEmpty) {
      /* fail error "Overrided method does not exist in parents" source method feature AlePackage::eINSTANCE.method_Name */
      String error = "Overrided method does not exist in parents";
      EObject source = method;
      EAttribute _method_Name = AlePackage.eINSTANCE.getMethod_Name();
      EStructuralFeature feature = _method_Name;
      throwForExplicitFail(error, new ErrorInformation(source, feature));
    }
    return new Result<Boolean>(true);
  }
  
  public Result<Boolean> noCyclicBehaviorImport(final Root root) {
    return noCyclicBehaviorImport(null, root);
  }
  
  public Result<Boolean> noCyclicBehaviorImport(final RuleApplicationTrace _trace_, final Root root) {
    try {
    	return noCyclicBehaviorImportInternal(_trace_, root);
    } catch (Exception _e_NoCyclicBehaviorImport) {
    	return resultForFailure(_e_NoCyclicBehaviorImport);
    }
  }
  
  protected Result<Boolean> noCyclicBehaviorImportInternal(final RuleApplicationTrace _trace_, final Root root) throws RuleFailedException {
    final ArrayList<Root> imports = CollectionLiterals.<Root>newArrayList();
    Boolean _imported = this.importedInternal(_trace_, root, imports);
    /* imported(root, imports) */
    if (!_imported) {
      sneakyThrowRuleFailedException("imported(root, imports)");
    }
    boolean _contains = imports.contains(root);
    if (_contains) {
      /* fail error "Cyclic dependency" source root feature AlePackage::eINSTANCE.root_Name */
      String error = "Cyclic dependency";
      EObject source = root;
      EAttribute _root_Name = AlePackage.eINSTANCE.getRoot_Name();
      EStructuralFeature feature = _root_Name;
      throwForExplicitFail(error, new ErrorInformation(source, feature));
    }
    return new Result<Boolean>(true);
  }
  
  public Result<Boolean> noCyclicNewClassHierarchy(final NewClass clazz) {
    return noCyclicNewClassHierarchy(null, clazz);
  }
  
  public Result<Boolean> noCyclicNewClassHierarchy(final RuleApplicationTrace _trace_, final NewClass clazz) {
    try {
    	return noCyclicNewClassHierarchyInternal(_trace_, clazz);
    } catch (Exception _e_NoCyclicNewClassHierarchy) {
    	return resultForFailure(_e_NoCyclicNewClassHierarchy);
    }
  }
  
  protected Result<Boolean> noCyclicNewClassHierarchyInternal(final RuleApplicationTrace _trace_, final NewClass clazz) throws RuleFailedException {
    final ArrayList<AleClass> ext = CollectionLiterals.<AleClass>newArrayList();
    Boolean _superClasses = this.superClassesInternal(_trace_, clazz, ext);
    /* superClasses(clazz, ext) */
    if (!_superClasses) {
      sneakyThrowRuleFailedException("superClasses(clazz, ext)");
    }
    boolean _contains = ext.contains(clazz);
    if (_contains) {
      /* fail error "Cyclic dependency" source clazz feature AlePackage::eINSTANCE.aleClass_SuperClass */
      String error = "Cyclic dependency";
      EObject source = clazz;
      EReference _aleClass_SuperClass = AlePackage.eINSTANCE.getAleClass_SuperClass();
      EStructuralFeature feature = _aleClass_SuperClass;
      throwForExplicitFail(error, new ErrorInformation(source, feature));
    }
    return new Result<Boolean>(true);
  }
  
  public Result<Boolean> checkClassNames(final Root root) {
    return checkClassNames(null, root);
  }
  
  public Result<Boolean> checkClassNames(final RuleApplicationTrace _trace_, final Root root) {
    try {
    	return checkClassNamesInternal(_trace_, root);
    } catch (Exception _e_CheckClassNames) {
    	return resultForFailure(_e_CheckClassNames);
    }
  }
  
  protected Result<Boolean> checkClassNamesInternal(final RuleApplicationTrace _trace_, final Root root) throws RuleFailedException {
    EList<AleClass> _classes = root.getClasses();
    final Function1<AleClass, String> _function = (AleClass it) -> {
      return it.getName();
    };
    Map<String, List<AleClass>> _groupBy = IterableExtensions.<String, AleClass>groupBy(_classes, _function);
    final Function2<String, List<AleClass>, Boolean> _function_1 = (String p1, List<AleClass> p2) -> {
      int _length = ((Object[])Conversions.unwrapArray(p2, Object.class)).length;
      return Boolean.valueOf((_length > 1));
    };
    Map<String, List<AleClass>> _filter = MapExtensions.<String, List<AleClass>>filter(_groupBy, _function_1);
    Collection<List<AleClass>> _values = _filter.values();
    Iterable<AleClass> _flatten = Iterables.<AleClass>concat(_values);
    for (final AleClass x : _flatten) {
      /* fail error "Duplicated name" source x feature AlePackage::eINSTANCE.aleClass_Name */
      String error = "Duplicated name";
      EObject source = x;
      EAttribute _aleClass_Name = AlePackage.eINSTANCE.getAleClass_Name();
      EStructuralFeature feature = _aleClass_Name;
      throwForExplicitFail(error, new ErrorInformation(source, feature));
    }
    return new Result<Boolean>(true);
  }
  
  public Result<Boolean> checkFieldName(final AleClass clazz) {
    return checkFieldName(null, clazz);
  }
  
  public Result<Boolean> checkFieldName(final RuleApplicationTrace _trace_, final AleClass clazz) {
    try {
    	return checkFieldNameInternal(_trace_, clazz);
    } catch (Exception _e_CheckFieldName) {
    	return resultForFailure(_e_CheckFieldName);
    }
  }
  
  protected Result<Boolean> checkFieldNameInternal(final RuleApplicationTrace _trace_, final AleClass clazz) throws RuleFailedException {
    EList<Field> _fields = clazz.getFields();
    final Function1<Field, String> _function = (Field it) -> {
      return it.getName();
    };
    Map<String, List<Field>> _groupBy = IterableExtensions.<String, Field>groupBy(_fields, _function);
    final Function2<String, List<Field>, Boolean> _function_1 = (String p1, List<Field> p2) -> {
      int _length = ((Object[])Conversions.unwrapArray(p2, Object.class)).length;
      return Boolean.valueOf((_length > 1));
    };
    Map<String, List<Field>> _filter = MapExtensions.<String, List<Field>>filter(_groupBy, _function_1);
    Collection<List<Field>> _values = _filter.values();
    Iterable<Field> _flatten = Iterables.<Field>concat(_values);
    for (final Field x : _flatten) {
      /* fail error "Duplicated name" source x feature AlePackage::eINSTANCE.field_Name */
      String error = "Duplicated name";
      EObject source = x;
      EAttribute _field_Name = AlePackage.eINSTANCE.getField_Name();
      EStructuralFeature feature = _field_Name;
      throwForExplicitFail(error, new ErrorInformation(source, feature));
    }
    return new Result<Boolean>(true);
  }
  
  public Result<Boolean> checkStateme(final Statement statement) {
    return checkStateme(null, statement);
  }
  
  public Result<Boolean> checkStateme(final RuleApplicationTrace _trace_, final Statement statement) {
    try {
    	return checkStatemeInternal(_trace_, statement);
    } catch (Exception _e_CheckStateme) {
    	return resultForFailure(_e_CheckStateme);
    }
  }
  
  protected Result<Boolean> checkStatemeInternal(final RuleApplicationTrace _trace_, final Statement statement) throws RuleFailedException {
    /* empty |- statement : var TypeSystem type */
    TypeSystem type = null;
    Result<TypeSystem> result = typeInternal(emptyEnvironment(), _trace_, statement);
    checkAssignableTo(result.getFirst(), TypeSystem.class);
    type = (TypeSystem) result.getFirst();
    
    return new Result<Boolean>(true);
  }
  
  public Result<Boolean> dsl(final DSL dsl) {
    return dsl(null, dsl);
  }
  
  public Result<Boolean> dsl(final RuleApplicationTrace _trace_, final DSL dsl) {
    try {
    	return dslInternal(_trace_, dsl);
    } catch (Exception _e_Dsl) {
    	return resultForFailure(_e_Dsl);
    }
  }
  
  protected Result<Boolean> dslInternal(final RuleApplicationTrace _trace_, final DSL dsl) throws RuleFailedException {
    AleStandaloneSetup _aleStandaloneSetup = new AleStandaloneSetup();
    Injector injector2 = _aleStandaloneSetup.createInjectorAndDoEMFRegistration();
    final XtextResourceSet resourceSet2 = injector2.<XtextResourceSet>getInstance(XtextResourceSet.class);
    resourceSet2.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
    EList<Behavior> _behaviours = dsl.getBehaviours();
    final Consumer<Behavior> _function = (Behavior b) -> {
      String _value = b.getValue();
      URI createURI = URI.createURI(_value);
      Resource resource2 = resourceSet2.getResource(createURI, true);
      EList<EObject> _contents = resource2.getContents();
      EObject _head = IterableExtensions.<EObject>head(_contents);
      EList<AleClass> _classes = ((Root) _head).getClasses();
      final Consumer<AleClass> _function_1 = (AleClass c) -> {
        /* empty |> c : var TypeSystem tmps */
        TypeSystem tmps = null;
        Result<TypeSystem> result = classTypeInternal(emptyEnvironment(), _trace_, c);
        checkAssignableTo(result.getFirst(), TypeSystem.class);
        tmps = (TypeSystem) result.getFirst();
        
      };
      _classes.forEach(_function_1);
    };
    _behaviours.forEach(_function);
    return new Result<Boolean>(true);
  }
  
  protected Boolean superClassesInternal(final RuleApplicationTrace _trace_, final AleClass openClass, final List<AleClass> existing) {
    try {
    	checkParamsNotNull(openClass, existing);
    	return superClassesDispatcher.invoke(_trace_, openClass, existing);
    } catch (Exception _e_superClasses) {
    	sneakyThrowRuleFailedException(_e_superClasses);
    	return false;
    }
  }
  
  protected void superClassesThrowException(final String _error, final String _issue, final Exception _ex, final AleClass openClass, final List<AleClass> existing, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    throwRuleFailedException(_error, _issue, _ex, _errorInformations);
  }
  
  protected Boolean importedInternal(final RuleApplicationTrace _trace_, final Root root, final List<Root> roots) {
    try {
    	checkParamsNotNull(root, roots);
    	return importedDispatcher.invoke(_trace_, root, roots);
    } catch (Exception _e_imported) {
    	sneakyThrowRuleFailedException(_e_imported);
    	return false;
    }
  }
  
  protected void importedThrowException(final String _error, final String _issue, final Exception _ex, final Root root, final List<Root> roots, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    throwRuleFailedException(_error, _issue, _ex, _errorInformations);
  }
  
  protected Result<TypeSystem> typeInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final Statement statement) {
    try {
    	checkParamsNotNull(statement);
    	return typeDispatcher.invoke(_environment_, _trace_, statement);
    } catch (Exception _e_type) {
    	sneakyThrowRuleFailedException(_e_type);
    	return null;
    }
  }
  
  protected void typeThrowException(final String _error, final String _issue, final Exception _ex, final Statement statement, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    String error = ("cannot type statement " + statement);
    EObject source = statement;
    throwRuleFailedException(error,
    	_issue, _ex, new ErrorInformation(source, null));
  }
  
  protected Result<TypeSystem> staticTypeInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final Type type) {
    try {
    	checkParamsNotNull(type);
    	return staticTypeDispatcher.invoke(_environment_, _trace_, type);
    } catch (Exception _e_staticType) {
    	sneakyThrowRuleFailedException(_e_staticType);
    	return null;
    }
  }
  
  protected void staticTypeThrowException(final String _error, final String _issue, final Exception _ex, final Type type, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    String error = ("cannot type type " + type);
    EObject source = type;
    throwRuleFailedException(error,
    	_issue, _ex, new ErrorInformation(source, null));
  }
  
  protected Result<TypeSystem> classTypeInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final AleClass clazz) {
    try {
    	checkParamsNotNull(clazz);
    	return classTypeDispatcher.invoke(_environment_, _trace_, clazz);
    } catch (Exception _e_classType) {
    	sneakyThrowRuleFailedException(_e_classType);
    	return null;
    }
  }
  
  protected void classTypeThrowException(final String _error, final String _issue, final Exception _ex, final AleClass clazz, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    String error = ("cannot type class" + clazz);
    EObject source = clazz;
    throwRuleFailedException(error,
    	_issue, _ex, new ErrorInformation(source, null));
  }
  
  protected Boolean superClassesImpl(final RuleApplicationTrace _trace_, final AleClass openClass, final List<AleClass> existing) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Boolean _result_ = applyAuxFunSuperClasses(_subtrace_, openClass, existing);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return auxFunName("superClasses") + "(" + stringRep(openClass) + ", " + stringRep(existing)+ ")" + " = " + stringRep(_result_);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyAuxFunSuperClasses) {
    	superClassesThrowException(auxFunName("superClasses") + "(" + stringRep(openClass) + ", " + stringRep(existing)+ ")",
    		SUPERCLASSES,
    		e_applyAuxFunSuperClasses, openClass, existing, new ErrorInformation[] {new ErrorInformation(openClass)});
    	return false;
    }
  }
  
  protected Boolean applyAuxFunSuperClasses(final RuleApplicationTrace _trace_, final AleClass openClass, final List<AleClass> existing) throws RuleFailedException {
    EList<AleClass> _superClass = openClass.getSuperClass();
    boolean _notEquals = (!Objects.equal(_superClass, null));
    if (_notEquals) {
      EList<AleClass> _superClass_1 = openClass.getSuperClass();
      for (final AleClass c : _superClass_1) {
        boolean _contains = existing.contains(c);
        boolean _not = (!_contains);
        if (_not) {
          boolean _add = existing.add(c);
          /* existing.add(c) */
          if (!_add) {
            sneakyThrowRuleFailedException("existing.add(c)");
          }
          /* superClasses(c, existing) */
          if (!this.superClassesInternal(_trace_, c, existing)) {
            sneakyThrowRuleFailedException("superClasses(c, existing)");
          }
        }
      }
    }
    return true;
  }
  
  protected Boolean importedImpl(final RuleApplicationTrace _trace_, final Root root, final List<Root> roots) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Boolean _result_ = applyAuxFunImported(_subtrace_, root, roots);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return auxFunName("imported") + "(" + stringRep(root) + ", " + stringRep(roots)+ ")" + " = " + stringRep(_result_);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyAuxFunImported) {
    	importedThrowException(auxFunName("imported") + "(" + stringRep(root) + ", " + stringRep(roots)+ ")",
    		IMPORTED,
    		e_applyAuxFunImported, root, roots, new ErrorInformation[] {new ErrorInformation(root)});
    	return false;
    }
  }
  
  protected Boolean applyAuxFunImported(final RuleApplicationTrace _trace_, final Root root, final List<Root> roots) throws RuleFailedException {
    EList<Import> _imports = root.getImports();
    boolean _notEquals = (!Objects.equal(_imports, null));
    if (_notEquals) {
      EList<Import> _imports_1 = root.getImports();
      for (final Import i : _imports_1) {
        Root _ref = i.getRef();
        boolean _contains = roots.contains(_ref);
        boolean _not = (!_contains);
        if (_not) {
          Root _ref_1 = i.getRef();
          boolean _add = roots.add(_ref_1);
          /* roots.add(i.ref) */
          if (!_add) {
            sneakyThrowRuleFailedException("roots.add(i.ref)");
          }
          Root _ref_2 = i.getRef();
          /* imported(i.ref, roots) */
          if (!this.importedInternal(_trace_, _ref_2, roots)) {
            sneakyThrowRuleFailedException("imported(i.ref, roots)");
          }
        }
      }
    }
    return true;
  }
  
  protected Result<TypeSystem> classTypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AleClass aleClass) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleClassType(G, _subtrace_, aleClass);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("ClassType") + stringRepForEnv(G) + " |> " + stringRep(aleClass) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleClassType) {
    	classTypeThrowException(ruleName("ClassType") + stringRepForEnv(G) + " |> " + stringRep(aleClass) + " : " + "ClassTypeT",
    		CLASSTYPE,
    		e_applyRuleClassType, aleClass, new ErrorInformation[] {new ErrorInformation(aleClass)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleClassType(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AleClass aleClass) throws RuleFailedException {
    
    return new Result<TypeSystem>(_applyRuleClassType_1(G, aleClass));
  }
  
  private ClassTypeT _applyRuleClassType_1(final RuleEnvironment G, final AleClass aleClass) throws RuleFailedException {
    ClassTypeT _createClassTypeT = AleFactory.eINSTANCE.createClassTypeT();
    return _createClassTypeT;
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BooleanLiteral bool) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleBooleanLiteral(G, _subtrace_, bool);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("BooleanLiteral") + stringRepForEnv(G) + " |- " + stringRep(bool) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleBooleanLiteral) {
    	typeThrowException(ruleName("BooleanLiteral") + stringRepForEnv(G) + " |- " + stringRep(bool) + " : " + "BooleanTypeT",
    		BOOLEANLITERAL,
    		e_applyRuleBooleanLiteral, bool, new ErrorInformation[] {new ErrorInformation(bool)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleBooleanLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BooleanLiteral bool) throws RuleFailedException {
    
    return new Result<TypeSystem>(_applyRuleBooleanLiteral_1(G, bool));
  }
  
  private BooleanTypeT _applyRuleBooleanLiteral_1(final RuleEnvironment G, final BooleanLiteral bool) throws RuleFailedException {
    BooleanTypeT _createBooleanTypeT = AleFactory.eINSTANCE.createBooleanTypeT();
    return _createBooleanTypeT;
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final StringLiteral bool) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleStringLiteral(G, _subtrace_, bool);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("StringLiteral") + stringRepForEnv(G) + " |- " + stringRep(bool) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleStringLiteral) {
    	typeThrowException(ruleName("StringLiteral") + stringRepForEnv(G) + " |- " + stringRep(bool) + " : " + "StringTypeT",
    		STRINGLITERAL,
    		e_applyRuleStringLiteral, bool, new ErrorInformation[] {new ErrorInformation(bool)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleStringLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final StringLiteral bool) throws RuleFailedException {
    
    return new Result<TypeSystem>(_applyRuleStringLiteral_1(G, bool));
  }
  
  private StringTypeT _applyRuleStringLiteral_1(final RuleEnvironment G, final StringLiteral bool) throws RuleFailedException {
    StringTypeT _createStringTypeT = AleFactory.eINSTANCE.createStringTypeT();
    return _createStringTypeT;
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IntLiteral bool) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleIntLiteral(G, _subtrace_, bool);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("IntLiteral") + stringRepForEnv(G) + " |- " + stringRep(bool) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleIntLiteral) {
    	typeThrowException(ruleName("IntLiteral") + stringRepForEnv(G) + " |- " + stringRep(bool) + " : " + "IntTypeT",
    		INTLITERAL,
    		e_applyRuleIntLiteral, bool, new ErrorInformation[] {new ErrorInformation(bool)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleIntLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IntLiteral bool) throws RuleFailedException {
    
    return new Result<TypeSystem>(_applyRuleIntLiteral_1(G, bool));
  }
  
  private IntTypeT _applyRuleIntLiteral_1(final RuleEnvironment G, final IntLiteral bool) throws RuleFailedException {
    IntTypeT _createIntTypeT = AleFactory.eINSTANCE.createIntTypeT();
    return _createIntTypeT;
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final RealLiteral bool) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleRealLiteral(G, _subtrace_, bool);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("RealLiteral") + stringRepForEnv(G) + " |- " + stringRep(bool) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleRealLiteral) {
    	typeThrowException(ruleName("RealLiteral") + stringRepForEnv(G) + " |- " + stringRep(bool) + " : " + "RealTypeT",
    		REALLITERAL,
    		e_applyRuleRealLiteral, bool, new ErrorInformation[] {new ErrorInformation(bool)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleRealLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final RealLiteral bool) throws RuleFailedException {
    
    return new Result<TypeSystem>(_applyRuleRealLiteral_1(G, bool));
  }
  
  private RealTypeT _applyRuleRealLiteral_1(final RuleEnvironment G, final RealLiteral bool) throws RuleFailedException {
    RealTypeT _createRealTypeT = AleFactory.eINSTANCE.createRealTypeT();
    return _createRealTypeT;
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NullLiteral bool) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleNullLiteralt(G, _subtrace_, bool);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("NullLiteralt") + stringRepForEnv(G) + " |- " + stringRep(bool) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleNullLiteralt) {
    	typeThrowException(ruleName("NullLiteralt") + stringRepForEnv(G) + " |- " + stringRep(bool) + " : " + "NullTypeT",
    		NULLLITERALT,
    		e_applyRuleNullLiteralt, bool, new ErrorInformation[] {new ErrorInformation(bool)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleNullLiteralt(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NullLiteral bool) throws RuleFailedException {
    
    return new Result<TypeSystem>(_applyRuleNullLiteralt_1(G, bool));
  }
  
  private NullTypeT _applyRuleNullLiteralt_1(final RuleEnvironment G, final NullLiteral bool) throws RuleFailedException {
    NullTypeT _createNullTypeT = AleFactory.eINSTANCE.createNullTypeT();
    return _createNullTypeT;
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IntRange bool) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleIntRangeLiteral(G, _subtrace_, bool);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("IntRangeLiteral") + stringRepForEnv(G) + " |- " + stringRep(bool) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleIntRangeLiteral) {
    	typeThrowException(ruleName("IntRangeLiteral") + stringRepForEnv(G) + " |- " + stringRep(bool) + " : " + "TypeSystem",
    		INTRANGELITERAL,
    		e_applyRuleIntRangeLiteral, bool, new ErrorInformation[] {new ErrorInformation(bool)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleIntRangeLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IntRange bool) throws RuleFailedException {
    TypeSystem x = null; // output parameter
    SequenceTypeT t = AleFactory.eINSTANCE.createSequenceTypeT();
    IntTypeT _createIntTypeT = AleFactory.eINSTANCE.createIntTypeT();
    t.setSubType(_createIntTypeT);
    x = t;
    return new Result<TypeSystem>(x);
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final SelfRef ref) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleSelfRef(G, _subtrace_, ref);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("SelfRef") + stringRepForEnv(G) + " |- " + stringRep(ref) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSelfRef) {
    	typeThrowException(ruleName("SelfRef") + stringRepForEnv(G) + " |- " + stringRep(ref) + " : " + "ClassTypeT",
    		SELFREF,
    		e_applyRuleSelfRef, ref, new ErrorInformation[] {new ErrorInformation(ref)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleSelfRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final SelfRef ref) throws RuleFailedException {
    ClassTypeT ts = null; // output parameter
    Iterable<EObject> _allContainers = EcoreUtil2.getAllContainers(ref);
    final Function1<EObject, Boolean> _function = (EObject e) -> {
      return Boolean.valueOf((e instanceof AleClass));
    };
    final EObject selfClass = IterableExtensions.<EObject>findFirst(_allContainers, _function);
    ClassTypeT _createClassTypeT = AleFactory.eINSTANCE.createClassTypeT();
    ts = _createClassTypeT;
    ts.setClazz(((AleClass) selfClass));
    return new Result<TypeSystem>(ts);
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ReturnStatement returnStatement) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleReturnStatement(G, _subtrace_, returnStatement);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("ReturnStatement") + stringRepForEnv(G) + " |- " + stringRep(returnStatement) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleReturnStatement) {
    	typeThrowException(ruleName("ReturnStatement") + stringRepForEnv(G) + " |- " + stringRep(returnStatement) + " : " + "TypeSystem",
    		RETURNSTATEMENT,
    		e_applyRuleReturnStatement, returnStatement, new ErrorInformation[] {new ErrorInformation(returnStatement)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleReturnStatement(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ReturnStatement returnStatement) throws RuleFailedException {
    TypeSystem typeRet = null; // output parameter
    /* G |- returnStatement.returned : typeRet */
    Expression _returned = returnStatement.getReturned();
    Result<TypeSystem> result = typeInternal(G, _trace_, _returned);
    checkAssignableTo(result.getFirst(), TypeSystem.class);
    typeRet = (TypeSystem) result.getFirst();
    
    return new Result<TypeSystem>(typeRet);
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final VarRef varRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleVarRef(G, _subtrace_, varRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("VarRef") + stringRepForEnv(G) + " |- " + stringRep(varRef) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleVarRef) {
    	typeThrowException(ruleName("VarRef") + stringRepForEnv(G) + " |- " + stringRep(varRef) + " : " + "TypeSystem",
    		VARREF,
    		e_applyRuleVarRef, varRef, new ErrorInformation[] {new ErrorInformation(varRef)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleVarRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final VarRef varRef) throws RuleFailedException {
    TypeSystem vrt = null; // output parameter
    EObject _eContainer = varRef.eContainer();
    if ((_eContainer instanceof Block)) {
      Iterable<EObject> _allContainers = EcoreUtil2.getAllContainers(varRef);
      final Function1<EObject, Boolean> _function = (EObject e) -> {
        return Boolean.valueOf((e instanceof Block));
      };
      EObject _findFirst = IterableExtensions.<EObject>findFirst(_allContainers, _function);
      Block parentBlock = ((Block) _findFirst);
      EList<Statement> _body = parentBlock.getBody();
      int varRefIdx = _body.indexOf(varRef);
      EList<Statement> _body_1 = parentBlock.getBody();
      List<Statement> scope = _body_1.subList(0, (varRefIdx - 1));
      final Function1<Statement, Boolean> _function_1 = (Statement e) -> {
        return Boolean.valueOf((e instanceof VarAssign));
      };
      Iterable<Statement> _filter = IterableExtensions.<Statement>filter(scope, _function_1);
      final Function1<Statement, Boolean> _function_2 = (Statement e) -> {
        String _name = ((VarAssign) e).getName();
        String _value = varRef.getValue();
        return Boolean.valueOf(Objects.equal(_name, _value));
      };
      Iterable<Statement> res = IterableExtensions.<Statement>filter(_filter, _function_2);
      boolean _isEmpty = IterableExtensions.isEmpty(res);
      boolean _not = (!_isEmpty);
      if (_not) {
        /* G |- res.head : var TypeSystem varType */
        Statement _head = IterableExtensions.<Statement>head(res);
        TypeSystem varType = null;
        Result<TypeSystem> result = typeInternal(G, _trace_, _head);
        checkAssignableTo(result.getFirst(), TypeSystem.class);
        varType = (TypeSystem) result.getFirst();
        
        vrt = varType;
      } else {
        Iterable<EObject> _allContainers_1 = EcoreUtil2.getAllContainers(varRef);
        final Function1<EObject, Boolean> _function_3 = (EObject e) -> {
          return Boolean.valueOf((e instanceof Method));
        };
        EObject _findFirst_1 = IterableExtensions.<EObject>findFirst(_allContainers_1, _function_3);
        Method method = ((Method) _findFirst_1);
        EList<Param> _params = method.getParams();
        final Function1<Param, Boolean> _function_4 = (Param p) -> {
          String _name = p.getName();
          String _value = varRef.getValue();
          return Boolean.valueOf(Objects.equal(_name, _value));
        };
        Iterable<Param> param = IterableExtensions.<Param>filter(_params, _function_4);
        boolean _isEmpty_1 = IterableExtensions.isEmpty(param);
        boolean _not_1 = (!_isEmpty_1);
        if (_not_1) {
          /* G |= param.head.type : var TypeSystem paramType */
          Param _head_1 = IterableExtensions.<Param>head(param);
          Type _type = _head_1.getType();
          TypeSystem paramType = null;
          Result<TypeSystem> result_1 = staticTypeInternal(G, _trace_, _type);
          checkAssignableTo(result_1.getFirst(), TypeSystem.class);
          paramType = (TypeSystem) result_1.getFirst();
          
          vrt = paramType;
        }
      }
    }
    return new Result<TypeSystem>(vrt);
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ChainedCall cc) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleChainedCall(G, _subtrace_, cc);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("ChainedCall") + stringRepForEnv(G) + " |- " + stringRep(cc) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleChainedCall) {
    	typeThrowException(ruleName("ChainedCall") + stringRepForEnv(G) + " |- " + stringRep(cc) + " : " + "TypeSystem",
    		CHAINEDCALL,
    		e_applyRuleChainedCall, cc, new ErrorInformation[] {new ErrorInformation(cc)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleChainedCall(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ChainedCall cc) throws RuleFailedException {
    TypeSystem cct = null; // output parameter
    /* G |- cc.left : var TypeSystem cclt */
    Expression _left = cc.getLeft();
    TypeSystem cclt = null;
    Result<TypeSystem> result = typeInternal(G, _trace_, _left);
    checkAssignableTo(result.getFirst(), TypeSystem.class);
    cclt = (TypeSystem) result.getFirst();
    
    /* G |- cc.right : var TypeSystem ccrt */
    Expression _right = cc.getRight();
    TypeSystem ccrt = null;
    Result<TypeSystem> result_1 = typeInternal(G, _trace_, _right);
    checkAssignableTo(result_1.getFirst(), TypeSystem.class);
    ccrt = (TypeSystem) result_1.getFirst();
    
    /* fail error cclt + " //// " */
    String _plus = (cclt + " //// ");
    String error = _plus;
    throwForExplicitFail(error, new ErrorInformation(null, null));
    /* ccrt; */
    return new Result<TypeSystem>(cct);
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NotInfixOperation nio) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleNotInfixOperationRule(G, _subtrace_, nio);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("NotInfixOperationRule") + stringRepForEnv(G) + " |- " + stringRep(nio) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleNotInfixOperationRule) {
    	typeThrowException(ruleName("NotInfixOperationRule") + stringRepForEnv(G) + " |- " + stringRep(nio) + " : " + "BooleanTypeT",
    		NOTINFIXOPERATIONRULE,
    		e_applyRuleNotInfixOperationRule, nio, new ErrorInformation[] {new ErrorInformation(nio)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleNotInfixOperationRule(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NotInfixOperation nio) throws RuleFailedException {
    BooleanTypeT nioT = null; // output parameter
    /* G |- nio.expression : nioT */
    Expression _expression = nio.getExpression();
    Result<TypeSystem> result = typeInternal(G, _trace_, _expression);
    checkAssignableTo(result.getFirst(), BooleanTypeT.class);
    nioT = (BooleanTypeT) result.getFirst();
    
    return new Result<TypeSystem>(nioT);
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final OperationCallOperation oc) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleOperationCallOperation(G, _subtrace_, oc);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("OperationCallOperation") + stringRepForEnv(G) + " |- " + stringRep(oc) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleOperationCallOperation) {
    	typeThrowException(ruleName("OperationCallOperation") + stringRepForEnv(G) + " |- " + stringRep(oc) + " : " + "TypeSystem",
    		OPERATIONCALLOPERATION,
    		e_applyRuleOperationCallOperation, oc, new ErrorInformation[] {new ErrorInformation(oc)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleOperationCallOperation(final RuleEnvironment G, final RuleApplicationTrace _trace_, final OperationCallOperation oc) throws RuleFailedException {
    TypeSystem oct = null; // output parameter
    EObject _eContainer = oc.eContainer();
    if ((_eContainer instanceof ChainedCall)) {
      /* G |- (oc.eContainer as ChainedCall).left : var TypeSystem leftType */
      EObject _eContainer_1 = oc.eContainer();
      Expression _left = ((ChainedCall) _eContainer_1).getLeft();
      TypeSystem leftType = null;
      Result<TypeSystem> result = typeInternal(G, _trace_, _left);
      checkAssignableTo(result.getFirst(), TypeSystem.class);
      leftType = (TypeSystem) result.getFirst();
      
      if ((leftType instanceof ClassTypeT)) {
        AleClass _clazz = ((ClassTypeT)leftType).getClazz();
        EList<Method> _methods = _clazz.getMethods();
        final Function1<Method, Boolean> _function = (Method m) -> {
          return Boolean.valueOf((Objects.equal(m.getName(), oc.getName()) && (m.getParams().size() == oc.getParameters().size())));
        };
        Iterable<Method> methods = IterableExtensions.<Method>filter(_methods, _function);
        boolean _isEmpty = IterableExtensions.isEmpty(methods);
        boolean _not = (!_isEmpty);
        if (_not) {
          /* G |= methods.head.type : var TypeSystem metType */
          Method _head = IterableExtensions.<Method>head(methods);
          Type _type = _head.getType();
          TypeSystem metType = null;
          Result<TypeSystem> result_1 = staticTypeInternal(G, _trace_, _type);
          checkAssignableTo(result_1.getFirst(), TypeSystem.class);
          metType = (TypeSystem) result_1.getFirst();
          
          oct = metType;
        } else {
        }
      }
    }
    return new Result<TypeSystem>(oct);
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BooleanOrOperation boo) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleBooleanOrOperation(G, _subtrace_, boo);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("BooleanOrOperation") + stringRepForEnv(G) + " |- " + stringRep(boo) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleBooleanOrOperation) {
    	typeThrowException(ruleName("BooleanOrOperation") + stringRepForEnv(G) + " |- " + stringRep(boo) + " : " + "BooleanTypeT",
    		BOOLEANOROPERATION,
    		e_applyRuleBooleanOrOperation, boo, new ErrorInformation[] {new ErrorInformation(boo)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleBooleanOrOperation(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BooleanOrOperation boo) throws RuleFailedException {
    /* G |- boo.left : var TypeSystem leftT */
    Expression _left = boo.getLeft();
    TypeSystem leftT = null;
    Result<TypeSystem> result = typeInternal(G, _trace_, _left);
    checkAssignableTo(result.getFirst(), TypeSystem.class);
    leftT = (TypeSystem) result.getFirst();
    
    /* G |- boo.right : var TypeSystem rightT */
    Expression _right = boo.getRight();
    TypeSystem rightT = null;
    Result<TypeSystem> result_1 = typeInternal(G, _trace_, _right);
    checkAssignableTo(result_1.getFirst(), TypeSystem.class);
    rightT = (TypeSystem) result_1.getFirst();
    
    /* leftT instanceof BooleanTypeT && rightT instanceof BooleanTypeT */
    if (!((leftT instanceof BooleanTypeT) && (rightT instanceof BooleanTypeT))) {
      sneakyThrowRuleFailedException("leftT instanceof BooleanTypeT && rightT instanceof BooleanTypeT");
    }
    return new Result<TypeSystem>(_applyRuleBooleanOrOperation_1(G, boo));
  }
  
  private BooleanTypeT _applyRuleBooleanOrOperation_1(final RuleEnvironment G, final BooleanOrOperation boo) throws RuleFailedException {
    BooleanTypeT _createBooleanTypeT = AleFactory.eINSTANCE.createBooleanTypeT();
    return _createBooleanTypeT;
  }
  
  protected Result<TypeSystem> staticTypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final LiteralType litType) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleLiteralType(G, _subtrace_, litType);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("LiteralType") + stringRepForEnv(G) + " |= " + stringRep(litType) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleLiteralType) {
    	staticTypeThrowException(ruleName("LiteralType") + stringRepForEnv(G) + " |= " + stringRep(litType) + " : " + "TypeSystem",
    		LITERALTYPE,
    		e_applyRuleLiteralType, litType, new ErrorInformation[] {new ErrorInformation(litType)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleLiteralType(final RuleEnvironment G, final RuleApplicationTrace _trace_, final LiteralType litType) throws RuleFailedException {
    TypeSystem typeLit = null; // output parameter
    /* { (litType.lit == 'Boolean') typeLit = AleFactory.eINSTANCE.createBooleanTypeT } or { (litType.lit == 'Real') typeLit = AleFactory.eINSTANCE.createRealTypeT } or { (litType.lit == 'Int') typeLit = AleFactory.eINSTANCE.createIntTypeT } or { (litType.lit == 'String') typeLit = AleFactory.eINSTANCE.createStringTypeT } */
    {
      RuleFailedException previousFailure = null;
      try {
        String _lit = litType.getLit();
        boolean _equals = Objects.equal(_lit, "Boolean");
        /* litType.lit == 'Boolean' */
        if (!_equals) {
          sneakyThrowRuleFailedException("litType.lit == \'Boolean\'");
        }
        BooleanTypeT _createBooleanTypeT = AleFactory.eINSTANCE.createBooleanTypeT();
        typeLit = _createBooleanTypeT;
      } catch (Exception e) {
        previousFailure = extractRuleFailedException(e);
        /* { (litType.lit == 'Real') typeLit = AleFactory.eINSTANCE.createRealTypeT } or { (litType.lit == 'Int') typeLit = AleFactory.eINSTANCE.createIntTypeT } or { (litType.lit == 'String') typeLit = AleFactory.eINSTANCE.createStringTypeT } */
        {
          try {
            String _lit_1 = litType.getLit();
            boolean _equals_1 = Objects.equal(_lit_1, "Real");
            /* litType.lit == 'Real' */
            if (!_equals_1) {
              sneakyThrowRuleFailedException("litType.lit == \'Real\'");
            }
            RealTypeT _createRealTypeT = AleFactory.eINSTANCE.createRealTypeT();
            typeLit = _createRealTypeT;
          } catch (Exception e_1) {
            previousFailure = extractRuleFailedException(e_1);
            /* { (litType.lit == 'Int') typeLit = AleFactory.eINSTANCE.createIntTypeT } or { (litType.lit == 'String') typeLit = AleFactory.eINSTANCE.createStringTypeT } */
            {
              try {
                String _lit_2 = litType.getLit();
                boolean _equals_2 = Objects.equal(_lit_2, "Int");
                /* litType.lit == 'Int' */
                if (!_equals_2) {
                  sneakyThrowRuleFailedException("litType.lit == \'Int\'");
                }
                IntTypeT _createIntTypeT = AleFactory.eINSTANCE.createIntTypeT();
                typeLit = _createIntTypeT;
              } catch (Exception e_2) {
                previousFailure = extractRuleFailedException(e_2);
                String _lit_3 = litType.getLit();
                boolean _equals_3 = Objects.equal(_lit_3, "String");
                /* litType.lit == 'String' */
                if (!_equals_3) {
                  sneakyThrowRuleFailedException("litType.lit == \'String\'");
                }
                StringTypeT _createStringTypeT = AleFactory.eINSTANCE.createStringTypeT();
                typeLit = _createStringTypeT;
              }
            }
          }
        }
      }
    }
    return new Result<TypeSystem>(typeLit);
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EqualityOperation eOp) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleEqualityOperation(G, _subtrace_, eOp);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("EqualityOperation") + stringRepForEnv(G) + " |- " + stringRep(eOp) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleEqualityOperation) {
    	typeThrowException(ruleName("EqualityOperation") + stringRepForEnv(G) + " |- " + stringRep(eOp) + " : " + "BooleanTypeT",
    		EQUALITYOPERATION,
    		e_applyRuleEqualityOperation, eOp, new ErrorInformation[] {new ErrorInformation(eOp)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleEqualityOperation(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EqualityOperation eOp) throws RuleFailedException {
    /* G |- eOp.left : var TypeSystem tl */
    Expression _left = eOp.getLeft();
    TypeSystem tl = null;
    Result<TypeSystem> result = typeInternal(G, _trace_, _left);
    checkAssignableTo(result.getFirst(), TypeSystem.class);
    tl = (TypeSystem) result.getFirst();
    
    /* G |- eOp.right: var TypeSystem tr */
    Expression _right = eOp.getRight();
    TypeSystem tr = null;
    Result<TypeSystem> result_1 = typeInternal(G, _trace_, _right);
    checkAssignableTo(result_1.getFirst(), TypeSystem.class);
    tr = (TypeSystem) result_1.getFirst();
    
    EClass _eClass = tl.eClass();
    EClass _eClass_1 = tr.eClass();
    /* tl.eClass == tr.eClass */
    if (!Objects.equal(_eClass, _eClass_1)) {
      sneakyThrowRuleFailedException("tl.eClass == tr.eClass");
    }
    return new Result<TypeSystem>(_applyRuleEqualityOperation_1(G, eOp));
  }
  
  private BooleanTypeT _applyRuleEqualityOperation_1(final RuleEnvironment G, final EqualityOperation eOp) throws RuleFailedException {
    BooleanTypeT _createBooleanTypeT = AleFactory.eINSTANCE.createBooleanTypeT();
    return _createBooleanTypeT;
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final VarAssign varAssign) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleVarAssign(G, _subtrace_, varAssign);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("VarAssign") + stringRepForEnv(G) + " |- " + stringRep(varAssign) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleVarAssign) {
    	typeThrowException(ruleName("VarAssign") + stringRepForEnv(G) + " |- " + stringRep(varAssign) + " : " + "TypeSystem",
    		VARASSIGN,
    		e_applyRuleVarAssign, varAssign, new ErrorInformation[] {new ErrorInformation(varAssign)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleVarAssign(final RuleEnvironment G, final RuleApplicationTrace _trace_, final VarAssign varAssign) throws RuleFailedException {
    TypeSystem varType = null; // output parameter
    /* G |- varAssign.value: var TypeSystem valueType */
    Expression _value = varAssign.getValue();
    TypeSystem valueType = null;
    Result<TypeSystem> result = typeInternal(G, _trace_, _value);
    checkAssignableTo(result.getFirst(), TypeSystem.class);
    valueType = (TypeSystem) result.getFirst();
    
    /* G |= varAssign.getType : var TypeSystem typeType */
    Type _type = varAssign.getType();
    TypeSystem typeType = null;
    Result<TypeSystem> result_1 = staticTypeInternal(G, _trace_, _type);
    checkAssignableTo(result_1.getFirst(), TypeSystem.class);
    typeType = (TypeSystem) result_1.getFirst();
    
    EClass _eClass = valueType.eClass();
    EClass _eClass_1 = typeType.eClass();
    boolean _equals = Objects.equal(_eClass, _eClass_1);
    /* valueType.eClass == typeType.eClass */
    if (!_equals) {
      sneakyThrowRuleFailedException("valueType.eClass == typeType.eClass");
    }
    varType = valueType;
    return new Result<TypeSystem>(varType);
  }
  
  protected Result<TypeSystem> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ForLoop fl) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeSystem> _result_ = applyRuleForLoop(G, _subtrace_, fl);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("ForLoop") + stringRepForEnv(G) + " |- " + stringRep(fl) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleForLoop) {
    	typeThrowException(ruleName("ForLoop") + stringRepForEnv(G) + " |- " + stringRep(fl) + " : " + "TypeSystem",
    		FORLOOP,
    		e_applyRuleForLoop, fl, new ErrorInformation[] {new ErrorInformation(fl)});
    	return null;
    }
  }
  
  protected Result<TypeSystem> applyRuleForLoop(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ForLoop fl) throws RuleFailedException {
    TypeSystem flt = null; // output parameter
    /* G |- fl.collection : var TypeSystem colT */
    Expression _collection = fl.getCollection();
    TypeSystem colT = null;
    Result<TypeSystem> result = typeInternal(G, _trace_, _collection);
    checkAssignableTo(result.getFirst(), TypeSystem.class);
    colT = (TypeSystem) result.getFirst();
    
    if ((colT instanceof SequenceTypeT)) {
      TypeSystem _subType = ((SequenceTypeT)colT).getSubType();
      TypeSystem _copy = EcoreUtil.<TypeSystem>copy(_subType);
      flt = _copy;
    }
    return new Result<TypeSystem>(flt);
  }
}
