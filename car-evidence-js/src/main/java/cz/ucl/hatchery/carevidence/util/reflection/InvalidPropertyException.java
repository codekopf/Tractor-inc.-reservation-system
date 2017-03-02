package cz.ucl.hatchery.carevidence.util.reflection;

/**
 * InvalidPropertyException
 * 
 * @author Marek Lobotka
 * 
 */
public class InvalidPropertyException extends RuntimeException {

	private static final long serialVersionUID = 3166827926791420065L;

	/**
	 * Constructs a new exception with <code>null</code> as its detail message.
	 */
	public InvalidPropertyException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message
	 *            Problem description message
	 */
	public InvalidPropertyException(final String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified cause.
	 * 
	 * @param cause
	 *            Cause that indicates why problem occurs
	 */
	public InvalidPropertyException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * 
	 * @param message
	 *            Problem description message
	 * @param cause
	 *            Cause that indicates why problem occurs
	 */
	public InvalidPropertyException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
