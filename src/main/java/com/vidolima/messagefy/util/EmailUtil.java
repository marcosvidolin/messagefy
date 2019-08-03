package com.vidolima.messagefy.util;

import com.vidolima.messagefy.exception.InvalidEmailException;
import java.util.Collection;

/**
 * E-mail util class.
 *
 * @author marcosvidolin
 * @since Aug 3, 2019
 */
public class EmailUtil {

  private static final String VALID_EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

  /**
   * Checks whether the e-mail is valid.
   * @param email
   * @throws InvalidEmailException
   */
  public static void isValid(final String email) {
    if (email == null || !email.matches(VALID_EMAIL_REGEX)) {
      throw new InvalidEmailException("Invalid e-mail: " + email);
    }
  }

  /**
   * Checks whether all e-mail are valid.
   * @param emails
   * @throws InvalidEmailException
   */
  public static void isValid(Collection<String> emails) {
    for (String email : emails) {
      isValid(email);
    }
  }

}
