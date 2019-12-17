
package CustomerDashboard;

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
    Connection connection = null;
    Statement stmt = null;
    
    //private boolean isAdmin;
    
    // starting the DB connection and putting in everything in the variables declared above
    public CustomerDashboardModel(){
        
        try{
            // get a connection with the database
            connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            stmt = connection.createStatement();

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
    
    // method to check if a user exists in the database
    public String[][] showArtTable(){
        
        // declaring result 2d array with data
        String[][] artData = null;
        
        
        try{
            // building the query
            String query = "SELECT * FROM carol_2018250.arts";
            String query2 = "SELECT * FROM carol_2018250.arts INNER JOIN artists ON arts.ArtistID = artist.ArtistID;";
           // String query = "SELECT * FROM users WHERE Username = '" + userLogged.getUsername() + "' AND Pass = '" + userLogged.getPassword() + "';";

            // Sending the query to the database
            ResultSet result = stmt.executeQuery(query) ;
            
            // If there is an entry in the database that satisfies
            // the username and password, then the login is successful
            // otherwise it fails
            login = result.next();
            
            // saving values from isAdmin column to check if user is an admin or not
            userLogged.isAdmin = result.getBoolean("IsAdmin");

            // Close the result set, statement and the connection
            result.close() ;

            // Calling the method in charge of closing the connections
            closings();
            
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

        // Retuning the login status
        return login;
    }
    
    // Separeating closing statements for better code structure
    private void closings(){
        try {            
            stmt.close();
            connection.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
