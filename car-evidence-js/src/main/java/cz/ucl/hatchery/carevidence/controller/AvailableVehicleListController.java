
/**
 * 
 */
package cz.ucl.hatchery.carevidence.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.ucl.hatchery.carevidence.model.LendingDTO;
import cz.ucl.hatchery.carevidence.model.LendingFilter;
import cz.ucl.hatchery.carevidence.service.LendingManagerService;
import cz.ucl.hatchery.carevidence.web.CommonConstants;

/**
 * @author Andrej Buday
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = LendingListController.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AvailableVehicleListController {

	public static final String BASE_URL = CommonConstants.SLASH + "lendings";

	@Autowired
	private LendingManagerService lendingService;

	@RequestMapping(value = "/vehicles", method = RequestMethod.GET)
	// TODO - toto upratata, pre klienta je to ine
	// public ClientDTO[] findByFilter(@RequestParam(required = false) final Long id,
	// @RequestParam(required = false) final String type, @RequestParam(required = false) final String vin,
	// @RequestParam(required = false) final String state,
	// @RequestParam(required = false) final BigDecimal priceFrom,
	// @RequestParam(required = false) final BigDecimal priceTo,
	// @RequestParam(required = false) final Date acquiredFrom,
	// @RequestParam(required = false) final Date acquiredTo,
	// @RequestParam(required = false) final Date lastTechnicalCheckFrom,
	// @RequestParam(required = false) final Date lastTechnicalCheckTo) {
	public LendingDTO[] findAvailableVehicleByFilter(final LendingFilter lendingFilter) {

		// final CarFilter filter = new CarFilter(id, type, vin, state, priceFrom, priceTo, acquiredFrom, acquiredTo,
		// lastTechnicalCheckFrom, lastTechnicalCheckTo);
		final List<LendingDTO> result = lendingService.findAvailableVehicleByFilter(lendingFilter);

		return result.toArray(new LendingDTO[result.size()]);
	}

	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// public void updateOldLending(@RequestBody final LendingOldForm oldLandingForm) {
	// lendingService.saveOrUpdate(oldLendingForm);
	// return;
	// }

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
