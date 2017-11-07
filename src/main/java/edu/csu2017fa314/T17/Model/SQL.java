package edu.csu2017fa314.T17.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SQL {
  //hardcoded variables for query
  String user = "cwesterm";
  String password = "830152296";
  String countSTR = "SELECT COUNT(*)";

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

  /**
   * Method that opens a database connection and SSH connection
   */
  private void openNetwork () {
    openTunnel();
    openDBConn();
  }

  /**
   * Method that closes database connection and SSH connection
   */
  private void closeNetwork () {
    closeDBConn();
    closeTunnel();
  }

  /**
   * Method that creates a secure connection between the server
   * and a computer on the CSU network
   */
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
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * Method that closes the secure connection between the server
   * and a computer on the CSU network
   */
  private void closeTunnel () {
    session.disconnect();
  }

  /**
   * Method that creates a database connection
   */
  private void openDBConn () {
    try{
      System.out.println("Beginning connection with " + dburl);
      Class.forName(myDriver);
      conn = DriverManager.getConnection(this.dburl, this.user, this.password);
      System.out.println("Finished connecting to Cheyenne.");
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  /**
   * Method that closes the database connection
   */
  private void closeDBConn () {
    try {
      conn.close();
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * Method that queries airports table using a search word
   * @param searchWord       String used to query database
   * @return data            an ArrayList containing the code, name,
   *                         latitude, and longitude of each Brewery in query results
   */
  public ArrayList<Brewery> searchByWord(String searchWord) {
    openNetwork();
    String dbQuery = "SELECT code,name,latitude,longitude " +
        "FROM airports " +
        "WHERE code like '%" + searchWord + "%' or name like '%" + searchWord + "%'";

    ResultSet rs = getRSFromDB( getStatement(), dbQuery );
    ArrayList<Brewery> data = rsToArrayList(rs);
    closeNetwork();
    return data;
  }

  /**
   * Method that queries all database tables using a search word
   * @param searchWord       String used to query database
   * @return data            a Hash Map containing the code and name of each Brewery in query results
   */
  public HashMap<String, String> searchAllTablesByWord(String searchWord) {
    openNetwork();
    String dbQuery = "SELECT airports.code, airports.name " +
        "FROM continents " +
        "INNER JOIN countries ON countries.continent = continents.code " +
        "INNER JOIN regions ON regions.iso_country = countries.code " +
        "INNER JOIN airports ON airports.iso_region = regions.code " +
        "WHERE airports.name LIKE '%" + searchWord + "%' " +
        "OR municipality LIKE '%" + searchWord + "%' " +
        "OR regions.name LIKE '%" + searchWord + "%' " +
        "OR countries.name LIKE '%" + searchWord + "%' " +
        "OR continents.name LIKE '%" + searchWord + "%' limit 100";

    ResultSet rs = getRSFromDB( getStatement(), dbQuery );
    HashMap<String, String> data = rsToHashMap(rs);
    closeNetwork();
    return data;
  }

  /**
   * Method that queries airports table using selected IDs
   * @param codesArray       an Array of IDs used to query database
   * @return data            an ArrayList containing all columns in airports for each Brewery in query results
   */
  public ArrayList<Brewery> getAllDataWithID(String[] codesArray) {
    openNetwork();
    String codesString = "";
    for (int i = 0; i < codesArray.length; i++) {
      if (i == 0) {
        codesString = codesString + "'" + codesArray[i] + "'";
      } else {
        codesString = codesString + ",'" + codesArray[i] + "'";
      }
    }

    String dbQuery = "SELECT * FROM airports WHERE code IN (" + codesString + ") limit 100";
    ResultSet rs = getRSFromDB( getStatement(), dbQuery );
    ArrayList<Brewery> data = rsToArrayListAllData(rs);
    closeNetwork();
    return data;
  }

  /**
   * Method that puts the Result Set into a usable ArrayList
   * @param rs             Result Set from query
   * @return dests         an ArrayList containing the code, name,
   *                       latitude, and longitude of each Brewery in Result Set
   */
  private ArrayList<Brewery> rsToArrayList(ResultSet rs){
    ArrayList<Brewery> dests = new ArrayList<>();
    try {
      while (rs.next()) {
        dests.add(new Brewery(rs.getString("code"),
            rs.getString("name"),
            rs.getDouble("latitude"),
            rs.getDouble("longitude")));
      }
    } catch (Exception e){
      e.printStackTrace();
    }
    return dests;
  }

  /**
   * Method that puts the Result Set into a Hash Map
   * @param rs             Result Set from query
   * @return rsMap         a Hash Map containing the codes and names from query
   */
  private HashMap<String,String> rsToHashMap(ResultSet rs){
    HashMap<String, String> rsMap = new HashMap<>();
    try {
      ResultSetMetaData rsmd = rs.getMetaData();
      int colCount = rsmd.getColumnCount();
      while (rs.next()) {
        for(int i = 1; i <= colCount; i++){
          rsMap.put(rs.getString("code"), rs.getString("name"));
        }
      }
    } catch (Exception e){
      e.printStackTrace();
    }
    return rsMap;
  }

  /**
   * Method that puts the Result Set into an ArrayList of Brewery
   * @param rs             Result Set from query
   * @return queryDests    an ArrayList of Brewery containing all columns
   *                       in airports for each Brewery in Result Set
   */
  private ArrayList<Brewery> rsToArrayListAllData(ResultSet rs){
    ArrayList<Brewery> queryDests = new ArrayList<>();
    try {
      ResultSetMetaData rsmd = rs.getMetaData();
      int colCount = rsmd.getColumnCount();
      while (rs.next()) {
        HashMap destInfo = new HashMap();
        for (int i = 1; i < colCount; i++) {
          destInfo.put(rsmd.getColumnName(i), rs.getString(i));
        }
        Brewery dest = new Brewery(destInfo);
        queryDests.add(dest);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return queryDests;
  }

  /**
   * Method that creates a statement used to query database
   */
  private Statement getStatement() {
    Statement statement;
    try {
      statement = conn.createStatement();
      return statement;
    } catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Method that creates the Result Set from a query
   * @param statement       Statement used to query database
   * @param query           a String containing what all to query
   * @return rs             a Result Set of the query results
   */
  private ResultSet getRSFromDB(Statement statement, String query){
    ResultSet rs;
    try {// submit the full query
      rs = statement.executeQuery(query);
      return rs;
    } catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

}
