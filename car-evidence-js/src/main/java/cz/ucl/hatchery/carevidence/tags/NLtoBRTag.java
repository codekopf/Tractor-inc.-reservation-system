package cz.ucl.hatchery.carevidence.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class NLtoBRTag extends TagSupport {

	private static final String LINE_ENDING_REGEXP = "\\r\\n|\\r|\\n";
	private static final String HTML_BR = "<br />";

	private static final long serialVersionUID = -2020355239147562565L;

	private String text;

	public void setText(final String text) {
		this.text = text;
	}

	@Override
	public int doEndTag() throws JspException {

		text = text.replaceAll(LINE_ENDING_REGEXP, HTML_BR);

		try {
			pageContext.getOut().write(text);
		} catch (final IOException e) {
			throw new JspException(e);
		}

		return Tag.EVAL_PAGE;
	}

}
