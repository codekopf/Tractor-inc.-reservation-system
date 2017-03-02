package cz.ucl.hatchery.carevidence.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.SOAPFaultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.JstlView;

import cz.ucl.hatchery.carevidence.access.UserSession;

public class CarEvidenceAuthenticationDispatcherServletFilter implements Filter {

	private static final String AUTH_ERROR_JSP = "/WEB-INF/jsp/base/auth-error-page.jsp";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CarEvidenceAuthenticationDispatcherServletFilter.class);

	@Autowired
	private UserSession userSession;

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException {

		final HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (request instanceof HttpServletRequest) {
			final HttpServletRequest httpServletRequest = (HttpServletRequest) request;

			try {
				userSession.authenticate(httpServletRequest);
			} catch (final SOAPFaultException af) {

				// When communication with B2B fails, special error page is
				// rendered to output
				final JstlView jstlView = new JstlView(AUTH_ERROR_JSP);
				jstlView.setApplicationContext(applicationContext);

				jstlView.setServletContext(httpServletRequest.getServletContext());
				try {

					jstlView.render(new HashMap<String, Object>(), httpServletRequest, httpServletResponse);
					return;

				} catch (final Exception e) {
					LOGGER.error(e.getMessage(), e);
					httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				}

			} catch (final Exception e) {
				throw new ServletException(e);
			}

		}

		if (!userSession.isAuthenticated()) {

			LOGGER.debug("no user authenticated -> sending 401 Unauthorized");
			httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);

		} else {
			filterChain.doFilter(request, response);
		}

	}

	@Override
	public void init(final FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
