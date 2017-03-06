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

import cz.ucl.hatchery.carevidence.model.ClientNewForm;
import cz.ucl.hatchery.carevidence.service.ClientManagerService;
import cz.ucl.hatchery.carevidence.web.CommonConstants;
import cz.ucl.hatchery.carevidence.web.ControllerUtils;
import cz.ucl.hatchery.carevidence.web.RequestParamsConstants;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = AddNewClientController.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AddNewClientController {

	public static final String BASE_URL = CommonConstants.SLASH + "clients/new";
	public final static String FORM_ATTRIBUTE_NAME = "clientNewForm";

	@Autowired
	private ClientManagerService clientManagerService;

	@Autowired
	private MessageSource messageSource;

	/*
	 * Provede ulozeni vozidla URI: /clients/new Method: POST
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void addNewClient(@RequestBody final ClientNewForm clientNewForm) {

		System.out.println("DEBUGG");
		// TODO Validate model
		clientManagerService.createNewClient(clientNewForm);

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
