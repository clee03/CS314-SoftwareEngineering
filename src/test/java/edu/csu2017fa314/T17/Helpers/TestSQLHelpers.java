package edu.csu2017fa314.T17.Helpers;

import org.junit.Test;

import static edu.csu2017fa314.T17.Helpers.SQLHelpers.collectCreds;

public class TestSQLHelpers {
  @Test
  public void testCollectCreds() {
    String[] creds = collectCreds();
    System.out.println("Username: [" + creds[0] + "]");
    System.out.println("Password: [" + creds[1] + "]");
  }
}
