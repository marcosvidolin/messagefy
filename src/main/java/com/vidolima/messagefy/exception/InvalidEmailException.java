package com.vidolima.messagefy.exception;

/**
 * If an invalid e-mail parameter is used, then a
 * <tt>InvalidEmailException</tt> will be thrown.
 *
 * @author marcosvidolin
 * @since Aug 3, 2019
 */
public class InvalidEmailException extends RuntimeException {

  public InvalidEmailException(String errorMessage) {
    super(errorMessage);
  }

}
