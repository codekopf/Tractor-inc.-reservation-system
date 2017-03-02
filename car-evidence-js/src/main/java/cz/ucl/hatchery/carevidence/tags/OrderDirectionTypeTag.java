package cz.ucl.hatchery.carevidence.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import cz.ucl.hatchery.carevidence.model.TableOrder;
import cz.ucl.hatchery.carevidence.model.enumeration.OrderDirection;

public class OrderDirectionTypeTag extends TagSupport {

	private static final long serialVersionUID = -6185568327872625457L;

	private String orderColumn;
	private TableOrder tableOrderDTO;

	private String var;

	public void setOrderColumn(final String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public void setTableOrdering(final TableOrder tableOrderDTO) {
		this.tableOrderDTO = tableOrderDTO;
	}

	public void setVar(final String var) {
		this.var = var;
	}

	@Override
	public int doEndTag() throws JspException {

		final OrderDirection orderDirection = (tableOrderDTO.getOrderColumn().equals(orderColumn)
				&& !tableOrderDTO.getAscending()) || !tableOrderDTO.getOrderColumn().equals(orderColumn)
						? OrderDirection.ASC : OrderDirection.DESC;

		if (var != null && !var.isEmpty()) {
			pageContext.setAttribute(var, orderDirection.getCode());
			return Tag.SKIP_BODY;
		}

		try {
			pageContext.getOut().write(orderDirection.getCode());
		} catch (final IOException e) {
			throw new JspException(e);
		}

		return Tag.EVAL_PAGE;

	}

}
