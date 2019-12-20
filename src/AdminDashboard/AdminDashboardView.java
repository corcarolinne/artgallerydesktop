
package AdminDashboard;

import CustomerDashboard.CustomerDashboardController;
import Entities.User;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//import javax.swing.event.TableModelListener;

public class AdminDashboardView extends JFrame {
   
    // controller
    private AdminDashboardController controller;
    JPanel panel;
    private JTable adminTable;
    
     String[][] adminData;
    
    
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
        this.setSize(1200,500);
        this.setTitle("Administrator Dashboard");
    }
    
    // method to organize components of the window
    private void components(){
        panel = new JPanel();
        this.add(panel);
        
        
        // button to go to profile page
        JButton profile = new JButton("Profile");
        profile.addActionListener((ActionListener) controller);
        profile.setActionCommand("admin-profile");
        panel.add(profile);
        
        // button to go to profile page
        JButton update = new JButton("Update");
        update.addActionListener((ActionListener) controller);
        update.setActionCommand("update-item");
        panel.add(update);
        
        
        // array with data for tables
        String[][] artData = null;
        // table header
        String[] header = {"Art", "ArtistFirst", "ArtistLast", "Type"};
    
        // calling method from model 
        artData = controller.model.showArtTable();
        JTable artTable = new JTable(artData, header);
        panel.add(artTable);
        
        
        this.renderAdminTable();
        
        
        // scroll
        JScrollPane scroll = new JScrollPane(artTable);
        JScrollPane scroll2 = new JScrollPane(adminTable);
        panel.add(scroll);
        panel.add(scroll2);
           
        validation();  
    }
    
    public void renderAdminTable() {
        String[] header2 = {"UserID","FirstName", "LastName", "Username", "Address", "Email"};
        adminData = controller.model.showAdminTable();
        JTable nextTable = new JTable(adminData, header2);
        this.adminTable = nextTable;
        this.panel.add(adminTable);
    }
    
    public User getSelectedUser() {
        User selectedUser = new User();
        int selectedUserIndex = adminTable.getSelectedRow();
        
        if (selectedUserIndex > -1) {
            String[] selectedUserArray = adminData[selectedUserIndex];
            
            selectedUser.setUserID(Integer.parseInt(selectedUserArray[0]));
            selectedUser.setFirstName(selectedUserArray[1]);
            selectedUser.setLastName(selectedUserArray[2]);
            selectedUser.setUsername(selectedUserArray[3]);
            selectedUser.setAddress(selectedUserArray[4]);
            selectedUser.setEmail(selectedUserArray[5]);
        }
        
        return selectedUser;
    }
    
   
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
    
    public JTable getAdminTable() {
        return this.adminTable;
    }
}
