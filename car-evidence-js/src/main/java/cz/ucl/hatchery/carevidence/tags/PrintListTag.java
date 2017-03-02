package cz.ucl.hatchery.carevidence.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Print list of String characters as comma delimited values Examples: a, b, c,
 * d a, b, c, ... a, b, ... a|b|c...
 * 
 * Optional custom number of items to show (with tripple dots at the end)
 * Optional delimiter can be defined, default is comma-space character ', '
 * Optional end tag can be defined, default tripple dot character ', ...'
 * 
 * @author Marek Lobotka
 */
public class PrintListTag extends TagSupport {

	private static final long serialVersionUID = -2020355239147562578L;

	private static final String DEFAULT_DELIMITER = ", ";
	private static final String DEFAULT_END_TAG = "...";

	private List<String> value;
	private Long numberOfValuesToShow;
	private String delimiter = DEFAULT_DELIMITER;
	private String endTag = DEFAULT_END_TAG;

	@Override
	public int doStartTag() throws JspException {
		final StringBuilder sb = new StringBuilder();

		// Print empty if null
		if (value != null) {
			// Itterate through string list values
			for (int i = 0; i < value.size(); i++) {
				// If no numberOfValuesToShow constraint defined or current list
				// item with index lower than defined
				// number of values to show
				// add value to list
				if (numberOfValuesToShow == null || i < numberOfValuesToShow) {
					sb.append(value.get(i));
				}
				// if not last item, add comma delimiter
				if (i < value.size() - 1) {
					sb.append(delimiter);
				}
				// If numberOfValuesToShow constraint defined and values to show
				// exceeded
				// add tripple dot and finish loop
				if (numberOfValuesToShow != null && i >= numberOfValuesToShow) {
					sb.append(endTag);
					break;
				}
			}
		}

		try {
			pageContext.getOut().print(sb.toString());
		} catch (final IOException e) {
			throw new JspException(e);
		}

		return Tag.SKIP_BODY;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(final List<String> value) {
		this.value = value;
	}

	public Long getNumberOfValuesToShow() {
		return numberOfValuesToShow;
	}

	public void setNumberOfValuesToShow(final Long numberOfValuesToShow) {
		this.numberOfValuesToShow = numberOfValuesToShow;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(final String delimiter) {
		this.delimiter = delimiter;
	}

	public String getEndTag() {
		return endTag;
	}

	public void setEndTag(final String endTag) {
		this.endTag = endTag;
	}

}
