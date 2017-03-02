package cz.ucl.hatchery.carevidence.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@Scope("prototype")
public class CarEvidenceBindingErrorProcessorBean implements CarEvidenceBindingErrorProcessor {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(CarEvidenceBindingErrorProcessorBean.class);

	private String fieldTitleKeyPrefix = "";

	@Autowired
	private MessageSource messageSource;

	@Override
	public void setFieldTitleKeyPrefix(final String fieldTitleKeyPrefix) {
		this.fieldTitleKeyPrefix = fieldTitleKeyPrefix;
	}

	@Override
	public void processMissingFieldError(final String missingField, final BindingResult bindingResult) {
	}

	@Override
	public void processPropertyAccessException(final PropertyAccessException ex, final BindingResult bindingResult) {

		final String errorKey = fieldTitleKeyPrefix + ex.getPropertyName();

		bindingResult
				.reject("error.parse",
						new Object[] { new String((String) ex.getValue()),
								messageSource.getMessage(errorKey, null, CommonConstants.DEFAULT_LOCALE) },
						"error.parse");

	}

}
