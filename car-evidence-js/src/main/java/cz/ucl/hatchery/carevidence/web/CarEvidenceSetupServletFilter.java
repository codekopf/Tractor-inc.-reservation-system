package cz.ucl.hatchery.carevidence.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cz.ucl.hatchery.carevidence.i18n.CarEvidenceLocaleResolver;

/**
 * Filter, ktery se stara o nasledujici:
 * 
 * - ulozi do requestu aktualni URI pozadavku predtim, nez ho zpracuje Spring a
 * zmeni ho,<br>
 * - zachytava z requestu parametr lang, ktery slouzi pro zmenu locale aplikace;
 * <br>
 * - nastavi spravne kodovani textu v pozadavku odesilaneho na server.
 *
 * @author unknown
 */
public class CarEvidenceSetupServletFilter implements Filter {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(CarEvidenceSetupServletFilter.class);

	@Autowired
	private CarEvidenceLocaleResolver carEvidenceLocaleResolver;

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException {

		// encoding
		request.setCharacterEncoding(CommonConstants.ENCODING_UTF8);

		// locale
		if (request.getParameter(CarEvidenceLocaleResolver.PARAM_LANG) != null) {
			carEvidenceLocaleResolver.setLocaleFromRequestParameter(request);
		}

		final HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		// actual uri
		request.setAttribute(RequestParamsConstants.REQ_PARAM_ACTUAL_URI, prepareActualURI(httpServletRequest));
		request.setAttribute(RequestParamsConstants.REQ_PARAM_LOCALE,
				carEvidenceLocaleResolver.resolveLocale(httpServletRequest));

		filterChain.doFilter(request, response);
	}

	private String prepareActualURI(final HttpServletRequest httpServletRequest) {
		return httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(final FilterConfig arg0) throws ServletException {
	}

}
