
package CustomerDashboard;

import Entities.User;
import Login.LoginModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomerDashboardModel {
    
    // properties
    String dbServer = "jdbc:mysql://localhost:3306/carol_2018250"; // type of database/port/database name
    String user = "root";
    String password = "13 Hatnephfcfati_";
    //private boolean isAdmin;
    
    public String[][] showArtTable(){
        
        // declaring result 2d array with data
        
         String[][] artData = null;
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            Statement stmt = connection.createStatement();
            // building the queries
            String checkNumOfRows = "SELECT * FROM carol_2018250.arts";
            String query = "SELECT arts.ArtID, arts.Title, artists.FirstName, artists.LastName, arts.ArtType FROM carol_2018250.arts INNER JOIN carol_2018250.artists ON arts.ArtistID =  artists.ArtistID ORDER BY arts.ArtID;";

            // sending the query to the database
            ResultSet resultNumOfRows = stmt.executeQuery(checkNumOfRows) ;
            
            int numOfRows = 0;       
            // while we have rows, or while next() returns true
            while(resultNumOfRows.next()) {
                // add one to rows
                numOfRows++;
            }

            ResultSet result = stmt.executeQuery(query) ;
            // set artData number of rows and number of columns
            artData= new String[numOfRows][4];

            int row = 0;
            // loop through result, while it's returns true (while there's lines in art table)
            while(result.next()) {
                // set artData array to receive each value from each row, for each corresponding column
                //artData1[row][0] = result.getString("ArtID");
                artData[row][0] = result.getString("Title");
                artData[row][1] = result.getString("FirstName");
                artData[row][2] = result.getString("LastName");
                artData[row][3] = result.getString("ArtType");

               // increase row to try to populate the next row
                row++;
            }
            
            // close the result set
            result.close();
            // calling the method in charge of closing the connections
            stmt.close();
            connection.close();     
        }
        catch( SQLException se ){
            System.out.println( "SQL Exception:" ) ;

            // Loop through the SQL Exceptions
            while( se != null ){
                System.out.println( "State  : " + se.getSQLState()  ) ;
                System.out.println( "Message: " + se.getMessage()   ) ;
                System.out.println( "Error  : " + se.getErrorCode() ) ;
                
                se = se.getNextException() ;
            }
        }
        catch( Exception e ){
                System.out.println( e ) ;
        }
        return artData;
    }
    
    
    public void updateProfile(User editUser, User loggedUser){
        
        // variable to define if the login is successful
        boolean update = false;
        //loggedUser = new User(String firstName, String lastName, String username, String email, String address, String password, boolean isAdmin);
        
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            Statement stmt = connection.createStatement();
            // building the query
            String query = "UPDATE carol_2018250.users SET FirstName= '"+editUser.getFirstName()+"', LastName= '"+editUser.getLastName()+"', Username= '"+editUser.getUsername()+"', Pass= '"+editUser.getPassword()+"', Address= '"+editUser.getAddress()+"', Email= '"+editUser.getEmail()+"' WHERE UserID = '"+loggedUser.getUserID() +"';"; 
            
            stmt.execute(query);

            // Calling the method in charge of closing the connections
            stmt.close();
            connection.close();
            
            loggedUser.setAddress(editUser.getAddress());
            loggedUser.setEmail(editUser.getEmail());
            loggedUser.setFirstName(editUser.getFirstName());
            loggedUser.setLastName(editUser.getLastName());
            loggedUser.setUsername(editUser.getUsername());
            loggedUser.setPassword(editUser.getPassword());
        }
        catch( SQLException se ){
            System.out.println( "SQL Exception:" ) ;

            // Loop through the SQL Exceptions
            while( se != null ){
                System.out.println( "State  : " + se.getSQLState()  ) ;
                System.out.println( "Message: " + se.getMessage()   ) ;
                System.out.println( "Error  : " + se.getErrorCode() ) ;

                se = se.getNextException() ;
            }
        }
        catch( Exception e ){
                System.out.println( e ) ;
        }
    }
}
