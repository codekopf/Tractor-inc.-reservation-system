/**
 * 
 */
package cz.ucl.hatchery.carevidence.model.enumeration;

/**
 * @author DZCJS9F
 *
 */
public enum OrderDirection {

	ASC(true, "a"), DESC(false, "d");

	private boolean ascending;
	private String code;

	public static final OrderDirection DEFAULT = ASC;

	private OrderDirection(final boolean ascending, final String code) {
		this.ascending = ascending;
		this.code = code;
	}

	public boolean isAscending() {
		return ascending;
	}

	public String getCode() {
		return code;
	}

	public static OrderDirection getByCode(final String code) {

		if (code == null || code.isEmpty()) {
			return DEFAULT;
		}

		if (code.equals(ASC.code)) {
			return ASC;
		}

		return DESC;

	}

}
