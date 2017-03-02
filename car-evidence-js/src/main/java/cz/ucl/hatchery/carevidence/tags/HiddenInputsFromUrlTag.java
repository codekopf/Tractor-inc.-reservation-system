package cz.ucl.hatchery.carevidence.tags;

import java.util.Map;

import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;

import cz.ucl.hatchery.carevidence.web.UrlStringProvider;

public class HiddenInputsFromUrlTag extends LinkFromUrlTag {

	private static final long serialVersionUID = 1L;

	@Override
	protected int doStartTagInternal() throws Exception {
		if (queryStringProvider == null) {
			final WebApplicationContext wac = getRequestContext().getWebApplicationContext();
			final AutowireCapableBeanFactory acbf = wac.getAutowireCapableBeanFactory();
			acbf.autowireBean(this);
		}

		final UrlStringProvider urlStringProvider = queryStringProvider.getQueryString();
		processQueryString(urlStringProvider);

		final Map<String, String[]> map = urlStringProvider.getParametersMap();
		for (final String parameterName : map.keySet()) {
			final String[] values = map.get(parameterName);
			for (final String value : values) {
				pageContext.getOut().print("<input type=\"hidden\" name=\"" + parameterName + "\" value=\""
						+ StringEscapeUtils.escapeHtml3(value) + "\">");
			}
		}

		return Tag.SKIP_BODY;
	}

}
