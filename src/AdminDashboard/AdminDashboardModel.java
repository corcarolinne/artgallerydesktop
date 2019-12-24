
package AdminDashboard;

import Entities.Art;
import Entities.Artist;
import Entities.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AdminDashboardModel {
    
    // properties
    String dbServer = "jdbc:mysql://database-1.cptrcvahtkfl.eu-west-1.rds.amazonaws.com/carol_2018250"; // type of database/port/database name
    String user = "carol_2018250";
    String password = "13 Hatnephfcfati_";
    
    // method to pick data for arts table, it returns an array 2d with data
    public String[][] showArtTable(){
        
        // declaring 2d array to hold data
         String[][] artData = null;
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            Statement stmt = connection.createStatement();
            // building the queries
            String checkNumOfRows = "SELECT * FROM carol_2018250.arts";
            String query = "SELECT arts.ArtID, arts.Title, arts.ArtistID, artists.FirstName, artists.LastName, arts.ArtType FROM carol_2018250.arts INNER JOIN carol_2018250.artists ON arts.ArtistID =  artists.ArtistID ORDER BY arts.ArtID;";

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
            artData= new String[numOfRows][6];

            int row = 0;
            
            // loop through result, while it's returns true (while there's lines in art table)
            while(result.next()) {
                // set artData array to receive each value from each row, for each corresponding column
                artData[row][0] = result.getString("ArtID");
                artData[row][1] = result.getString("Title");
                artData[row][2] = result.getString("ArtistID");
                artData[row][3] = result.getString("FirstName");
                artData[row][4] = result.getString("LastName");
                artData[row][5] = result.getString("ArtType");

               // increase row to populate the next row
                row++;
            }
            
            // close the result set, statement and coonection
            result.close();
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
    
    // method to create an art piece, receives an art object
    public void createArt(Art newArt){
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);
            
             // get a statement from the connection
            Statement stmt = connection.createStatement();
            
            // building the query
            String query = "INSERT INTO carol_2018250.arts (Title, ArtistID, ArtType) VALUES ('"+newArt.getTitle()+"','"+newArt.getArtistID()+"','"+newArt.getArtType()+"');";
            
            // Sending the query to the database
            stmt.execute(query) ;

            // closing statement and connection
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
    }
    // method to create an artist, receives an artist object
    public void createArtist(Artist newArtist){
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);
            
             // get a statement from the connection
            Statement stmt = connection.createStatement();
            
            // building the query
            String query = "INSERT INTO artists (FirstName, LastName, Address, Website) VALUES ('"+newArtist.getFirstName()+"','"+newArtist.getLastName()+"','"+newArtist.getAddress()+"','"+newArtist.getWebsite()+"');";
            
            // Sending the query to the database
            stmt.execute(query) ;

            // closing statement and connections
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
    }
    // method to update logged admin profile, receives 2 instances of user object
    public void updateProfile(User editUser, User loggedUser){
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            Statement stmt = connection.createStatement();
            // building the query
            String query = "UPDATE carol_2018250.users SET FirstName= '"+editUser.getFirstName()+"', LastName= '"+editUser.getLastName()+"', Username= '"+editUser.getUsername()+"', Pass= '"+editUser.getPassword()+"', Address= '"+editUser.getAddress()+"', Email= '"+editUser.getEmail()+"' WHERE UserID = '"+loggedUser.getUserID() +"';"; 
            
            stmt.execute(query);

            // closing connection and statement
            stmt.close();
            connection.close();
            // setting user objec to have the values from the other user that now has new values
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
    
     // method to update art piece, recives 2 instances of Art object
    public void updateArt(Art artToBeEdited, Art artSelected ){
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            Statement stmt = connection.createStatement();
            // building the query
            String query = "UPDATE carol_2018250.arts SET Title= '"+artToBeEdited.getTitle()+"', ArtistID= '"+artToBeEdited.getArtistID()+"', ArtType= '"+artToBeEdited.getArtType()+"' WHERE ArtID = '"+artSelected.getArtID() +"';"; 
            
            stmt.execute(query);

            // Calling the method in charge of closing the connections
            stmt.close();
            connection.close();
            
            // setting art object to have the values from the other art that now has new values
            artSelected.setTitle(artToBeEdited.getTitle());
            artSelected.setArtistID(artToBeEdited.getArtistID());
            artSelected.setArtType(artToBeEdited.getArtType());
            
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
  
    // method to update artist, recives 2 instances of Artist object
    public void updateArtist(Artist artistToBeEdited, Artist artistSelected ){
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            Statement stmt = connection.createStatement();
            // building the query
            String query = "UPDATE carol_2018250.artists SET FirstName= '"+artistToBeEdited.getFirstName()+"', LastName= '"+artistToBeEdited.getLastName()+"', Address= '"+artistToBeEdited.getAddress()+"', Website= '"+artistToBeEdited.getWebsite()+"' WHERE ArtistID = '"+artistSelected.getArtistID() +"';"; 
            
            stmt.execute(query);

            // Calling the method in charge of closing the connections
            stmt.close();
            connection.close();
            
            // setting artist object to have the values from the other artist that now has new values
            artistSelected.setFirstName(artistToBeEdited.getFirstName());
            artistSelected.setLastName(artistToBeEdited.getLastName());
            artistSelected.setAddress(artistToBeEdited.getAddress());
            artistSelected.setWebsite(artistToBeEdited.getWebsite());
            
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
  
    // method to pick data for admin table, it returns an array 2d with data
    public String[][] showAdminTable(){
        
        // declaring 2d array to hold data
         String[][] adminData = null;
         
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            Statement stmt = connection.createStatement();
            // building the queries
            String checkNumOfRows = "SELECT * FROM carol_2018250.users WHERE isAdmin = 1";
            String query = "SELECT users.UserID, users.FirstName, users.LastName, users.Username, users.Address, users.Email FROM carol_2018250.users WHERE isAdmin = 1";

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
            adminData= new String[numOfRows][6];

            int row = 0;
          
            // loop through result, while it's returns true (while there's lines in admin table)
            while(result.next()) {
                // set adminData array to receive each value from each row, for each corresponding column
                adminData[row][0] = result.getString("UserID");
                adminData[row][1] = result.getString("FirstName");
                adminData[row][2] = result.getString("LastName");
                adminData[row][3] = result.getString("Username");
                adminData[row][4] = result.getString("Address");
                adminData[row][5] = result.getString("Email");

               // increase row to try to populate the next row
                row++;
            }
            
            // closing resul, connection and statement
            result.close();
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
        return adminData;
    }

    // method to delete admin, it receives the user selected on table
    public void deleteAdmin(User userToBeDeleted) {
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            Statement stmt = connection.createStatement();
            // building the query
            String query = "DELETE FROM carol_2018250.users WHERE UserID = '"+userToBeDeleted.getUserID() +"';";
            
            stmt.execute(query);

            // closing connection and statement 
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
        
    }
    
    // method to delete art, it receives the art selected on table
    public void deleteArt(Art artToBeDeleted) {
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            Statement stmt = connection.createStatement();
            // building the query
            String query = "SELECT * FROM carol_2018250.favourites WHERE ArtID = '"+artToBeDeleted.getArtID() +"';";
            String query2 = "DELETE FROM carol_2018250.favourites WHERE ArtID = '"+artToBeDeleted.getArtID() +"';";
            String query3 = "DELETE FROM carol_2018250.arts WHERE ArtID = '"+artToBeDeleted.getArtID() +"';";
            
            // if there's an art piece in favourites table, delete this art piece in favourites and arts table
            // otherwise, delete only in arts table
            if(stmt.execute(query)) {
                stmt.execute(query2);
                stmt.execute(query3);
            }
            else {
                stmt.execute(query2);
            }

            // closing statement and connection
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
        
    }
    // method to delete artist, it receives the artist selected on table
    public void deleteArtist(Artist artistToBeDeleted) {
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            Statement stmt = connection.createStatement();
            // building the query
            String select_arts_from_artist = "SELECT * from carol_2018250.arts WHERE artistID='"+artistToBeDeleted.getArtistID() +"';";
            
            // sending the query to the database
            ResultSet artsFromArtist = stmt.executeQuery(select_arts_from_artist) ;
            
            // query to delete
            String query = "DELETE FROM carol_2018250.artists WHERE ArtistID = '"+artistToBeDeleted.getArtistID() +"';";
            
            // while there's a row in the result set
            while(artsFromArtist.next()) {
          
                // save the art id into a variable
                String artIDString = artsFromArtist.getString("ArtID");
                int artID = Integer.parseInt(artIDString);
                // make a new instance of Art
                Art artToBeDeleted =  new Art(artID);
                // call the method to delete art passing this instance 
                deleteArt(artToBeDeleted);
                
            } 
            stmt.execute(query) ;

            // closing statements and connection
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
        
    }
    
    // method to pick data from artist table, it returns a 2d array with the data
    public String[][] showArtistsTable(){
        
        // declaring 2d array to hold data
        String[][] artistsData = null;
         
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            Statement stmt = connection.createStatement();
            // building the queries
            String checkNumOfRows = "SELECT * FROM carol_2018250.artists";
            String query = "SELECT * FROM carol_2018250.artists";

            // sending the query to the database
            ResultSet resultNumOfRows = stmt.executeQuery(checkNumOfRows) ;
            
            int numOfRows = 0;       
            // while we have rows, or while next() returns true
            while(resultNumOfRows.next()) {
                // add one to rows
                numOfRows++;
            }

            ResultSet result = stmt.executeQuery(query) ;
            // set artistData number of rows and number of columns
            artistsData= new String[numOfRows][5];

            int row = 0; 
            // loop through result, while it's returns true (while there's lines in artist table)
            while(result.next()) {
                // set artistData array to receive each value from each row, for each corresponding column
                artistsData[row][0] = result.getString("ArtistID");
                artistsData[row][1] = result.getString("FirstName");
                artistsData[row][2] = result.getString("LastName");
                artistsData[row][3] = result.getString("Address");
                artistsData[row][4] = result.getString("Website");

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
        return artistsData;
    }
    
    // method to create a new admin, it receives an instance of User
    public void createAdmin(User newUser){
        try{
           
            Connection connection = DriverManager.getConnection(dbServer, user, password);
            
             // get a statement from the connection
            Statement stmt = connection.createStatement();
            
            // building the query
            String query = "INSERT INTO carol_2018250.users (FirstName, LastName, Username, Pass, IsAdmin, Address, Email) VALUES ('"+newUser.getFirstName()+"','"+newUser.getLastName()+"','"+newUser.getUsername()+"', '"+newUser.getPassword()+"', '1', '"+newUser.getAddress()+"', '"+newUser.getEmail()+"');";
            
            // Sending the query to the database
            stmt.execute(query) ;

            // closing statement and connection
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

    }
    
}
