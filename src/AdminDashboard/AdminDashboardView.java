
package AdminDashboard;

import CustomerDashboard.CustomerDashboardController;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.event.TableModelListener;

public class AdminDashboardView extends JFrame {
   
    // controller
    private AdminDashboardController controller;
    
    // constructor receives a Controller class
    public AdminDashboardView(AdminDashboardController controller) {
        // Putting the reference of the controller in the local reference
        this.controller= controller;
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
        this.setTitle("Administrator Dashboard");
    }
    
    // method to organize components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        
        // button to go to profile page
        JButton profile = new JButton("Profile");
        profile.addActionListener((ActionListener) controller);
        profile.setActionCommand("profile");
        panel.add(profile);
        
        
        
        // array with data for tables
        String[][] artData = null;
         String[][] adminData = null;
        // table header
        String[] header = {"Art", "ArtistFirst", "ArtistLast", "Type"};
        String[] header2 = {"UserID","FirstName", "LastName", "Username", "Address", "Email"};
    
        // calling method from model 
        artData = controller.model.showArtTable();
        adminData = controller.model.showAdminTable();
        
        // table
        JTable artTable = new JTable(artData, header);
        panel.add(artTable);
        JTable adminTable = new JTable(adminData, header2);
        panel.add(adminTable);
        
        
        // scroll
        JScrollPane scroll = new JScrollPane(artTable);
        JScrollPane scroll2 = new JScrollPane(adminTable);
        panel.add(scroll);
        panel.add(scroll2);
           
         validation();  
    }
   
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
    
    
}
