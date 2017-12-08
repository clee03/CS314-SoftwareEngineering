package edu.csu2017fa314.T17.Server;

import org.junit.Before;
import org.junit.Test;

import static edu.csu2017fa314.T17.Helpers.SQLHelpers.collectCreds;

public class TestServer {
  Server svr;

  @Before
  public void setUp() {
    String[] creds = collectCreds();
    svr = new Server(creds[0], creds[1]);
  }

  @Test
  public void testSearch() throws Exception {

  }

}
