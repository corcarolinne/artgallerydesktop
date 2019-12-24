
package AdminDashboard;

import CustomerDashboard.CustomerDashboardController;
import Entities.Art;
import Entities.Artist;
import Entities.User;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AdminDashboardView extends JFrame {
   
    // properties
    private AdminDashboardController controller;
    JPanel panel;
    
    private JTable artsTable;
    String[][] artsData;
    
    private JTable adminTable;
    String[][] adminData;
    
    private JTable artistsTable;
    String[][] artistsData;
    
    // constructor receives a Controller class
    public AdminDashboardView(AdminDashboardController controller) {
        // Putting the reference of the controller in the local reference
        this.controller= controller;
        // calling methods to make the window or the view
        this.showView();
         
    }
    
    // show admin dashboard view calling the methods
    public void showView() {
        // calling methods to make the window or the view
        attributesSetter();
        components();
        validation();
    }
    
    // method to set attributes
    private void attributesSetter(){
        this.setVisible(true);
        this.setSize(1500,550);
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
        
        // button to go to update art page
        JButton goToArtCreate = new JButton("Create Art");
        goToArtCreate.addActionListener((ActionListener) controller);
        goToArtCreate.setActionCommand("go-to-art-create");
        panel.add(goToArtCreate);
        
        // button to go to update art page
        JButton goToArtistCreate = new JButton("Create Artist");
        goToArtistCreate.addActionListener((ActionListener) controller);
        goToArtistCreate.setActionCommand("go-to-artist-create");
        panel.add(goToArtistCreate);
        
        // button to go to update art page
        JButton goToAdminCreate = new JButton("Create Admin");
        goToAdminCreate.addActionListener((ActionListener) controller);
        goToAdminCreate.setActionCommand("go-to-admin-create");
        panel.add(goToAdminCreate);
        
        // button to go to update art page
        JButton goToArtUpdate = new JButton("Update Art");
        goToArtUpdate.addActionListener((ActionListener) controller);
        goToArtUpdate.setActionCommand("go-to-art-update");
        panel.add(goToArtUpdate);
        
        // button to go to update art page
        JButton goToArtistUpdate = new JButton("Update Artist");
        goToArtistUpdate.addActionListener((ActionListener) controller);
        goToArtistUpdate.setActionCommand("go-to-artist-update");
        panel.add(goToArtistUpdate);
        
        // button to go to update data from table
        JButton update = new JButton("Update Admin");
        update.addActionListener((ActionListener) controller);
        update.setActionCommand("update-item");
        panel.add(update);
        
        // button to delete art from table
        JButton deleteArt = new JButton("Delete Art");
        deleteArt.addActionListener((ActionListener) controller);
        deleteArt.setActionCommand("delete-art");
        panel.add(deleteArt);
        
        // button to delete art from table
        JButton deleteArtist = new JButton("Delete Artist");
        deleteArtist.addActionListener((ActionListener) controller);
        deleteArtist.setActionCommand("delete-artist");
        panel.add(deleteArtist);
        
          // button to delete data from table
        JButton delete = new JButton("Delete Admin");
        delete.addActionListener((ActionListener) controller);
        delete.setActionCommand("delete-item");
        panel.add(delete);
        
        // button to go to profile page
        JButton logout = new JButton("Logout");
        logout.addActionListener((ActionListener) controller);
        logout.setActionCommand("logout");
        panel.add(logout);
        
        // calling methods to show tables
        this.renderArtsTable();
        this.renderAdminTable();
        this.renderArtistsTable();
        
        // scroll
        JScrollPane scroll = new JScrollPane(artsTable);
        JScrollPane scroll2 = new JScrollPane(adminTable);
        JScrollPane scroll3 = new JScrollPane(artistsTable);
        panel.add(scroll);
        panel.add(scroll3);
        panel.add(scroll2);
           
        validation();  
    }
    
     public void renderArtsTable() {
        String[] headerArts = {"Art ID", "Art", "Artist ID", "Artist First Name", "Artist Last Name", "Type"};
        artsData = controller.model.showArtTable();
        JTable nextTable = new JTable(artsData, headerArts);
        this.artsTable = nextTable;
        this.panel.add(artsTable);
    }
    
    public void renderAdminTable() {
        String[] headerAdmin = {"UserID","FirstName", "LastName", "Username", "Address", "Email"};
        adminData = controller.model.showAdminTable();
        JTable nextTable = new JTable(adminData, headerAdmin);
        this.adminTable = nextTable;    
        this.panel.add(adminTable);
    }
    
     
    public void renderArtistsTable() {
        String[] headerArtists = {"ArtistID","FirstName", "LastName", "Address", "Website"};
        artistsData = controller.model.showArtistsTable();
        JTable nextTable = new JTable(artistsData, headerArtists);
        this.artistsTable = nextTable;
        this.panel.add(artistsTable);
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
    
    public Art getSelectedArt() {
        Art selectedArt = new Art();
        int selectedArtIndex = artsTable.getSelectedRow();
        
        if (selectedArtIndex > -1) {
            String[] selectedArtsArray = artsData[selectedArtIndex];
            
            selectedArt.setArtID(Integer.parseInt(selectedArtsArray[0]));
            selectedArt.setTitle(selectedArtsArray[1]);
            selectedArt.setArtistID(Integer.parseInt(selectedArtsArray[2]));
            selectedArt.setArtistFirstName(selectedArtsArray[3]);
            selectedArt.setArtistLastName(selectedArtsArray[4]);
            selectedArt.setArtType(selectedArtsArray[5]);
        
        }
        
        return selectedArt;
    }
    
     public Artist getSelectedArtist() {
        Artist selectedArtist = new Artist();
        int selectedArtistIndex = artistsTable.getSelectedRow();
        
        if (selectedArtistIndex > -1) {
            String[] selectedArtsArray = artistsData[selectedArtistIndex];
            
            selectedArtist.setArtistID(Integer.parseInt(selectedArtsArray[0]));
            selectedArtist.setFirstName(selectedArtsArray[1]);
            selectedArtist.setLastName(selectedArtsArray[2]);
            selectedArtist.setAddress(selectedArtsArray[3]);
            selectedArtist.setWebsite(selectedArtsArray[4]);
            
        
        }
        
        return selectedArtist;
    }
   
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
    
    // getter for adminTable
    public JTable getAdminTable() {
        return this.adminTable;
    }
    
     // getter for artistsTable
    public JTable getArtistsTable() {
        return this.artistsTable;
    }
}
