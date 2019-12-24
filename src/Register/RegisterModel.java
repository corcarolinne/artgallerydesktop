
package Register;

import Login.LoginModel;
import Entities.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegisterModel {
    
    // properties
    String dbServer = "jdbc:mysql://database-1.cptrcvahtkfl.eu-west-1.rds.amazonaws.com/carol_2018250"; // type of database/port/database name
    String user = "carol_2018250";
    String password = "13 Hatnephfcfati_";
    Connection connection = null;
    Statement stmt = null;
    
    // starting the DB connection and putting in everything in the variables declared above
    public RegisterModel(){
        
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
    public void register(User newUser){
        
        // variable to define if the login is successful
        boolean register = false;
        
        try{
            // building the query
            String query = "INSERT INTO users (FirstName, LastName, Username, Pass, IsAdmin, Address, Email) VALUES ('"+newUser.getFirstName()+"','"+newUser.getLastName()+"','"+newUser.getUsername()+"', '"+newUser.getPassword()+"', '0', '"+newUser.getAddress()+"', '"+newUser.getEmail()+"');";
            
            // Sending the query to the database
            stmt.execute(query) ;

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
        //return register;
    }
    
    // Separeating closing statements for better code structure
    private void closings(){
        try {            
            stmt.close();
            connection.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(RegisterModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
