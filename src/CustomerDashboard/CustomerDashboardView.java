
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
    // properties
    private JTextField searchTextField;
    private JComboBox dropdown;
    private JTable artsTable;
    String[][] artsData;
    
    // controller
    private CustomerDashboardController controller;
    
    // constructor receives a Controller class
    public CustomerDashboardView(CustomerDashboardController controller) {
        this.controller= controller;
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
        this.setSize(500,600);
        this.setTitle("Customer Dashboard");
    }
    
    // method to organize components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        // search label and input
        JLabel searchLabel = new JLabel("Search:");        
        searchTextField = new JTextField(20);
        panel.add(searchLabel);
        panel.add(searchTextField);
        
        // filter dropdown
        String filterOptions[]={"","Title","Artist","ArtType"};        
        this.dropdown = new JComboBox(filterOptions);
        dropdown.addActionListener((ActionListener) controller);
        dropdown.setActionCommand("filter");
        panel.add(dropdown);
        
        // button for search
        JButton search = new JButton("Search");
        search.addActionListener((ActionListener) controller);
        search.setActionCommand("search");
        panel.add(search);
        
        // button to like art
        JButton like = new JButton("Like Art");
        like.addActionListener((ActionListener) controller);
        like.setActionCommand("like");
        panel.add(like);
        
        // button to go to profile page
        JButton profile = new JButton("Profile");
        profile.addActionListener((ActionListener) controller);
        profile.setActionCommand("profile");
        panel.add(profile);
        
         // button to go to favourites page
        JButton favourites = new JButton("Favourites");
        favourites.addActionListener((ActionListener) controller);
        favourites.setActionCommand("go-to-favourites");
        panel.add(favourites);
        
        // button to logout
        JButton logout = new JButton("Logout");
        logout.addActionListener((ActionListener) controller);
        logout.setActionCommand("logout");
        panel.add(logout);
        
         String[] header = {"Art ID","Ttile", "Artist ID","Artist First Name", "Artist Last Name", "Type"};
    
        // calling method from model to show art table
        artsData = controller.model.showArtTable("","");
        
        // table
        artsTable = new JTable(artsData, header);
        panel.add(artsTable);
   
        // scroll
        JScrollPane scroll = new JScrollPane(artsTable);
        panel.add(scroll);
        
        validation();  
    }
   
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }

    // getters
    public String getSearchInput() {
         return this.searchTextField.getText();
    }
    
    public String getDropdownItem() {
        return this.dropdown.getSelectedItem().toString();
    }
    
    // get art selected from table, returns an instance of Art
    public Art getSelectedArt() {
        // create new instance
        Art selectedArt = new Art();
        // get index of the selected row
        int selectedArtIndex = artsTable.getSelectedRow();
        
        // if something is selected 
        if (selectedArtIndex > -1) {
            // creates array to store selected row inside 2d array
            String[] selectedArtsArray = artsData[selectedArtIndex];
             // using setters to pass the values in the row to the Art object
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
