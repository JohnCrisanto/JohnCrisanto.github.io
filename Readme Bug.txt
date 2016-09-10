Team
I have found a bug in the Database Helper class.  Since Felipe said that this class was running fine on his Linux machine, I did not want to upload a new Database Helper without Felipe and/or the Team verifying it on their machines first.  This may be related to the difference in Drivers and the connection method between Linux and Windows.

Explanation.
I get the following error message when either using the Database Test class or my Open defects servlet.
searchDefect Exception: SQLException: java.sql.SQLException: Operation not allowed after ResultSet closed 

I traced the error down to lines 236 and 237 in the Database Helper class.  This is where the statement object and the connection to MySQL are closed.   I did a little research and it appears that the Result  Set (rs) is also closed when either of these are closed.  I tested this and it didn’t matter which one was closed, I still got the error.  When I left these open the Database Helper worked as expected.  However, this is also not a good situation.  The statement object and the connection need to be closed so as not to cause interference with other methods or lost data.  
I will work on fixing the Database Helper class for my situation tonight and let you know in the morning.  So far, I have only been able to fix the problem by creating the array list of defect objects within the “try” block of the Execute Query class and having this as the return parameter. 
