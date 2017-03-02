package cz.ucl.hatchery.carevidence.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

/*
 * Slouzi pro zachytavani vyjimek:
 * - pri nenalezeni prislusneho controlleru -> 404
 * 
 */
@ControllerAdvice
public class CarEvidenceExceptionResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarEvidenceExceptionResolver.class);

	public static final String MSG_CAUGHT_EXCEPTION = "CAUGHT EXCEPTION ";

	public static final String VIEW_NAME_PAGE401 = "page401";
	public static final String VIEW_NAME_PAGE403 = "page403";
	public static final String VIEW_NAME_PAGE404 = "page404";
	public static final String VIEW_NAME_PAGE500 = "page500";

	@ExceptionHandler(NoSuchRequestHandlingMethodException.class)
	public ModelAndView handleException(final NoSuchRequestHandlingMethodException ex) {

		LOGGER.error(MSG_CAUGHT_EXCEPTION + ex.getMessage());

		final ModelAndView mav = new ModelAndView(VIEW_NAME_PAGE404);
		return mav;

	}

	@ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
	public ModelAndView handleException(final UnsatisfiedServletRequestParameterException ex) {

		LOGGER.error(MSG_CAUGHT_EXCEPTION + ex.getMessage());

		final ModelAndView mav = new ModelAndView(VIEW_NAME_PAGE404);
		return mav;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(final Exception ex) {

		LOGGER.error(MSG_CAUGHT_EXCEPTION + ex.getMessage(), ex);

		final ModelAndView mav = new ModelAndView(VIEW_NAME_PAGE500);
		return mav;

	}

}