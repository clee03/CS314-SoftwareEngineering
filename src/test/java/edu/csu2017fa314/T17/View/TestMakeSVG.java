package edu.csu2017fa314.T17.View;

import edu.csu2017fa314.T17.Model.ParseCSV;
import org.junit.Before;
import org.junit.Test;

public class TestMakeSVG {
  private MakeSVG svgObj;
  private ParseCSV parseObj;

  @Before
  public void setUp() throws Exception {
    svgObj = new MakeSVG(1066.6073,783.0824);
    parseObj = new ParseCSV("data/brews.csv");
  }
  @Test
  public void outputMap () {
    svgObj.saveMap(parseObj.getBrewerys(),"data/brews.csv",
                    true, true);
  }
}
