package cz.ucl.hatchery.carevidence.model;

import cz.ucl.hatchery.carevidence.entity.ordering.ListOrderColumn;
import cz.ucl.hatchery.carevidence.model.enumeration.OrderDirection;

public abstract class AbstractListFilter {

	private Integer page;
	private Integer itemsPerPage;

	private String orderdir;
	private String ordercol;

	private ListOrderColumn orderType;
	private OrderDirection orderDirection;

	public AbstractListFilter(final Integer actualPage, final Integer itemsPerPage) {
		setPage(actualPage);
		this.itemsPerPage = itemsPerPage;
		setOrderDirection(OrderDirection.DEFAULT);
	}

	public AbstractListFilter(final Integer actualPage, final Integer itemsPerPage, final ListOrderColumn orderType,
			final OrderDirection orderDirection) {
		setPage(actualPage);
		this.itemsPerPage = itemsPerPage;
		this.orderType = orderType;
		this.orderDirection = orderDirection;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(final Integer page) {
		this.page = page;
		if (this.page == null || this.page < 1) {
			this.page = 1;
		}
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(final Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public ListOrderColumn getOrderType() {
		return orderType;
	}

	public void setOrderType(final ListOrderColumn orderType) {
		this.orderType = orderType;
	}

	public OrderDirection getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(final OrderDirection orderDirection) {
		this.orderDirection = orderDirection;
	}

	public String getOrderdir() {
		return orderdir;
	}

	public void setOrderdir(final String orderdir) {
		this.orderdir = orderdir;
		this.orderDirection = OrderDirection.getByCode(orderdir);
	}

	public String getOrdercol() {
		return ordercol;
	}

	public void setOrdercol(final String ordercol) {
		this.ordercol = ordercol;
	}

}