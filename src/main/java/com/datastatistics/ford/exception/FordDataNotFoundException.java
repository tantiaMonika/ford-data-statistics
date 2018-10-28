package com.datastatistics.ford.exception;
/**
 * Customized exception class when requested data does not exist.
 * @author Monika Tantia
 *
 */
public class FordDataNotFoundException extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public FordDataNotFoundException(String exception) {
		super(exception);
	}

}
