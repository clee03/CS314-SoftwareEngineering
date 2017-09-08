package edu.csu2017fa314.T17;
import edu.csu2017fa314.T17.Model.*;
import edu.csu2017fa314.T17.View.*;
import java.util.ArrayList;

public class TripCo {

  private String name = "";

  public String getName() {
    return name;
  }

  public String getMessage() {
    if (name == "") {
      return "Hello!";
    } else {
      return "Hello " + name + "!";
    }
  }

  public void setName(String name) {
    this.name = name;
  }

  public static void main(String[] args) {
    System.out.println("Welcome to TripCo");
    if (args.length == 0){
      System.out.println("Proper usage of this program is through " +
          "the commandline followed by a .csv file");
    }else if( args.length > 1){
      System.out.println("sorry this feature isn't implemented yet." +
          "please use only a single file for now");
    }
    ArrayList<Brewery> brewList = new ArrayList<Brewery>();
    ParseCSV parse = null;
    try {
      parse = new ParseCSV(args[0]);
    } catch (Exception e) {
      System.out.println("Error parsing the .csv file! Please input better data");
      e.printStackTrace();
    }
    brewList = parse.getBrewerys();
    writeJSON jWrite = new writeJSON();
    jWrite.formatJSON(brewList, args[0]);
    System.out.println("Json file successfully created!");

  }

}