
package AdminDashboard;

import Entities.Artist;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


class ArtistCreateView extends JFrame {
    
     // creating properties to make values accesible for other parts of the program
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField addressTextField;
    private JTextField websiteTextField;
    
    // controller
    private AdminDashboardController controller;
    


    public ArtistCreateView(AdminDashboardController controller) {
        
        // Putting the reference of the controller in the local reference
        this.controller= controller;
        
        // calling methods to make the window or the view
        attributesSetter();
        components();
        validation();
    }
    
    // method to set attributes
    private void attributesSetter(){
        this.setVisible(true);
        this.setSize(325,400);
        this.setTitle("Create Artist");
    }
    
    // method to organize components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        // creating text fields and labels
        JLabel firstNameLabel, lastNameLabel, addressLabel, websiteLabel; 
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        addressLabel = new JLabel("Address");  
        websiteLabel = new JLabel("Website");
        firstNameTextField = new JTextField(20);
        lastNameTextField = new JTextField(20);
        addressTextField = new JTextField(20);
        websiteTextField = new JTextField(20);
        
        // creating Create Account button
        JButton createArtist = new JButton("Create Artist");
        createArtist.addActionListener((ActionListener) controller);
        createArtist.setActionCommand("create-artist");
        
        // adding components to the panel
        panel.add(firstNameLabel);
        panel.add(firstNameTextField);
        panel.add(lastNameLabel);
        panel.add(lastNameTextField);
        panel.add(addressLabel);
        panel.add(addressTextField);
        panel.add(websiteLabel);
        panel.add(websiteTextField);
        panel.add(createArtist);   
    }
    
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
    
    // getters
    public String getFirstName(){
        return firstNameTextField.getText();
    }
    public String getLastName(){
        return lastNameTextField.getText();
    } 
     public String getAddress(){
        return addressTextField.getText();
    } 
     public String getWebsite(){
        return websiteTextField.getText();
    }
}
