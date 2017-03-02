package cz.ucl.hatchery.carevidence.web;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RequestQueryStringFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(RequestQueryStringFilter.class);

	private static final String[] PARAMS_TO_CATCH = new String[] { "page", "ordercol", "orderdir" };

	@Autowired
	private RequestQueryStringProvider provider;

	public void destroy() {
	}

	@SuppressWarnings("rawtypes")
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		final Set entrySet = request.getParameterMap().entrySet();
		for (final Object paramEntr : entrySet) {
			final Entry paramEntry = (Entry) paramEntr;
			if (ArrayUtils.indexOf(PARAMS_TO_CATCH, paramEntry.getKey()) != ArrayUtils.INDEX_NOT_FOUND) {
				provider.addParameter((String) paramEntry.getKey(), (String[]) paramEntry.getValue());
			}

		}

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		provider.setBaseUrl(httpRequest.getRequestURI());

		try {
			chain.doFilter(request, response);

		} catch (final ServletException e) {
			logErrorRequest(request);
			LOG.error("An exception stack: ", e);
			throw e;
		} catch (final IOException e) {
			logErrorRequest(request);
			LOG.error("An exception stack: ", e);
			throw e;
		}

	}

	public void init(final FilterConfig arg0) throws ServletException {
	}

	/*
	 * ***********************************************************************
	 * PRIVATE CLASSES
	 * **********************************************************************
	 */

	private void logErrorRequest(final ServletRequest request) {

		if (request instanceof HttpServletRequest) {
			final String url = ((HttpServletRequest) request).getRequestURL().toString();
			final String queryString = ((HttpServletRequest) request).getQueryString();
			LOG.error("Request mapping url : " + url + "?" + queryString);
		}

	}
}
