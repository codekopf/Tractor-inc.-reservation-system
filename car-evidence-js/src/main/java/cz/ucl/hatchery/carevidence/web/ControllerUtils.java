package cz.ucl.hatchery.carevidence.web;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;

public class ControllerUtils {

	public static void setDateCustomEditorToBinder(final WebDataBinder binder) {

		final String datePattern = "dd.MM.yyyy";
		setDateCustomEditorToBinder(binder, datePattern);

	}

	public static void setDateCustomEditorToBinder(final WebDataBinder binder, final String datePattern) {

		// convert dates
		final SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

	}

	public static void setNumberCustomEditorToBinder(final WebDataBinder binder, final Locale locale) {
		setNumberCustomEditorToBinder(binder, 2, 2, locale);
	}

	public static void setNumberCustomEditorToBinder(final WebDataBinder binder, final Integer maximumFractionDigits,
			final Integer minimumFractionDigits, final Locale locale) {
		final CustomNumberEditor editor = makeCustomNumberEditor(maximumFractionDigits, minimumFractionDigits, locale);
		binder.registerCustomEditor(BigDecimal.class, editor);
	}

	public static void setNumberCustomEditorToBinder(final WebDataBinder binder, final String property,
			final Integer maximumFractionDigits, final Integer minimumFractionDigits, final Locale locale) {
		final CustomNumberEditor editor = makeCustomNumberEditor(maximumFractionDigits, minimumFractionDigits, locale);
		binder.registerCustomEditor(BigDecimal.class, property, editor);
	}

	private static CustomNumberEditor makeCustomNumberEditor(final Integer maximumFractionDigits,
			final Integer minimumFractionDigits, final Locale locale) {
		final DecimalFormat decimalFormat = new DecimalFormat();
		final DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
		decimalFormat.setDecimalFormatSymbols(symbols);
		decimalFormat.setMinimumFractionDigits(minimumFractionDigits);
		decimalFormat.setMaximumFractionDigits(maximumFractionDigits);

		final CustomNumberEditor res = new CustomNumberEditor(BigDecimal.class, decimalFormat, true);
		return res;
	}

	public static void setStringTrimmerEditorToBinder(final WebDataBinder binder) {
		// trim all string
		final StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);

	}

	public static void setBindingErrorProcessorToBinder(final WebDataBinder binder,
			final CarEvidenceBindingErrorProcessor bindingErrorProcessor, final String fieldTitleKeyPrefix) {

		// bindingErrorProcessor to avoid exception when for example dates
		// cannot be parsed
		bindingErrorProcessor.setFieldTitleKeyPrefix(fieldTitleKeyPrefix);
		binder.setBindingErrorProcessor(bindingErrorProcessor);

	}

	public static String buildUri(final Object... tokens) {
		return StringUtils.join(tokens, CommonConstants.SLASH);
	}

	public static String buildRedirectViewName(final Object... tokens) {

		final StringBuilder redirect = new StringBuilder(RequestParamsConstants.PREFIX_REDIRECT);
		redirect.append(StringUtils.join(tokens, CommonConstants.SLASH));

		return redirect.toString();

	}

}
