package cz.ucl.hatchery.carevidence.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ExternalConfigurationManagerBean implements ExternalConfigurationManager {

	private static final Logger LOG = LoggerFactory.getLogger(ExternalConfigurationManagerBean.class);

	@Autowired
	private ApplicationContext applicationContext;

	private static final String CONFIGURATION_PROPERTIES_FILE = "car-evidence-configuration.properties";

	@Override
	public Map<String, Object> getExternalConfiguration() {
		final Map<String, Object> result = new HashMap<String, Object>();

		return Collections.emptyMap();
		// Properties props;
		// try {
		// props = getExternalProperties();
		// } catch (final IOException e) {
		// LOG.error(e.getMessage(), e);
		// return Collections.emptyMap();
		// }
		//
		// for (final Entry<Object, Object> entry : props.entrySet()) {
		// result.put(entry.getKey().toString(), entry.getValue());
		// }
		// LOG.info("External configuration loaded " + props);
		// return result;
	}

	/*
	 * **********************************************************************
	 * PRIVATE METHODS
	 * **********************************************************************
	 */

	private Properties getExternalProperties() throws IOException {
		InputStream appConfigurationInputStream = null;
		final Properties props;
		try {
			final String fileName = getPropertiesFileName();
			LOG.debug("Loading external configuration from file " + fileName);
			appConfigurationInputStream = new FileInputStream(new File(fileName));
			props = new Properties();
			props.load(appConfigurationInputStream);
		} finally {
			IOUtils.closeQuietly(appConfigurationInputStream);
		}
		return props;
	}

	private String getPropertiesFileName() {
		return CONFIGURATION_PROPERTIES_FILE;
	}

}
