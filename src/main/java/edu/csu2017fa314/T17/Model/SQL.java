package edu.csu2017fa314.T17.Model;

import java.sql.*;
import java.util.ArrayList;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SQL {
  //hardcoded variables for query
  String user = "cwesterm";
  String password = "830152296";
  String countSTR = "SELECT COUNT(1)";

  int lport = 33000;
  int rport = 3306;
  final String myDriver = "com.mysql.jdbc.Driver";
  final String dburl;

  String rhost = "faure.cs.colostate.edu";
  String host = "faure.cs.colostate.edu";
  String sshuser = "shawtm";
  String sshpassword = "DannyTheDog!996";

  Connection conn = null;
  Session session = null;

  public SQL() {
    dburl = "jdbc:mysql://localhost:" + lport + "/cs314";

  }

  public ArrayList<Brewery> searchByWord(String searchWord) {
    openNetwork();
    String dbQuery = "SELECT id,name,latitude,longitude " +
                     "FROM airports " +
                     "WHERE id like '%" + searchWord + "%' or name like '%" + searchWord + "%'";

    ResultSet rs = getRSFromDB( getStatement(), dbQuery );
    ArrayList<Brewery> data = rsToArrayList(rs);
    closeNetwork();
    return data;
  }

  private void openNetwork () {
    openTunnel();
    openDBConn();
  }
  private void closeNetwork () {
    closeDBConn();
    closeTunnel();
  }

  private void openTunnel () {
    JSch jsch = new JSch();

    java.util.Properties config = new java.util.Properties();
    config.put("StrictHostKeyChecking", "no");

    System.out.println("Connecting to Cheyenne...");
    try {
      this.session = jsch.getSession(sshuser, host, 22);
      session.setPassword(sshpassword);
      session.setConfig(config);
      session.connect();
      session.setPortForwardingL(lport, rhost, rport);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

  private void closeTunnel () {
    session.disconnect();
  }

  private void openDBConn () {
    try{
      System.out.println("Beginning connection with " + dburl);
      Class.forName(myDriver);
      conn = DriverManager.getConnection(this.dburl, this.user, this.password);
      System.out.println("Finished connecting to Cheyenne.");
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

  private void closeDBConn () {
    try {
      conn.close();
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }



  //method to create statement
  private Statement getStatement() {
    Statement statement;
    try {
      statement = conn.createStatement();
      return statement;
    } catch (SQLException e) {
      System.err.printf("Exception with statement in getStatement: ");
      System.out.println(e.getMessage());
    }
    return null;
  }

  //method to put ResultSet into an Arraylist<Brewery>
  private ArrayList<Brewery> rsToArrayList(ResultSet rs){
    ArrayList<Brewery> dests = new ArrayList<>();
    try {
      while (rs.next()) {
        dests.add(new Brewery(rs.getString("id"),
            rs.getString("name"),
            rs.getDouble("latitude"),
            rs.getDouble("longitude")));
      }
    } catch (SQLException se) {
      //Handle errors for JDBC
      se.printStackTrace();
    }
    return dests;
  }
  //method to create result set from query
  private ResultSet getRSFromDB(Statement statement, String query){
    ResultSet rs;
    try {
      try {// submit the full query
        rs = statement.executeQuery(query);
        return rs;
      }
      catch (SQLException e) {
        System.err.printf("Exception with rs getResultSetFromDB: ");
        System.out.println(e.getMessage());
      }
    }
    catch (Exception e) {
      System.err.printf("Exception in getResultSetFromBD: ");
      System.err.println(e.getMessage());
    }
    return null;
  }
}