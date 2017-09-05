package edu.csu2017fa314.T17.View;

public class JSONWriter {
package edu.csu2017fa314.T17.View;

import edu.csu2017fa314.T17.Model.Brewery;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import java.util.ArrayList;


    public class JSONWriter {

        JSONObject brewObj = new JSONObject();

        //File IO in JAVA
        //Matching layout to spec
        public JSONWriter(ArrayList<String> breweryList) throws IOException{
            formatJSON();
            brewObj.put(brewsList);

            // try-with-resources statement based on post comment below :)
            try (FileWriter brewFile = new FileWriter("/Users/<username>/Documents/breweryJsonFile.txt")) {
                file.write(brewObj.toJSONString());
                System.out.println("Successfully Copied JSON Object to File...");
                //System.out.println("\nJSON Object: " + obj);
            }
        }

        public String formatJSON(){
            String brewEntry = "[\n";

            for(int i = 0; i < breweryList.size(); i++) {
                brewEntry += "{\n";
                brewEntry += "\"start\": " + breweryList[i].getID() + ",\n";
                brewEntry += "\"end\": " + breweryList[++i].getID() + ",\n";
                brewEntry += "\"distance\": " + breweryList[i].getdist() + "\n";
                brewEntry += "}\n";
            }


            brewEntry += "]";
            return brewEntry;
        }

        //ArrayList Brewery d
        //d.putsomethingin( BREW )
    }




}
