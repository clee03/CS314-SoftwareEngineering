package edu.csu2017fa314.T17.Model;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class TestSQL {
  private SQL sql;

  @Before
  public void setUp() throws Exception{
    sql = new SQL();
  }

  @Test
  public void testSQL(){
    String searchWord = "denver";
    String[] codeArray = {"1TA5", "2CO4", "CND7", "CO39"};

    HashMap<String,String> retCodeName = sql.searchAllTablesByWord(searchWord);
    ArrayList<Location> totalColumnsList = sql.getAllDataWithID(codeArray);

    int codeNameTotals = retCodeName.size();
    assertEquals(30, codeNameTotals);

    int allColumns = totalColumnsList.size();
    assertEquals(4, allColumns);
  }
}
