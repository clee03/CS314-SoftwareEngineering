package edu.csu2017fa314.T17.Server;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestServerRequest {
  ServerRequest serverRequest;

  @Before
  public void setUp() throws Exception{
    serverRequest = new ServerRequest("brewery", "idkirk");
  }

  @Test
  public void testToString(){
    assertEquals("Request{query='brewery', code='idkirk'}",
        serverRequest.toString());
  }

  @Test
  public void testGetQuery(){
    String query = serverRequest.getQuery();
    assertEquals("brewery", query);
  }

  @Test
  public void testSetQuery(){
    serverRequest.setQuery("denver");
    String query = serverRequest.getQuery();
    assertEquals("denver", query);
  }

  @Test
  public void testGetCode(){
    String code = serverRequest.getCode();
    assertEquals("idkirk", code);
  }

  @Test
  public void testSetCode(){
    serverRequest.setCode("cwestern");
    String code = serverRequest.getCode();
    assertEquals("cwestern", code);
  }
}
