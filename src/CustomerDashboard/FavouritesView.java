
package CustomerDashboard;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class FavouritesView extends JFrame {
    
    // controller
    private CustomerDashboardController controller;
    
    private String[][] favouritesData;
    
    // constructor receives a Controller class
    public FavouritesView(CustomerDashboardController controller, String[][] favouritesData) {
        // Putting the reference of the controller in the local reference
        this.controller= controller;
        this.favouritesData = favouritesData;
        // calling methods to make the window or the view
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
         String[] header = {"Art ID","Ttile", "Artist ID","Artist First Name", "Artist Last Name", "Type"};
        
        // table for search
        JTable favouritesTable = new JTable(this.favouritesData, header);
        panel.add(favouritesTable);
        
        
        JScrollPane scroll = new JScrollPane(favouritesTable);
        panel.add(scroll); 
        
        validation();  
    }
   
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }

    
    public void setFavourtitesData(String[][] favouritesData) {
        this.favouritesData = favouritesData;
    }
    
}

    
