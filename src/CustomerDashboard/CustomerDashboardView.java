
package CustomerDashboard;

//import Register.RegisterController;
import Entities.Art;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class CustomerDashboardView extends JFrame {
    // creating properties to make values accesible for other parts of the program
    private JTextField searchTextField;
    private JComboBox dropdown;
    
     private JTable artsTable;
     String[][] artsData;
    
    // controller
    private CustomerDashboardController controller;
    //private String[][] searchData;
    
    // constructor receives a Controller class
    public CustomerDashboardView(CustomerDashboardController controller) {
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
        this.setTitle("Customer Dashboard");
    }
    
    // method to organize components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
       
        // search
        JLabel searchLabel = new JLabel("Search:");        
        searchTextField = new JTextField(20);
        panel.add(searchLabel);
        panel.add(searchTextField);
        
        // filter
        String filterOptions[]={"","Title","Artist","ArtType"};        
        this.dropdown = new JComboBox(filterOptions);
        dropdown.addActionListener((ActionListener) controller);
        dropdown.setActionCommand("filter");
        panel.add(dropdown);
        
        
        // button to go to profile page
        JButton search = new JButton("Search");
        search.addActionListener((ActionListener) controller);
        search.setActionCommand("search");
        panel.add(search);
        
        // button to go to profile page
        JButton like = new JButton("Like Art");
        like.addActionListener((ActionListener) controller);
        like.setActionCommand("like");
        panel.add(like);
        
        // button to go to profile page
        JButton profile = new JButton("Profile");
        profile.addActionListener((ActionListener) controller);
        profile.setActionCommand("profile");
        panel.add(profile);
        
         // button to go to profile page
        JButton favourites = new JButton("Favourites");
        favourites.addActionListener((ActionListener) controller);
        favourites.setActionCommand("go-to-favourites");
        panel.add(favourites);
        
        // button to go to profile page
        JButton logout = new JButton("Logout");
        logout.addActionListener((ActionListener) controller);
        logout.setActionCommand("logout");
        panel.add(logout);
        
        // array with data for table
        //this.artsData = null;
        //searchData = null;
        // table header
         String[] header = {"Art ID","Ttile", "Artist ID","Artist First Name", "Artist Last Name", "Type"};
    
        // calling method from model 
        artsData = controller.model.showArtTable("","");
        //searchData = controller.model.showArtTable(this.getDropdownItem(),this.getSearchInput());
        
        // table
        artsTable = new JTable(artsData, header);
        panel.add(artsTable);
        
        // table for search
        
        //JTable searchTable = new JTable(searchData, header);
        //panel.add(searchTable);
        
        // scroll
        JScrollPane scroll = new JScrollPane(artsTable);
        panel.add(scroll);
        //JScrollPane scroll2 = new JScrollPane(searchTable);
        //panel.add(scroll2); 
        
        validation();  
    }
   
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }

    public String getSearchInput() {
         return this.searchTextField.getText();
    }
    
    public String getDropdownItem() {
        return this.dropdown.getSelectedItem().toString();
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

    
    
}
