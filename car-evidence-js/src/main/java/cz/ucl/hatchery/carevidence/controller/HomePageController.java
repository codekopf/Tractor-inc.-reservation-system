package cz.ucl.hatchery.carevidence.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cz.ucl.hatchery.carevidence.web.CommonConstants;
import cz.ucl.hatchery.carevidence.web.TimeDurationFormatter;

@Controller
@RequestMapping(value = CommonConstants.SLASH)
public class HomePageController {

	private static final String VIEW_NAME_HOMEPAGE = "homepage";

	@Autowired
	private ApplicationContext applicationContext;

	/*
	 * Vraci homepage aplikace URI: / Method: GET
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView homepage() {

		final ModelAndView mv = new ModelAndView(VIEW_NAME_HOMEPAGE);

		setupStartupTime(mv);

		return mv;

	}

	/*
	 * **********************************************************************
	 * PRIVATE METHODS
	 * **********************************************************************
	 */
	private void setupStartupTime(final ModelAndView mv) {

		final int secondsOfRun = (int) ((Calendar.getInstance().getTimeInMillis() - applicationContext.getStartupDate())
				/ 1000);

		mv.addObject("applicationStart", new Date(applicationContext.getStartupDate()));
		mv.addObject("runningDays", new TimeDurationFormatter(secondsOfRun).getDuration());

	}

}
