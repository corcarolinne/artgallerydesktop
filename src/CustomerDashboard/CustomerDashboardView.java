
package CustomerDashboard;

//import Register.RegisterController;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class CustomerDashboardView extends JFrame {
    // creating properties to make values accesible for other parts of the program
   
    // controller
    private CustomerDashboardController controller;
    
    // constructor receives a Controller class
    public CustomerDashboardView(CustomerDashboardController controller) {
        
        // Putting the reference of the controller in the local reference
        this.controller= controller;
        
        // calling methods to make the window or the view
        attributesSetter();
        //components();
         
    
    
    // method to organize components of the window
    //private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        
        // button for profile
        JButton profile = new JButton("Profile");
        profile.addActionListener((ActionListener) controller);
        profile.setActionCommand("view-profile");
        
        // array with data for table
        String[][] artData = null;
        
        
        //System.out.println(artData);
        // table header
        String[] header = {"Art ID", "Art", "ArtistFirst", "ArtistLast", "Type"};
    
        System.out.println(header); 
        artData = controller.model.printArt();
        
        
        // table
        JTable artTable = new JTable(artData, header);
        panel.add(artTable);
        
        // scroll
        JScrollPane scroll = new JScrollPane(artTable);
        panel.add(scroll);
        
        panel.add(profile);
           
         validation();  
    }
   
    // method to set attributes
    private void attributesSetter(){
        this.setVisible(true);
        this.setSize(400,400);
        this.setTitle("Customer Dashboard");
    }
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }

    
}
