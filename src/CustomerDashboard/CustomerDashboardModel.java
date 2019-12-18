
package CustomerDashboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
//import Login.User;

public class CustomerDashboardModel {
    
    
    
           
        public String[][] printArt(){
            
            String[][] result = null;
            try{
			// Load the database driver
			//Class.forName("com.mysql.jdbc.Driver").newInstance() ;
			
			String dbServer = "jdbc:mysql://localhost:3306/carol_2018250";
			String user = "root";
			String password = "13 Hatnephfcfati_";
                        
    
                        
                        
                        
			String query = "SELECT * FROM arts";
                        String query2 = "SELECT * FROM arts inner join artists ON arts.ArtistID = artists.ArtistID;";
                        
//art.Piece_Name, art.Type, artist.First_name, artist.Last_name    
			// Get a connection to the database
			Connection conn = DriverManager.getConnection(dbServer, user, password) ;

			// Get a statement from the connection
			Statement stmt = conn.createStatement() ;

			// Execute the query
			ResultSet rs = stmt.executeQuery(query) ;
                        
                        int rows = 0;
                        
                        System.out.println(rows);
                        while(rs.next()){
                            rows++;
                            
                        }
			
                        ResultSet rs2 = stmt.executeQuery(query2) ;
                        result = new String[rows][5];
                        
			int jb = 0;
                        
                        
                        
                        
			// Loop through the result set
			while(rs2.next()) {
                            
                                
                                result[jb][0] = rs2.getString("ArtID");
				result[jb][1] = rs2.getString("Title");
                                result[jb][2] = rs2.getString("ArtType");
                                result[jb][3] = rs2.getString("FirstName");
                                result[jb][4] = rs2.getString("LastName");
                                
                                
                                
                               // result[jb] = rs.getString("first_name");
                                jb++;
			}

			// Close the result set, statement and the connection
			rs.close() ;
			stmt.close() ;
			conn.close() ;
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
                return result;
            
            
            
            
    }
    
}