package edu.csu2017fa314.T17.Model;

import org.junit.Test;

import java.util.ArrayList;
import java.sql.*;
import static org.junit.Assert.assertEquals;

public class TestSQL {
  private SQL sql;

  @Test
  public void testSQL(){

    String searchWord = "denver";
    sql = new SQL();

    ArrayList<Brewery> retBrewsList = sql.searchByWord(searchWord);

    int totalNumRows = retBrewsList.size();
    assertEquals(6, totalNumRows);

    for(Brewery b : retBrewsList){
      System.out.println(b + ", ");
    }
  }
}
