import java.io.*;
import java.util.*;
import java.sql.*;

public class DataBaseHelper {

    // Update the member bellow to match your database driver
    private final static String driverString = "org.mariadb.jdbc.Driver";
    // Update the member bellow to match your database connection socket, user and password
    private final static String connString = "jdbc:mariadb://localhost:3306/books?user=userdb&password=";

    // This method gets the highest defectName number
    // WARNING: THIS IS PRIVATE AND SHOULD NOT BE INVOKED FROM EXTARNAL CLASSES
    private static Integer getLastDefectId() {
        
        ArrayList<Defect> defectList = searchDefect("*","");
        
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
        Defect newDefect = new Defect();
        newDefect.Copy(defect);
        ResultSet rs = null;
       
        Integer newName = getLastDefectId() + 1;
        newDefect.setDefectName(newName);
        
        //execute Query to insert defect
        try
        {
            rs = executeQuery("INSERT INTO defects0 VALUES ('" + newDefect.getapplication() + "'," +
                                                               + newDefect.getDefectName() + "," +
                                                           "'" + newDefect.getSummary() + "'," +
                                                           "'" + newDefect.getStatus() + "'," +
                                                           "'" + newDefect.getPriority() + "'," +
                                                           "'" + newDefect.getAssignee() + "'," +
                                                           "'" + newDefect.getDescription() + "')"
                                                           );
            return newDefect;
        }
        catch (IOException e)
        {
            System.out.println("createDefect Exception: IOException: " + e);
        }
        finally
        {
            return null;
        }
    }

    // This method updates a defect
    // It returns the updated defect
    // or null if the defectName was not found
    public static Defect updateDefect(Defect defect)
    {
        ArrayList<Defect> defectList = searchDefect("*","");

        Defect modifiedDefect = new Defect();
        modifiedDefect.Copy(defect);
        ResultSet rs = null;
        
        Boolean defectFound = false;
        
        for (Defect item : defectList) {
            //System.out.println("updateDefect: comparing defect1 " + item.getDefectName() + item.getDefectName().getClass().getName());
            //System.out.println("updateDefect: comparing defect2 " + modifiedDefect.getDefectName() + modifiedDefect.getDefectName().getClass().getName());
            
            if (item.getDefectName().intValue() == modifiedDefect.getDefectName().intValue()) {
                defectList.set(defectList.indexOf(item), modifiedDefect);
                defectFound = true;
                //System.out.println("updateDefect: found defect " + modifiedDefect.getDefectName());
                break;
            }
        }

        if (defectFound) {
        
        
            try
            {
                rs = executeQuery("UPDATE defects0 SET application='" + modifiedDefect.getapplication() + "'," +
                                                               "summary='" + modifiedDefect.getSummary() + "'," +
                                                               "status='" + modifiedDefect.getStatus() + "'," +
                                                               "priority='" + modifiedDefect.getPriority() + "'," +
                                                               "assignee='" + modifiedDefect.getAssignee() + "'," +
                                                               "description='" + modifiedDefect.getDescription() + "'" +
                                                               " WHERE defectName='" + modifiedDefect.getDefectName() + "'");
                return modifiedDefect;
            }
            catch (IOException e)
            {
                System.out.println("updateDefect Exception: IOException: " + e);
                return null;
            } 
        } else {
            return null;
        }
    }
    
    // This method allows you to search the defect ArrayList
    // valid keyNames are defectName, application, *
    public static ArrayList<Defect> searchDefect(String keyName,String keyValue)
    {        
        //if (defectList == null) {
            //System.out.println("fillList called");
        //    fillList();
        //}
        ArrayList<Defect> defectList = new ArrayList<Defect>();
        
        ArrayList<Defect> newDefectList = new ArrayList<Defect>();
        Boolean match;
        
        ResultSet rs = null;
        
        try
        {
            rs = executeQuery("SELECT * FROM defects0");
        }
        catch (IOException e)
        {
            System.out.println("searchDefect Exception: IOException: " + e);
        }
        
        if (rs != null)
        {
            try
            {
                while (rs.next())
                {
                    Defect tempDefect = new Defect(rs.getString(1), rs.getString(6), rs.getInt(2),
		    	rs.getString(7), rs.getString(5), rs.getString(4), rs.getString(3));
                    defectList.add(tempDefect);
                }
            }
            catch (SQLException e)
            {
                System.out.println("searchDefect Exception: SQLException: " + e);
            }
        }
        
        
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

        return newDefectList;
    
    }
    
    public static HashMap<String, String> getAppMap()
    {
        HashMap<String, String> localAppMap = new HashMap<String, String>();
        ResultSet rs = null;
        
        //executeQuery("query to search app map");
        try
        {
            rs = executeQuery("SELECT * FROM applications0");
            while (rs.next())
            {
                localAppMap.put(rs.getString(1), rs.getString(2));
            }
            return localAppMap;
        }
        catch (SQLException e)
        {
            System.out.println("getAppMap SQLException: " + e);
            return null;
        }
        catch (IOException e)
        {
            System.out.println("getAppMap IOException: " + e);
            return null;
        }
    }

        
    // This is the wiring towards the database
    // This method is the interface with the JDBC driver
    private static ResultSet executeQuery(String sqlQuery)
        throws IOException
    {
        // Initialize the return rs with null
        ResultSet rs = null;
         
        try {
           
            // STEP 1: Load the Driver
            Class.forName(driverString);
            
            // STEP 2: Make a Connection to the Database
            // In linux Leap, the name of the mysql is mariadb
            Connection connection = DriverManager.getConnection(connString);
            
            // STEP 3: Create a Statement
            Statement statement = connection.createStatement();
            
            // STEP 4: Execute SQL Statements

            // Get table size
            //statement.execute("SELECT COUNT(*) FROM phrases");
            statement.execute(sqlQuery);
            
            rs = statement.getResultSet();
                                    
            if (rs == null) {
                // Read the sentence column
                //System.out.println("excuteQuery info: null result set for query " + sqlQuery);
            }
            
            // STEP 5: Close the statement and connection
            statement.close();
            connection.close();
            
            
        } catch (Exception error) {
            System.out.println("executeQuery Exception: An error has occurred during database execution: "+ error);
        }
        
        return rs;
    }
}