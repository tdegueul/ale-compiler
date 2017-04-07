/**
 * generated by Xtext 2.10.0
 */
package ale.xtext.ale.impl;

import ale.xtext.ale.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AleFactoryImpl extends EFactoryImpl implements AleFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static AleFactory init()
  {
    try
    {
      AleFactory theAleFactory = (AleFactory)EPackage.Registry.INSTANCE.getEFactory(AlePackage.eNS_URI);
      if (theAleFactory != null)
      {
        return theAleFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new AleFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AleFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case AlePackage.ROOT: return createRoot();
      case AlePackage.IMPORT: return createImport();
      case AlePackage.ALE_CLASS: return createAleClass();
      case AlePackage.FIELD: return createField();
      case AlePackage.METHOD: return createMethod();
      case AlePackage.DEF_METHOD: return createDefMethod();
      case AlePackage.OVERRIDE_METHOD: return createOverrideMethod();
      case AlePackage.STATEMENT: return createStatement();
      case AlePackage.BLOCK: return createBlock();
      case AlePackage.EXPRESSION: return createExpression();
      case AlePackage.SYMBOL: return createSymbol();
      case AlePackage.PARAM_CALL: return createParamCall();
      case AlePackage.PARAM: return createParam();
      case AlePackage.TYPE: return createType();
      case AlePackage.LITERAL_TYPE: return createLiteralType();
      case AlePackage.TYPE_SYSTEM: return createTypeSystem();
      case AlePackage.OPEN_CLASS: return createOpenClass();
      case AlePackage.NEW_CLASS: return createNewClass();
      case AlePackage.RETURN_STATEMENT: return createReturnStatement();
      case AlePackage.LET_STATEMENT: return createLetStatement();
      case AlePackage.IF_STATEMENT: return createIfStatement();
      case AlePackage.WHILE_STATEMENT: return createWhileStatement();
      case AlePackage.FOR_LOOP: return createForLoop();
      case AlePackage.VAR_ASSIGN: return createVarAssign();
      case AlePackage.IMPLIES_OPERATION: return createImpliesOperation();
      case AlePackage.BOOLEAN_OR_OPERATION: return createBooleanOrOperation();
      case AlePackage.BOOLEAN_AND_OPERATION: return createBooleanAndOperation();
      case AlePackage.BOOLEAN_XOR_OPERATION: return createBooleanXorOperation();
      case AlePackage.COMPARE_LE_OPERATION: return createCompareLEOperation();
      case AlePackage.COMPARE_GE_OPERATION: return createCompareGEOperation();
      case AlePackage.COMPARE_NE_OPERATION: return createCompareNEOperation();
      case AlePackage.COMPARE_LOPERATION: return createCompareLOperation();
      case AlePackage.COMPARE_GOPERATION: return createCompareGOperation();
      case AlePackage.EQUALITY_OPERATION: return createEqualityOperation();
      case AlePackage.MULT_OPERATION: return createMultOperation();
      case AlePackage.DIV_OPERATION: return createDivOperation();
      case AlePackage.ADD_OPERATION: return createAddOperation();
      case AlePackage.SUB_OPERATION: return createSubOperation();
      case AlePackage.CHAINED_CALL: return createChainedCall();
      case AlePackage.CHAINED_CALL_ARROW: return createChainedCallArrow();
      case AlePackage.NOT_INFIX_OPERATION: return createNotInfixOperation();
      case AlePackage.NEG_INFIX_OPERATION: return createNegInfixOperation();
      case AlePackage.CONSTRUCTOR_OPERATION: return createConstructorOperation();
      case AlePackage.OPERATION_CALL_OPERATION: return createOperationCallOperation();
      case AlePackage.OA_DENOT: return createOADenot();
      case AlePackage.SELF_REF: return createSelfRef();
      case AlePackage.SUPER_REF: return createSuperRef();
      case AlePackage.STRING_LITERAL: return createStringLiteral();
      case AlePackage.INT_LITERAL: return createIntLiteral();
      case AlePackage.REAL_LITERAL: return createRealLiteral();
      case AlePackage.BOOLEAN_LITERAL: return createBooleanLiteral();
      case AlePackage.NULL_LITERAL: return createNullLiteral();
      case AlePackage.INT_RANGE: return createIntRange();
      case AlePackage.SEQUENCE_DECL: return createSequenceDecl();
      case AlePackage.ORDERED_SET_DECL: return createOrderedSetDecl();
      case AlePackage.VAR_REF: return createVarRef();
      case AlePackage.OUT_OF_SCOPE_TYPE: return createOutOfScopeType();
      case AlePackage.SEQUENCE_TYPE: return createSequenceType();
      case AlePackage.ORDERED_SET_TYPE: return createOrderedSetType();
      case AlePackage.BOOLEAN_TYPE_T: return createBooleanTypeT();
      case AlePackage.REAL_TYPE_T: return createRealTypeT();
      case AlePackage.INT_TYPE_T: return createIntTypeT();
      case AlePackage.STRING_TYPE_T: return createStringTypeT();
      case AlePackage.NULL_TYPE_T: return createNullTypeT();
      case AlePackage.SEQUENCE_TYPE_T: return createSequenceTypeT();
      case AlePackage.CLASS_TYPE_T: return createClassTypeT();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Root createRoot()
  {
    RootImpl root = new RootImpl();
    return root;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Import createImport()
  {
    ImportImpl import_ = new ImportImpl();
    return import_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AleClass createAleClass()
  {
    AleClassImpl aleClass = new AleClassImpl();
    return aleClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Field createField()
  {
    FieldImpl field = new FieldImpl();
    return field;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Method createMethod()
  {
    MethodImpl method = new MethodImpl();
    return method;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DefMethod createDefMethod()
  {
    DefMethodImpl defMethod = new DefMethodImpl();
    return defMethod;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OverrideMethod createOverrideMethod()
  {
    OverrideMethodImpl overrideMethod = new OverrideMethodImpl();
    return overrideMethod;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Statement createStatement()
  {
    StatementImpl statement = new StatementImpl();
    return statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Block createBlock()
  {
    BlockImpl block = new BlockImpl();
    return block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression createExpression()
  {
    ExpressionImpl expression = new ExpressionImpl();
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Symbol createSymbol()
  {
    SymbolImpl symbol = new SymbolImpl();
    return symbol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParamCall createParamCall()
  {
    ParamCallImpl paramCall = new ParamCallImpl();
    return paramCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Param createParam()
  {
    ParamImpl param = new ParamImpl();
    return param;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type createType()
  {
    TypeImpl type = new TypeImpl();
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LiteralType createLiteralType()
  {
    LiteralTypeImpl literalType = new LiteralTypeImpl();
    return literalType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeSystem createTypeSystem()
  {
    TypeSystemImpl typeSystem = new TypeSystemImpl();
    return typeSystem;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpenClass createOpenClass()
  {
    OpenClassImpl openClass = new OpenClassImpl();
    return openClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NewClass createNewClass()
  {
    NewClassImpl newClass = new NewClassImpl();
    return newClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ReturnStatement createReturnStatement()
  {
    ReturnStatementImpl returnStatement = new ReturnStatementImpl();
    return returnStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LetStatement createLetStatement()
  {
    LetStatementImpl letStatement = new LetStatementImpl();
    return letStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IfStatement createIfStatement()
  {
    IfStatementImpl ifStatement = new IfStatementImpl();
    return ifStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WhileStatement createWhileStatement()
  {
    WhileStatementImpl whileStatement = new WhileStatementImpl();
    return whileStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForLoop createForLoop()
  {
    ForLoopImpl forLoop = new ForLoopImpl();
    return forLoop;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VarAssign createVarAssign()
  {
    VarAssignImpl varAssign = new VarAssignImpl();
    return varAssign;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImpliesOperation createImpliesOperation()
  {
    ImpliesOperationImpl impliesOperation = new ImpliesOperationImpl();
    return impliesOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanOrOperation createBooleanOrOperation()
  {
    BooleanOrOperationImpl booleanOrOperation = new BooleanOrOperationImpl();
    return booleanOrOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanAndOperation createBooleanAndOperation()
  {
    BooleanAndOperationImpl booleanAndOperation = new BooleanAndOperationImpl();
    return booleanAndOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanXorOperation createBooleanXorOperation()
  {
    BooleanXorOperationImpl booleanXorOperation = new BooleanXorOperationImpl();
    return booleanXorOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompareLEOperation createCompareLEOperation()
  {
    CompareLEOperationImpl compareLEOperation = new CompareLEOperationImpl();
    return compareLEOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompareGEOperation createCompareGEOperation()
  {
    CompareGEOperationImpl compareGEOperation = new CompareGEOperationImpl();
    return compareGEOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompareNEOperation createCompareNEOperation()
  {
    CompareNEOperationImpl compareNEOperation = new CompareNEOperationImpl();
    return compareNEOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompareLOperation createCompareLOperation()
  {
    CompareLOperationImpl compareLOperation = new CompareLOperationImpl();
    return compareLOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompareGOperation createCompareGOperation()
  {
    CompareGOperationImpl compareGOperation = new CompareGOperationImpl();
    return compareGOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EqualityOperation createEqualityOperation()
  {
    EqualityOperationImpl equalityOperation = new EqualityOperationImpl();
    return equalityOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MultOperation createMultOperation()
  {
    MultOperationImpl multOperation = new MultOperationImpl();
    return multOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DivOperation createDivOperation()
  {
    DivOperationImpl divOperation = new DivOperationImpl();
    return divOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AddOperation createAddOperation()
  {
    AddOperationImpl addOperation = new AddOperationImpl();
    return addOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SubOperation createSubOperation()
  {
    SubOperationImpl subOperation = new SubOperationImpl();
    return subOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChainedCall createChainedCall()
  {
    ChainedCallImpl chainedCall = new ChainedCallImpl();
    return chainedCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChainedCallArrow createChainedCallArrow()
  {
    ChainedCallArrowImpl chainedCallArrow = new ChainedCallArrowImpl();
    return chainedCallArrow;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotInfixOperation createNotInfixOperation()
  {
    NotInfixOperationImpl notInfixOperation = new NotInfixOperationImpl();
    return notInfixOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NegInfixOperation createNegInfixOperation()
  {
    NegInfixOperationImpl negInfixOperation = new NegInfixOperationImpl();
    return negInfixOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstructorOperation createConstructorOperation()
  {
    ConstructorOperationImpl constructorOperation = new ConstructorOperationImpl();
    return constructorOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OperationCallOperation createOperationCallOperation()
  {
    OperationCallOperationImpl operationCallOperation = new OperationCallOperationImpl();
    return operationCallOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OADenot createOADenot()
  {
    OADenotImpl oaDenot = new OADenotImpl();
    return oaDenot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SelfRef createSelfRef()
  {
    SelfRefImpl selfRef = new SelfRefImpl();
    return selfRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SuperRef createSuperRef()
  {
    SuperRefImpl superRef = new SuperRefImpl();
    return superRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringLiteral createStringLiteral()
  {
    StringLiteralImpl stringLiteral = new StringLiteralImpl();
    return stringLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntLiteral createIntLiteral()
  {
    IntLiteralImpl intLiteral = new IntLiteralImpl();
    return intLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RealLiteral createRealLiteral()
  {
    RealLiteralImpl realLiteral = new RealLiteralImpl();
    return realLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanLiteral createBooleanLiteral()
  {
    BooleanLiteralImpl booleanLiteral = new BooleanLiteralImpl();
    return booleanLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NullLiteral createNullLiteral()
  {
    NullLiteralImpl nullLiteral = new NullLiteralImpl();
    return nullLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntRange createIntRange()
  {
    IntRangeImpl intRange = new IntRangeImpl();
    return intRange;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SequenceDecl createSequenceDecl()
  {
    SequenceDeclImpl sequenceDecl = new SequenceDeclImpl();
    return sequenceDecl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderedSetDecl createOrderedSetDecl()
  {
    OrderedSetDeclImpl orderedSetDecl = new OrderedSetDeclImpl();
    return orderedSetDecl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VarRef createVarRef()
  {
    VarRefImpl varRef = new VarRefImpl();
    return varRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OutOfScopeType createOutOfScopeType()
  {
    OutOfScopeTypeImpl outOfScopeType = new OutOfScopeTypeImpl();
    return outOfScopeType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SequenceType createSequenceType()
  {
    SequenceTypeImpl sequenceType = new SequenceTypeImpl();
    return sequenceType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderedSetType createOrderedSetType()
  {
    OrderedSetTypeImpl orderedSetType = new OrderedSetTypeImpl();
    return orderedSetType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanTypeT createBooleanTypeT()
  {
    BooleanTypeTImpl booleanTypeT = new BooleanTypeTImpl();
    return booleanTypeT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RealTypeT createRealTypeT()
  {
    RealTypeTImpl realTypeT = new RealTypeTImpl();
    return realTypeT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntTypeT createIntTypeT()
  {
    IntTypeTImpl intTypeT = new IntTypeTImpl();
    return intTypeT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringTypeT createStringTypeT()
  {
    StringTypeTImpl stringTypeT = new StringTypeTImpl();
    return stringTypeT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NullTypeT createNullTypeT()
  {
    NullTypeTImpl nullTypeT = new NullTypeTImpl();
    return nullTypeT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SequenceTypeT createSequenceTypeT()
  {
    SequenceTypeTImpl sequenceTypeT = new SequenceTypeTImpl();
    return sequenceTypeT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClassTypeT createClassTypeT()
  {
    ClassTypeTImpl classTypeT = new ClassTypeTImpl();
    return classTypeT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AlePackage getAlePackage()
  {
    return (AlePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static AlePackage getPackage()
  {
    return AlePackage.eINSTANCE;
  }

} //AleFactoryImpl
