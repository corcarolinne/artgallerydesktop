
package CustomerDashboard;

import Entities.Art;
import Entities.User;
import Login.LoginModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomerDashboardModel {
    
    // properties
    String dbServer = "jdbc:mysql://database-1.cptrcvahtkfl.eu-west-1.rds.amazonaws.com/carol_2018250"; // type of database/port/database name
    String user = "carol_2018250";
    String password = "13 Hatnephfcfati_";
    
    // method to pick data for arts table, it returns an array 2d with data
    // it receives the input from search and the filter selected
    public String[][] showArtTable(String searchInput, String selectedFilter){
        
        // declaring result 2d array with data
        String[][] artData = null;
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);

            // get a statement from the connection
            Statement stmt = connection.createStatement();
            
            // if there's no search just display normal art table
            if(searchInput.isEmpty()) {
                // building the queries
                String checkNumOfRows = "SELECT * FROM carol_2018250.arts";
                String query = "SELECT arts.ArtID, arts.Title, artists.ArtistID, artists.FirstName, artists.LastName, arts.ArtType FROM carol_2018250.arts INNER JOIN carol_2018250.artists ON arts.ArtistID =  artists.ArtistID ORDER BY arts.ArtID;";

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
                
                
             // or if there's any search and filter selected
            } else {
                
                // if the filter selected is Artist
                if(selectedFilter == "Artist") {
                
                    String artistID = "";
                    // query the artist table looking for artist name that matches user input
                    String queryInArtists = "SELECT * FROM carol_2018250.artists WHERE FirstName LIKE '%"+searchInput+"%' OR LastName LIKE '%"+searchInput+"%';";
                    
                    int row = 0;
                    // sending the query to the database
                    ResultSet searchArtists = stmt.executeQuery(queryInArtists);
                    
                    // while there's a result in our query
                    while(searchArtists.next()) { 
                        // save id of the artist
                        artistID = searchArtists.getString("ArtistID"); 
                        row++;
                    }
                    // query with art pieces of this artist
                    String queryInArts = "SELECT arts.ArtID, arts.Title, arts.ArtistID, artists.FirstName, artists.LastName, arts.ArtType FROM carol_2018250.arts INNER JOIN carol_2018250.artists ON arts.ArtistID =  artists.ArtistID WHERE artists.ArtistID='"+artistID+"' ORDER BY arts.ArtID;"; 
 
                    // execute query
                    ResultSet resultToCheckSize = stmt.executeQuery(queryInArts);
                    
                     int numOfRows = 0;       
                    // while we have rows, or while next() returns true
                    while(resultToCheckSize.next()) {
                        // add one to rows
                        numOfRows++;
                    }
                    System.out.println(numOfRows);
                    
                    // set artData number of rows and number of columns
                    artData= new String[numOfRows][6];
                    
                    // sending the query to the database
                    ResultSet searchResult = stmt.executeQuery(queryInArts);
                    
                    
                    int row2 = 0;
                    // while there's a result 
                    while(searchResult.next()) {
                        
                        // set artData array to receive each value from each row, for each corresponding column
                        artData[row2][0] = searchResult.getString("ArtID");
                        artData[row2][1] = searchResult.getString("Title");
                        artData[row2][2] = searchResult.getString("ArtistID");
                        artData[row2][3] = searchResult.getString("FirstName");
                        artData[row2][4] = searchResult.getString("LastName");
                        artData[row2][5] = searchResult.getString("ArtType");
                        
                        row2++;

                    }

                // if the filter is ArtType or Title
                } else {
                    // query to check size
                    String queryToCheckSize = "SELECT arts.ArtID, arts.Title, arts.ArtistID, artists.FirstName, artists.LastName, arts.ArtType FROM carol_2018250.arts INNER JOIN carol_2018250.artists ON arts.ArtistID =  artists.ArtistID WHERE "+selectedFilter+" LIKE '%"+searchInput+"%' ORDER BY arts.ArtID;"; 

                    // query with arts
                     String query = "SELECT arts.ArtID, arts.Title, arts.ArtistID, artists.FirstName, artists.LastName, arts.ArtType FROM carol_2018250.arts INNER JOIN carol_2018250.artists ON arts.ArtistID =  artists.ArtistID WHERE "+selectedFilter+" LIKE '%"+searchInput+"%' ORDER BY arts.ArtID;"; 

                    ResultSet resultToCheckSize = stmt.executeQuery(query);
                    
                    int numOfRows = 0;       
                    // while we have rows, or while next() returns true
                    while(resultToCheckSize.next()) {
                        // add one to rows
                        numOfRows++;
                    }

                    // set artData number of rows and number of columns
                    artData= new String[numOfRows][6];
                    
                    // sending the query to the database
                    ResultSet searchResult = stmt.executeQuery(query);
                    int row = 0;

                    // while there's a result
                    while(searchResult.next()) {
                        
                       
                        // set artData array to receive each value from each row, for each corresponding column
                        artData[row][0] = searchResult.getString("ArtID");
                        artData[row][1] = searchResult.getString("Title");
                        artData[row][2] = searchResult.getString("ArtistID");
                        artData[row][3] = searchResult.getString("FirstName");
                        artData[row][4] = searchResult.getString("LastName");
                        artData[row][5] = searchResult.getString("ArtType");


                        row++;

                    }
                }
              // closings
              stmt.close();
              connection.close();
            }
                 
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
        return artData;
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

            // closing
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

    // method to create favourite, receives an instance of Art and an instance of User
     public void createFavourite(Art newFavouriteArt, User userLogged){
        
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);
            
             // get a statement from the connection
            Statement stmt = connection.createStatement();
            
            // building the query
            String query = "INSERT INTO carol_2018250.favourites (ArtID, UserID) VALUES ('"+newFavouriteArt.getArtID()+"','"+userLogged.getUserID()+"');";
            
            // Sending the query to the database
            stmt.execute(query) ;

            // Calling the method in charge of closing the connections
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
     // method to pick data for Favourites table, receives an instance of User
     public String[][] showFavourites(User userLogged){
        
        // array to hold data for favourites table
        String[][] favouritesData = null;
        
        
        try{
            Connection connection = DriverManager.getConnection(dbServer, user, password);
            
             // get a statement from the connection
            Statement stmt = connection.createStatement();
            
            // queries
            String query = "SELECT * FROM carol_2018250.favourites WHERE UserID='"+userLogged.getUserID()+"';";
            String queryInArts = "SELECT arts.ArtID, arts.Title, arts.ArtistID, arts.ArtType FROM carol_2018250.arts INNER JOIN carol_2018250.favourites ON arts.ArtID =  favourites.ArtID WHERE favourites.UserID='"+userLogged.getUserID()+"' ORDER BY arts.ArtID;"; 
            
            ResultSet resultToCheckSize = stmt.executeQuery(query);
            
            int numOfRows = 0;
            
            // while we have rows, or while next() returns true
            while(resultToCheckSize.next()) {
                // add one to rows
                numOfRows++;
            }
            System.out.println(numOfRows);

            // set artData number of rows and number of columns
            favouritesData = new String[numOfRows][4];
            
            int row = 0; 
           
            ResultSet favouritesResult = stmt.executeQuery(queryInArts);
         

            // while there's a result
            while(favouritesResult.next()) {

                // set favouritesData array to receive each value from each row, for each corresponding column
                favouritesData[row][0] = favouritesResult.getString("ArtID");
                favouritesData[row][1] = favouritesResult.getString("Title");
                favouritesData[row][2] = favouritesResult.getString("ArtistID");
                favouritesData[row][3] = favouritesResult.getString("ArtType");

                row++;

            }
             

            // closings
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

        return favouritesData;
    }
}
