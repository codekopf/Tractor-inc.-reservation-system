package cz.ucl.hatchery.carevidence.configuration;

/**
 * If you keep same system property in Singleton, you implement this interface
 * and you will be notified if properties was changed.
 * 
 * @author DZCJS9F
 *
 */
public interface RefreshableSystemProperties {

	/**
	 * You can rekeep new value from system properties
	 */
	public void refreshChangedSystemProperties();
}
