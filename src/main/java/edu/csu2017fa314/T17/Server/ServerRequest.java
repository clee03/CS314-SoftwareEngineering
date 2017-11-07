package edu.csu2017fa314.T17.Server;

/**
 * Created by sswensen on 10/1/17.
 * Edited by T17 on 10/17/17
 */

public class ServerRequest {
  private String query = "";
  private String code = "";

  public ServerRequest(String query, String code) {
    this.query = query;
    this.code = code;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Object toGson(){

    return "";
  }

  @Override
  public String toString() {
    return "Request{" +
        "query='" + query + '\'' +
        ", code='" + code + '\'' +
        '}';
  }
}