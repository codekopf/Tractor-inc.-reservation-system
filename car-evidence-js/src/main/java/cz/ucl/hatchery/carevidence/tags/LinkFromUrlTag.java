package cz.ucl.hatchery.carevidence.tags;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import cz.ucl.hatchery.carevidence.web.CommonConstants;
import cz.ucl.hatchery.carevidence.web.RequestQueryStringProvider;
import cz.ucl.hatchery.carevidence.web.UrlStringProvider;

public class LinkFromUrlTag extends RequestContextAwareTag implements DynamicAttributes {

	private static final long serialVersionUID = -2020355239147562565L;
	private Map<String, Object> dynamicAttrs = new HashMap<String, Object>();

	@Autowired
	protected RequestQueryStringProvider queryStringProvider;

	protected String url;

	public void setUrl(final String url) {
		this.url = url;
	}

	protected boolean limitParams = false;

	public void setLimitParams(final boolean limitParams) {
		this.limitParams = limitParams;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		if (queryStringProvider == null) {
			final WebApplicationContext wac = getRequestContext().getWebApplicationContext();
			final AutowireCapableBeanFactory acbf = wac.getAutowireCapableBeanFactory();
			acbf.autowireBean(this);
		}

		final UrlStringProvider urlStringProvider = queryStringProvider.getQueryString();

		processQueryString(urlStringProvider);

		pageContext.getOut().print(urlStringProvider.toString());

		return Tag.SKIP_BODY;
	}

	protected void processQueryString(final UrlStringProvider urlStringProvider) {
		if (url != null) {
			urlStringProvider.setBaseUrl(url);
		}

		final Map<String, String[]> limitedParams = new LinkedHashMap<String, String[]>();
		for (final String limitedParamName : dynamicAttrs.keySet()) {
			final String limitedParamValue = (String) dynamicAttrs.get(limitedParamName);
			// use the same as in query
			if (StringUtils.isEmpty(limitedParamValue)) {
				final String[] valueFromRequest = urlStringProvider.getParameter(limitedParamName);
				if (valueFromRequest == null) {
					limitedParams.put(limitedParamName, new String[] { CommonConstants.EMPTY_STRING });
				} else {
					limitedParams.put(limitedParamName, valueFromRequest);
				}
			} else {
				limitedParams.put(limitedParamName, new String[] { limitedParamValue });
			}

		}
		if (limitParams) {
			urlStringProvider.clearParameters();
		}
		for (final String limitedParamName : limitedParams.keySet()) {
			urlStringProvider.setParameter(limitedParamName, limitedParams.get(limitedParamName));
		}
	}

	@Override
	public void setDynamicAttribute(final String uri, final String localName, final Object value) throws JspException {
		dynamicAttrs.put(localName, value);
	}

	public void setQueryStringProvider(final RequestQueryStringProvider queryStringProvider) {
		this.queryStringProvider = queryStringProvider;
	}

}
