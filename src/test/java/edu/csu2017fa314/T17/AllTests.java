package edu.csu2017fa314.T17;
import edu.csu2017fa314.T17.Helpers.TestSQLHelpers;
import edu.csu2017fa314.T17.Helpers.TestMathHelpers;
import edu.csu2017fa314.T17.Model.*;
import edu.csu2017fa314.T17.Server.TestServer;
import edu.csu2017fa314.T17.Server.TestTripCo;
import edu.csu2017fa314.T17.View.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.framework.JUnit4TestAdapter;

// This section declares all of the test classes in your program.
@RunWith(Suite.class)
@Suite.SuiteClasses({
    //Helpers
    TestSQLHelpers.class,
    TestMathHelpers.class,
    // Model
    TestDistance.class,
    TestLocation.class,
    TestShorterTrip.class,
    TestSQL.class,
    // TripCoServer
    // Server
      //TestServer.class,
      //TestTripCo.class,
    // View
    TestView.class,
    TestWriteJSON.class,
})

public class AllTests{
   //This can be empty if you are using an IDE that includes support for JUnit
   //such as Eclipse.  However, if you are using Java on the command line or
   //with a simpler IDE like JGrasp or jCreator, the following main() and suite()
   //might be helpful.

   // Execution begins at main().  In this test class, we will execute
   // a text test runner that will tell you if any of your tests fail.
   public static void main (String[] args){
      junit.textui.TestRunner.run (suite());
   }

   // The suite() method is helpful when using JUnit 3 Test Runners or Ant.
   public static junit.framework.Test suite(){
      return new JUnit4TestAdapter(AllTests.class);
   }
}


