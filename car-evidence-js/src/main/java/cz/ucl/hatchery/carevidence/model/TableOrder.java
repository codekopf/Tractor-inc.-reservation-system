package cz.ucl.hatchery.carevidence.model;

public class TableOrder {

	private String orderColumn;
	private boolean ascending;

	/**
	 * Constructor.
	 */
	public TableOrder() {
		/* nothing to do */ }

	public TableOrder(final AbstractListFilter filterDTO) {
		this.orderColumn = filterDTO.getOrderType().getCode();
		this.ascending = filterDTO.getOrderDirection().isAscending();
	}

	public TableOrder(final String orderColumn, final boolean ascending) {
		this.orderColumn = orderColumn;
		this.ascending = ascending;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(final String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public boolean getAscending() {
		return ascending;
	}

	public void setAscending(final boolean ascending) {
		this.ascending = ascending;
	}

}