
package Login;

import Entities.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

// connection with database
public class LoginModel {
    
    // properties
    String dbServer = "jdbc:mysql://database-1.cptrcvahtkfl.eu-west-1.rds.amazonaws.com/carol_2018250"; // type of database/port/database name
    String user = "carol_2018250";
    String password = "13 Hatnephfcfati_";
    Connection connection = null;
    Statement stmt = null;
    
    
    // starting the DB connection and putting in everything in the variables declared above
    public LoginModel(){
        
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
    
    // method to check if a user exists in the database, receives an User instance
    public boolean login(User userLogged){
        
        // variable to define if the login is successful
        boolean login = false;
        
        try{
            // building the query
            String query = "SELECT * FROM users WHERE Username = '" + userLogged.getUsername() + "' AND Pass = '" + userLogged.getPassword() + "';";

            // Sending the query to the database
            ResultSet result = stmt.executeQuery(query) ;
            
            // If there is an entry in the database that satisfies
            // the username and password, then the login is successful
            // otherwise it fails
            login = result.next();
            
            // saving values in the User instance
            userLogged.setIsAdmin(result.getBoolean("IsAdmin"));
            userLogged.setUserID(result.getInt("UserID"));
            userLogged.setFirstName(result.getString("FirstName"));
            userLogged.setLastName(result.getString("LastName"));
            userLogged.setUsername(result.getString("Username"));
            userLogged.setAddress(result.getString("Address"));
            userLogged.setEmail(result.getString("Email"));
            userLogged.setPassword(result.getString("Pass"));

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
    
    // closings
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
