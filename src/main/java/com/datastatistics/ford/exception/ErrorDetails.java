package com.datastatistics.ford.exception;

import java.util.Date;
/**
 * Error details class to return customized response to the client
 * when an Exception/Error is thrown by the application
 * @author Monika Tantia
 *
 */
public class ErrorDetails {

	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
