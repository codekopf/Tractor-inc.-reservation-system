package cz.ucl.hatchery.carevidence.tags;

import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import cz.ucl.hatchery.carevidence.access.UserSessionHolder;
import cz.ucl.hatchery.carevidence.configuration.ApplicationConfiguration;

public class SystemConfigTag extends RequestContextAwareTag {

	private static final long serialVersionUID = -7694677898457575186L;

	private ApplicationConfiguration systemConfig;

	private UserSessionHolder userSessionHolder;

	private String key;

	private String var = null;

	public void setKey(final String key) {
		this.key = key;
	}

	public void setVar(final String var) {
		this.var = var;
	}

	@Override
	public int doStartTagInternal() throws Exception {

		if (systemConfig == null) {
			final WebApplicationContext wac = getRequestContext().getWebApplicationContext();
			final AutowireCapableBeanFactory acbf = wac.getAutowireCapableBeanFactory();
			acbf.autowireBean(this);
		}

		String value = systemConfig.getValue(key, String.class);
		if (value == null) {
			value = userSessionHolder.getProperty(key, String.class);
		}

		if (value == null) {
			return Tag.EVAL_PAGE;
		}

		if (StringUtils.isBlank(var)) {
			pageContext.getOut().write(value);
		} else {
			pageContext.setAttribute(var, value);
		}

		return Tag.EVAL_PAGE;

	}

	@Autowired
	public void setSystemConfig(final ApplicationConfiguration systemConfig) {
		this.systemConfig = systemConfig;
	}

	@Autowired
	public void setUserSessionHolder(final UserSessionHolder userSessionHolder) {
		this.userSessionHolder = userSessionHolder;
	}

}
