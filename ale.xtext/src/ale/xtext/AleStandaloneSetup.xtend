/*
 * generated by Xtext 2.10.0
 */
package ale.xtext


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class AleStandaloneSetup extends AleStandaloneSetupGenerated {

	def static void doSetup() {
		new AleStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}