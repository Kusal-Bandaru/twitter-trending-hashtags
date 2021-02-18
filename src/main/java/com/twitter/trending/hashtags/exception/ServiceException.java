package com.twitter.trending.hashtags.exception;

/**
 * Exception class to wrap exceptions in Service class
 * 
 * @author Kusal
 *
 */
public class ServiceException extends Exception{

	/**
	 * serialVersionUID to ensure proper serialization
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException(String msg) {
		super(msg);
	}
}
