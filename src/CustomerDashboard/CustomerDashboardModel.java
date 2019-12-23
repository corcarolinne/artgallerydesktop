
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
    String dbServer = "jdbc:mysql://localhost:3306/carol_2018250"; // type of database/port/database name
    String user = "root";
    String password = "13 Hatnephfcfati_";
    //private boolean isAdmin;
    
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
                
                
             // or if there's any search, do the query and populate the table with the data found
            } else {
                
                if(selectedFilter == "Artist") {
                
                    String artistID = "";
                    String queryInArtists = "SELECT * FROM carol_2018250.artists WHERE FirstName = '"+searchInput+"' OR LastName = '"+searchInput+"';";
                    
                    int rowTest = 0;
                    // sending the query to the database
                    ResultSet searchArtists = stmt.executeQuery(queryInArtists);
                    while(searchArtists.next()) {
                        
                        System.out.println("entrou no while");
                        artistID = searchArtists.getString("ArtistID");
                        System.out.println(artistID);
                        
                        rowTest++;
                    }
                      // building the query
                    //String queryToCheckSize = "SELECT * FROM carol_2018250.arts WHERE ArtistID = '"+artistID+"';"; 
                    String queryInArts = "SELECT arts.ArtID, arts.Title, artists.FirstName, artists.LastName, arts.ArtType FROM carol_2018250.arts INNER JOIN carol_2018250.artists ON arts.ArtistID =  artists.ArtistID WHERE artists.ArtistID='"+artistID+"' ORDER BY arts.ArtID;"; 
 
                    
                    ResultSet resultToCheckSize = stmt.executeQuery(queryInArts);
                    
                     int numOfRows = 0;       
                    // while we have rows, or while next() returns true
                    while(resultToCheckSize.next()) {
                        // add one to rows
                        numOfRows++;
                    }
                    System.out.println(numOfRows);
                    
                    // set artData number of rows and number of columns
                    artData= new String[numOfRows][4];
                    
                    // sending the query to the database
                    ResultSet searchResult = stmt.executeQuery(queryInArts);
                    
                    
                    int row = 0;
                    while(searchResult.next()) {
                        
                        // set artData array to receive each value from each row, for each corresponding column
                        //artData1[row][0] = result.getString("ArtID");
                        artData[row][0] = searchResult.getString("Title");
                        artData[row][1] = searchResult.getString("FirstName");
                        artData[row][2] = searchResult.getString("LastName");
                        artData[row][3] = searchResult.getString("ArtType");
                        
                        row++;

                    }


                } else {
                    String queryToCheckSize = "SELECT arts.ArtID, arts.Title, artists.FirstName, artists.LastName, arts.ArtType FROM carol_2018250.arts INNER JOIN carol_2018250.artists ON arts.ArtistID =  artists.ArtistID WHERE "+selectedFilter+"='"+searchInput+"' ORDER BY arts.ArtID;"; 

                    // building the query
                     String query = "SELECT arts.ArtID, arts.Title, artists.FirstName, artists.LastName, arts.ArtType FROM carol_2018250.arts INNER JOIN carol_2018250.artists ON arts.ArtistID =  artists.ArtistID WHERE "+selectedFilter+"='"+searchInput+"' ORDER BY arts.ArtID;"; 

                     
                    ResultSet resultToCheckSize = stmt.executeQuery(query);
                    
                    int numOfRows = 0;       
                    // while we have rows, or while next() returns true
                    while(resultToCheckSize.next()) {
                        // add one to rows
                        numOfRows++;
                    }

                    // set artData number of rows and number of columns
                    artData= new String[numOfRows][4];
                    
                    // sending the query to the database
                    ResultSet searchResult = stmt.executeQuery(query);
                    int row = 0;

                    //boolean isResult= searchResult.next();
                    //System.out.println(isResult);
                    while(searchResult.next()) {
                        
                        //row = searchResult.getRow();
                        // set artData array to receive each value from each row, for each corresponding column
                        //artData1[row][0] = result.getString("ArtID");
                        artData[row][0] = searchResult.getString("Title");
                        artData[row][1] = searchResult.getString("FirstName");
                        artData[row][2] = searchResult.getString("LastName");
                        artData[row][3] = searchResult.getString("ArtType");
                        
                       
                        row++;

                    }
                }
              // calling the method in charge of closing the connections
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
