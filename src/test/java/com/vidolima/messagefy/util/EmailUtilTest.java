package com.vidolima.messagefy.util;

import com.vidolima.messagefy.exception.InvalidEmailException;
import java.util.Arrays;
import org.junit.Test;

public class EmailUtilTest {

  @Test
  public void test_isValid_withValidEmail_mustPass() {
    EmailUtil.isValid("marcosvidolin@vidolima.com");
  }

  @Test(expected = InvalidEmailException.class)
  public void test_isValid_withIncompleteEmail_mustThrowInvalidEmailException() {
    EmailUtil.isValid("marcosvidolin@vidolima");
  }

  @Test(expected = InvalidEmailException.class)
  public void test_isValid_withInvalidEmail_mustThrowInvalidEmailException() {
    EmailUtil.isValid("marcosvidolin.com");
  }

  @Test
  public void test_isValid_withValidEmailList_mustPass() {
    EmailUtil.isValid(Arrays.asList("marcosvidolin@vidolima.com", "vidolin@messagefy.com"));
  }

  @Test(expected = InvalidEmailException.class)
  public void test_isValid_withInvalidEmailList_mustThrowInvalidEmailException() {
    EmailUtil.isValid(Arrays.asList("marcos-vidolin@vidolima.com", "4vidolin@messagefy.com", "vidolin"));
  }

  @Test(expected = InvalidEmailException.class)
  public void test_isValid_withNullEmailList_mustThrowInvalidEmailException() {
    EmailUtil.isValid(Arrays.asList("marcos-vidolin@vidolima.com", "4vidolin@messagefy.com", null));
  }

  @Test(expected = InvalidEmailException.class)
  public void test_isValid_withEmptyStringEmailList_mustThrowInvalidEmailException() {
    EmailUtil.isValid(Arrays.asList("marcos-vidolin@vidolima.com", "4vidolin@messagefy.com", ""));
  }

}