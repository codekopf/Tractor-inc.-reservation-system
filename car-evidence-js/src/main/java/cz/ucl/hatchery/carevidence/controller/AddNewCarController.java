package cz.ucl.hatchery.carevidence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.ucl.hatchery.carevidence.model.CarNewForm;
import cz.ucl.hatchery.carevidence.service.CarManagerService;
import cz.ucl.hatchery.carevidence.web.CommonConstants;
import cz.ucl.hatchery.carevidence.web.ControllerUtils;
import cz.ucl.hatchery.carevidence.web.RequestParamsConstants;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = AddNewCarController.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AddNewCarController {

	public static final String BASE_URL = CommonConstants.SLASH + "cars/new";
	public final static String FORM_ATTRIBUTE_NAME = "carNewForm";

	@Autowired
	private CarManagerService carManagerService;

	@Autowired
	private MessageSource messageSource;

	/*
	 * Provede ulozeni vozidla
	 * URI: /cars/new
	 * Method: POST
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void addNewCar(@RequestBody final CarNewForm carNewForm) {

		carManagerService.createNewCar(carNewForm);

		final String message = messageSource.getMessage(RequestParamsConstants.SAVE_NOTICE, new Object[] {},
				CommonConstants.CZECH_LOCALE);

		return;

	}

	/*
	 * **********************************************************************
	 * PRIVATE METHODS
	 * **********************************************************************
	 */

	@InitBinder(AddNewCarController.FORM_ATTRIBUTE_NAME)
	protected void initBinder(final WebDataBinder binder) {

		// BigDecimal custom binder
		ControllerUtils.setNumberCustomEditorToBinder(binder, CommonConstants.CZECH_LOCALE);

		// trim all string
		ControllerUtils.setStringTrimmerEditorToBinder(binder);
	}

}
