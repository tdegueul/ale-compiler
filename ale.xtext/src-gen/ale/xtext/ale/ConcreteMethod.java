/**
 * generated by Xtext 2.12.0
 */
package ale.xtext.ale;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concrete Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ale.xtext.ale.ConcreteMethod#getBlock <em>Block</em>}</li>
 * </ul>
 *
 * @see ale.xtext.ale.AlePackage#getConcreteMethod()
 * @model
 * @generated
 */
public interface ConcreteMethod extends AleMethod
{
  /**
   * Returns the value of the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Block</em>' containment reference.
   * @see #setBlock(XExpression)
   * @see ale.xtext.ale.AlePackage#getConcreteMethod_Block()
   * @model containment="true"
   * @generated
   */
  XExpression getBlock();

  /**
   * Sets the value of the '{@link ale.xtext.ale.ConcreteMethod#getBlock <em>Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Block</em>' containment reference.
   * @see #getBlock()
   * @generated
   */
  void setBlock(XExpression value);

} // ConcreteMethod
