
package AdminDashboard;

import Entities.Art;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


class ArtUpdateView extends JFrame {

   // creating properties to make values accesible for other parts of the program
    private JTextField titleTextField;
    private JTextField artistIDTextField;
    private JTextField typeTextField;
    
    private Art editArt;
    
    // controller
    private AdminDashboardController controller;
    
    // constructor receives a Controller class
    public ArtUpdateView(AdminDashboardController controller, Art editArt) {
        
        // Putting the reference of the controller in the local reference
        this.controller= controller;
        this.editArt = editArt;
        
        // calling methods to make the window or the view
        attributesSetter();
        components();
        validation();
        
    }
    
    // method to set attributes
    private void attributesSetter(){
        this.setVisible(true);
        this.setSize(400,400);
        this.setTitle("Update Art");
    }
    
    // method to organize components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        // creating text fields and labels
        JLabel titleLabel, artistIDLabel, typeLabel; 
        titleLabel = new JLabel("Title");
        artistIDLabel = new JLabel("Artist ID");
        typeLabel = new JLabel("Type");  
        titleTextField = new JTextField(this.editArt.getTitle(), 20);
        artistIDTextField = new JTextField(Integer.toString(this.editArt.getArtistID()), 20);
        typeTextField = new JTextField(this.editArt.getArtType(), 20);
        
        // creating Update Art button
        JButton updateArt = new JButton("Update Art");
        updateArt.addActionListener((ActionListener) controller);
        updateArt.setActionCommand("update-art");
        
        // adding components to the panel
        panel.add(titleLabel);
        panel.add(titleTextField);
        panel.add(artistIDLabel);
        panel.add(artistIDTextField);
        panel.add(typeLabel);
        panel.add(typeTextField);
        panel.add(updateArt);
 
    }
    
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
    
    // getters
    public String getArtTitle(){
        return titleTextField.getText();
    }
    public String getArtistID(){
        return artistIDTextField.getText();
    } 
    public String getArtType(){
        return typeTextField.getText();
    }
    
}
