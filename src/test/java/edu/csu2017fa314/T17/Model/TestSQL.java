package edu.csu2017fa314.T17.Model;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class TestSQL {
  private SQL sql;

  @Test
  public void testSQL(){

    String searchWord = "denver";
    sql = new SQL();

    String[] codeArray = {"1TA5", "2CO4", "CND7", "CO39"};

    ArrayList<Brewery> retBrewsList = sql.searchByWord(searchWord);
    HashMap<String,String> retCodeName = sql.searchAllTablesByWord(searchWord);
    ArrayList<Brewery> totalColumnsList = sql.getAllDataWithID(codeArray);

    int totalNumRows = retBrewsList.size();
    assertEquals(7, totalNumRows);

    int codeNameTotals = retCodeName.size();
    assertEquals(30, codeNameTotals);

    int allColumns = totalColumnsList.size();
    assertEquals(4, allColumns);

/*
    for(String b : retCodeName.keySet()){
      System.out.println(b + ", ");
    }

    for(Brewery b : retBrewsList){
      System.out.println(b + ", ");
    }

    for(Brewery b : totalColumnsList){
      System.out.println(b + ", ");
    }
    */
  }
}
