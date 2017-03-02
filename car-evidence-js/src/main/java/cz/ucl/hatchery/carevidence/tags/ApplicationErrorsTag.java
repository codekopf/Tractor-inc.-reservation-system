package cz.ucl.hatchery.carevidence.tags;

import java.util.Arrays;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.tags.form.ErrorsTag;
import org.springframework.web.servlet.tags.form.TagWriter;

public class ApplicationErrorsTag extends ErrorsTag {

	private static final long serialVersionUID = -9221820067452252031L;

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationErrorsTag.class);

	@Override
	protected void renderDefaultContent(final TagWriter tagWriter) throws JspException {

		Arrays.sort(getBindStatus().getErrorMessages());

		super.renderDefaultContent(tagWriter);

	}

}
