
package CustomerDashboard;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class SearchResultsView extends JFrame {
    
    // properties
    private CustomerDashboardController controller;
    private String[][] searchData;
    
    // constructor receives a Controller class and a 2d array with data for search table
    public SearchResultsView(CustomerDashboardController controller, String[][] searchData) {
        // Putting the reference of the controller in the local reference
        this.controller= controller;
        this.searchData = searchData;
        // calling methods to make the view
        this.showView(); 
    }
    
    public void showView() {
        // calling methods to make the window or the view
        attributesSetter();
        components();
        validation();
    }
    
    // method to set attributes
    private void attributesSetter(){
        this.setVisible(true);
        this.setSize(500,500);
        this.setTitle("Search Results");
    }
    
    // method to organize components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
  
        // table header
         String[] header = {"Art ID","Title", "Artist ID","Artist First Name", "Artist Last Name", "Type"};
        
        // table for search
        JTable searchTable = new JTable(this.searchData, header);
        panel.add(searchTable);
        
        JScrollPane scroll = new JScrollPane(searchTable);
        panel.add(scroll); 
        
        validation();  
    }
   
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }

    public void setSearchData(String[][] searchData) {
        this.searchData = searchData;
    }
    
}
