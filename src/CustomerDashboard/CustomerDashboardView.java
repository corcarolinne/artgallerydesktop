
package CustomerDashboard;

//import Register.RegisterController;
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
    
    // controller
    private CustomerDashboardController controller;
    
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
        String filterOptions[]={"Title","Artist","Type"};        
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
        JButton profile = new JButton("Profile");
        profile.addActionListener((ActionListener) controller);
        profile.setActionCommand("profile");
        panel.add(profile);
        
        // button to go to profile page
        JButton logout = new JButton("Logout");
        logout.addActionListener((ActionListener) controller);
        logout.setActionCommand("logout");
        panel.add(logout);
        
        // array with data for table
        String[][] artData = null;
        // table header
        String[] header = {"Art", "ArtistFirst", "ArtistLast", "Type"};
    
        // calling method from model 
        artData = controller.model.showArtTable();
        
        // table
        JTable artTable = new JTable(artData, header);
        panel.add(artTable);
        
        // scroll
        JScrollPane scroll = new JScrollPane(artTable);
        panel.add(scroll);
           
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
    

    
}
