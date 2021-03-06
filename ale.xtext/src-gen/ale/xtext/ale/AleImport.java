/**
 * generated by Xtext 2.12.0
 */
package ale.xtext.ale;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ale.xtext.ale.AleImport#getRef <em>Ref</em>}</li>
 * </ul>
 *
 * @see ale.xtext.ale.AlePackage#getAleImport()
 * @model
 * @generated
 */
public interface AleImport extends EObject
{
  /**
   * Returns the value of the '<em><b>Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ref</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref</em>' reference.
   * @see #setRef(AleRoot)
   * @see ale.xtext.ale.AlePackage#getAleImport_Ref()
   * @model
   * @generated
   */
  AleRoot getRef();

  /**
   * Sets the value of the '{@link ale.xtext.ale.AleImport#getRef <em>Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ref</em>' reference.
   * @see #getRef()
   * @generated
   */
  void setRef(AleRoot value);

} // AleImport
