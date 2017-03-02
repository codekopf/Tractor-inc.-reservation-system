package cz.ucl.hatchery.carevidence.configuration;

import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import cz.ucl.hatchery.carevidence.web.CommonConstants;

@Component
public class ApplicationConfigurationBean implements ApplicationConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfigurationBean.class);

	@Autowired
	private ExternalConfigurationManager externalConfigurationManager;

	@Override
	public Object get(final String key) {
		return getConfiguration().get(key);
	}

	@Override
	public String getValue(final String key) {
		return getValue(key, String.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getValue(final String key, final Class<T> clazz) {
		return (T) (clazz.isInstance(getConfiguration().get(key)) ? getConfiguration().get(key)
				: ConvertUtils.convert(getConfiguration().get(key), clazz));
	}

	@Override
	public void setValue(final String key, final Object value) {
		LOG.debug("putting to application configuration " + key + ": " + value
				+ (value != null ? " - " + value.getClass().getName() : CommonConstants.EMPTY_STRING));
		getConfiguration().put(key, value);
	}

	private String getVersionInfo() {
		String version;
		try {
			LOG.info("Loading version info");
			final Resource appVersionResource = new ClassPathResource("version.info");
			version = FileUtils.readFileToString(appVersionResource.getFile());
			LOG.info("Found version info: " + version);
		} catch (final Exception e) {
			version = null;
			LOG.error("Loading version info failed", e);
		}
		return version;
	}

	private Map<String, Object> getConfiguration() {
		return externalConfigurationManager.getExternalConfiguration();
	}

}
