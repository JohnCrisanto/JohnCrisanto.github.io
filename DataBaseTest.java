import java.util.*;

public class DataBaseTest {
    
    public static void main(String[] args) {
    
        ArrayList<Defect> myList = new ArrayList<Defect>();
    
        // ============== Database API example 1 ===================
        // Database API example: search by keyName, keyValue
        // valid keyNames are defectName, application, *
        // In this example we fetch ALL defects
        
        myList = DataBaseHelper.searchDefect("*","");
        
        System.out.println("\nWe get here 2");
    
        for (Defect myItem : myList) {
            System.out.println(myItem);
        }
        
                
        // ============== Database API example 2 ===================
        // Here is an example of inserting a defect
        // defect.defectName will be ignored and automatically generated from database

        Defect defect = new Defect();
        defect.Copy(myList.get(2));
        
        defect.setDefectName(0); // This field will be ignored when creating a defect

        DataBaseHelper.createDefect(defect);
        DataBaseHelper.createDefect(defect);
        DataBaseHelper.createDefect(defect);
        
        myList = DataBaseHelper.searchDefect("*",""); // We fetch all defects again
        
        System.out.println("\nWe get here 3");
    
        for (Defect myItem : myList) {
            System.out.println(myItem);
        }
        
        // ============== Database API example 3 ===================
        // Here is an example of updating a defect
        // defect.defectName will be searched in database

        defect.Copy(myList.get(1));
        defect.setPriority("low");
        
        if (DataBaseHelper.updateDefect(defect) == null)
        {
            System.out.println("Update error: " + defect);
        }
        
        myList = DataBaseHelper.searchDefect("*",""); // We fetch all defects again

        System.out.println("\nWe get here 4");
    
        for (Defect myItem : myList) {
            System.out.println(myItem);
        }
        
    }
    
}