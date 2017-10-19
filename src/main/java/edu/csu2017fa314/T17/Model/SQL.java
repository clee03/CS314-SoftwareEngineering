package edu.csu2017fa314.T17.Model;

import java.sql.*;
import java.util.ArrayList;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SQL {
  //hardcoded variables for query
  String user = "jdorcey";
  String password = "829427264";
  String columnsSTR = "SELECT id,name,latitude,longitude ";
  String tableSTR = "FROM airports";
  String countSTR = "SELECT COUNT(1)";
  //variable query will use
  String whereSTR = "";
  sqlType connectionType;

  public ResultSet retSet = null;
  public Connection conn = null;
  public Statement st = null;

  final static String myDriver = "com.mysql.jdbc.Driver";
  final static String dburl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
  final static String localurl = "jdbc:mysql://localhost";

  int lport=22;
  String rhost="cheyenne.cs.colostate.edu";
  String host="cheyenne.cs.colostate.edu";
  int rport=3306;
  String sshuser="shawtm";
  String sshpassword="DannyTheDog!996";
  Session session= null;


  public ArrayList<Brewery> destList;
  int numRetSet = 0;
  public enum sqlType {local, remote};

  //constructor to query DB using a search word
  public SQL(String searchWord, sqlType type) {
    //query string
    this.whereSTR = " WHERE id like '%" + searchWord + "%' or name like '%" + searchWord + "%'";

    // local or remote url
    this.connectionType = type;

    //connection and statement for getResultSetFromDB
    this.conn = getDBConn();
    this.st = getStatement(this.conn);

    //returns a ResultSet for the query
    this.retSet = getResultSetFromDB(this.conn, this.st, this.columnsSTR, this.tableSTR, this.whereSTR);

    //returns an ArrayList<Brewery> with data from ResultSet
    this.destList = retSetToArrayList(this.retSet);

    //create a new connection and statement to get total row count of table from query
    this.conn = getDBConn();
    this.st = getStatement(this.conn);
    this.numRetSet = recordsCount(this.conn, this.st, this.tableSTR, this.whereSTR);
  }

  //method to connect to database
  public Connection getDBConn() {
    Connection conn = null;
    try{
       java.util.Properties config = new java.util.Properties();
      config.put("StrictHostKeyChecking", "no");
      JSch jsch = new JSch();
      session=jsch.getSession(sshuser, host, 22);
      session.setPassword(sshpassword);
      session.setConfig(config);
      session.connect();
      Class.forName(myDriver);
      if (connectionType == sqlType.remote)
        conn = DriverManager.getConnection(this.dburl, this.user, this.password);
      if (connectionType == sqlType.local)
        conn = DriverManager.getConnection(this.localurl, this.user, this.password);
    }catch(Exception e){
      e.printStackTrace();
    } finally{
      if(session !=null && session.isConnected()){
        session.disconnect();
      }
    }
    return conn;
  }

  //method to create statement
  public Statement getStatement(Connection dbConn) {
    Statement statement = null;
    try {
      statement = dbConn.createStatement();
    } catch (SQLException e) {
      System.err.printf("Exception with statement in getStatement: ");
      System.out.println(e.getMessage());
    }
    return statement;
  }

  //method to create result set from query
  public ResultSet getResultSetFromDB(Connection conn, Statement st, String columns, String tab, String where) {
    ResultSet rs = null;
    try {
      Connection dbConn = conn;
      Statement statement = st;
      try {// submit the full query
        rs = statement.executeQuery(columns + tab + where);
      } catch (SQLException e) {
        System.err.printf("Exception with rs getResultSetFromDB: ");
        System.out.println(e.getMessage());
      }
      } catch (Exception e) {
        System.err.printf("Exception in getResultSetFromBD: ");
        System.err.println(e.getMessage());
      }
    return rs;
  }

  //method to get total row count in table for results that match query
  public int recordsCount(Connection conn, Statement st, String tab, String where) {
    int numResults = 0;
    try {
      Connection dbConn = conn;
      Statement statement = st;
      try {
        ResultSet rs = statement.executeQuery(this.countSTR + tab + where);
        try {
          rs.next();
          numResults = rs.getInt(1);
        } catch (SQLException e) {
          System.err.printf("Exception with rs.next in recordsCount: ");
          System.out.println(e.getMessage());
        }
      } catch (Exception e) {
        System.err.printf("Exception with rs in recordsCount: ");
        System.err.println(e.getMessage());
      }
    } catch (Exception e) {
        System.err.printf("Exception in recordCount: ");
        System.err.println(e.getMessage());
      }
    return numResults;
  }

  //method to put ResultSet into an Arraylist<Brewery>
  public ArrayList<Brewery> retSetToArrayList(ResultSet rs){
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

  //method to close DB Connection, Statement, and ResultSet
  public void closeDBConnections(Connection conn, Statement st, ResultSet rs) {
    try {
      rs.close();
      st.close();
      conn.close();
      System.out.println("Successfully Closed Connection, Statement, and Result Set.");
    } catch (SQLException e) {
      System.err.printf("Exception in closeDBConnections: ");
      System.out.println(e.getMessage());
    }
  }
}