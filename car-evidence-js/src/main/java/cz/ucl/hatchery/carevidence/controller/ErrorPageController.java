package cz.ucl.hatchery.carevidence.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = ErrorPageController.BASE_URL)
public class ErrorPageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorPageController.class);

	private static final String SUB_URI_ERROR_CODE = "/{errorCode:\\d+}";
	public static final String BASE_URL = ControllerConstants.URI_ERROR;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView get(final HttpServletResponse response) {

		final int status = response.getStatus();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caught http error " + status);
		}

		return new ModelAndView(getViewName(status));
	}

	@RequestMapping(value = SUB_URI_ERROR_CODE, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showWithErrorCode(final HttpServletResponse response,
			@PathVariable("errorCode") final int status) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("caught http error " + status);
		}

		return new ModelAndView(getViewName(status));

	}

	/*
	 * **********************************************************************
	 * PRIVATE METHODS
	 * **********************************************************************
	 */
	private String getViewName(final int status) {
		String viewName;
		switch (status) {

		case HttpServletResponse.SC_UNAUTHORIZED:
			viewName = ControllerConstants.VIEW_NAME_PAGE401;
			break;

		case HttpServletResponse.SC_FORBIDDEN:
			viewName = ControllerConstants.VIEW_NAME_PAGE403;
			break;

		case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
			viewName = ControllerConstants.VIEW_NAME_PAGE500;
			break;

		case HttpServletResponse.SC_NOT_FOUND:
			viewName = ControllerConstants.VIEW_NAME_PAGE404;
			break;

		default:
			viewName = ControllerConstants.VIEW_NAME_ERROR;

		}
		return viewName;
	}

}
