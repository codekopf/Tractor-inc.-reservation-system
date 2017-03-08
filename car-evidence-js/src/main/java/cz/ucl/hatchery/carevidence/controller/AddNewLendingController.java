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

import cz.ucl.hatchery.carevidence.model.LendingNewForm;
import cz.ucl.hatchery.carevidence.service.LendingManagerService;
import cz.ucl.hatchery.carevidence.web.CommonConstants;
import cz.ucl.hatchery.carevidence.web.ControllerUtils;
import cz.ucl.hatchery.carevidence.web.RequestParamsConstants;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = AddNewLendingController.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AddNewLendingController {

	public static final String BASE_URL = CommonConstants.SLASH + "lendings/new";
	public final static String FORM_ATTRIBUTE_NAME = "lendingNewForm";

	@Autowired
	private LendingManagerService lendingManagerService;

	@Autowired
	private MessageSource messageSource;

	/*
	 * Ulozenie vozidla
	 * URI: /clients/new
	 * Method: POST
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void addNewClient(@RequestBody final LendingNewForm lendingNewForm) {

		lendingManagerService.createNewLending(lendingNewForm);

		final String message = messageSource.getMessage(RequestParamsConstants.SAVE_NOTICE, new Object[] {},
				CommonConstants.CZECH_LOCALE);

		return;

	}

	/*
	 * **********************************************************************
	 * PRIVATE METHODS
	 * **********************************************************************
	 */

	@InitBinder(AddNewClientController.FORM_ATTRIBUTE_NAME)
	protected void initBinder(final WebDataBinder binder) {

		// BigDecimal custom binder
		ControllerUtils.setNumberCustomEditorToBinder(binder, CommonConstants.CZECH_LOCALE);

		// trim all string
		ControllerUtils.setStringTrimmerEditorToBinder(binder);
	}

}
