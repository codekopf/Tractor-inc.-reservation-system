package cz.ucl.hatchery.carevidence.web;

import org.springframework.validation.BindingErrorProcessor;

public interface CarEvidenceBindingErrorProcessor extends BindingErrorProcessor {

	public void setFieldTitleKeyPrefix(String fieldTitleKeyPrefix);

}
