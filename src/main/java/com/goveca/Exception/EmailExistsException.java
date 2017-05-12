package com.goveca.Exception;

@SuppressWarnings("serial")
public class EmailExistsException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1586957965590623202L;

	public EmailExistsException(final String message) {
		super(message);
	}

}
