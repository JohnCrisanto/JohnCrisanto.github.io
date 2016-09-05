import java.io.*;
import java.util.*;
//import java.sql.*;

public class DataBaseHelper {

    // ============== PLEASE IGNORE ===============
    // This members below are just for dev purposes
    // They will be deleted when the database backend is fully implemented
    private static ArrayList<Defect> defectList = null;
        
    private static void fillList ()
    {
        defectList = new ArrayList<Defect>();
        
        //System.out.println("We get here 1");
        
        defectList.add(new Defect("ProjectA", "felipe@erv.com.ca", 1000, "this is a summary",  "high", "open", "this is a description"));
        defectList.add(new Defect("ProjectA", "john@tts.com", 1001, "this is a summary",  "high", "open", "this is a description"));
        defectList.add(new Defect("ProjectB", "samuel@ggw.com", 1005, "this is a summary",  "high", "open", "this is a description"));
    }
    // =============================================

    // This method gets the highest defectName number
    private static Integer getLastDefectId() {
        
        //ArrayList<Defect> defectList = searchDefects("*","");
        
        Integer lastId = 0;
        
        for (Defect item : defectList) {
            if (item.getDefectName() > lastId) {
                lastId = item.defectName;
            }
        }
        
        return lastId;
    }

    // This method creates a defect
    // It returns the created defect
    public static Defect createDefect(Defect defect)
    {
        //ArrayList<Defect> defectList = searchDefects("*","");
        
        Defect newDefect = new Defect();
        newDefect.Copy(defect);
        
        Integer newName = getLastDefectId() + 1;
        newDefect.setDefectName(newName);
        
        defectList.add(newDefect);
        //executeQuery("query to insert defect");

        return newDefect;
    }

    // This method updates a defect
    // It returns the updated defect
    // or null if the defectName was not found
    public static Defect updateDefect(Defect defect)
    {
        //ArrayList<Defect> defectList = searchDefects("*","");

        Defect modifiedDefect = new Defect();
        modifiedDefect.Copy(defect);
        
        Boolean defectFound = false;
        
        for (Defect item : defectList) {
            if (item.getDefectName() == modifiedDefect.getDefectName()) {
                defectList.set(defectList.indexOf(item), modifiedDefect);
                defectFound = true;
            }
        }

        if (defectFound) {
            return modifiedDefect;
        } else {
            return null;
        }
        
        //executeQuery("query to update defect");
    }
    
    // This method allows you to search the defect ArrayList
    // valid keyNames are defectName, application, *
    public static ArrayList<Defect> searchDefect(String keyName,String keyValue)
    {        
        if (defectList == null) {
            //System.out.println("fillList called");
            fillList();
        }
        
        ArrayList<Defect> newDefectList = new ArrayList<Defect>();
        Boolean match;
        
        for (Defect item : defectList) {
            match = false;
            switch (keyName) {
                case "defectName" : if (Integer.parseInt(keyValue) == item.getDefectName()) { match = true; } break;
                case "application" : if (keyValue == item.getapplication()) { match = true; } break;
                case "*" : match = true; break;
                default : System.out.println("Invalid keyName");
            }
            if (match)
            {
                Defect holder = new Defect();
                holder.Copy(item);
                newDefectList.add(holder);
            }
        }
        //executeQuery("query to search defect");
        //parse result and build defectList
        
        return newDefectList;
    
    }
        
    // This is the wiring towards the database
    // It will be implemented later
    private static void executeQuery(String sqlQuery)
        throws IOException
    {
        // Need to implement database beckend
    }
}