
package AdminDashboard;


import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


class ArtCreateView extends JFrame {

    // properties
    private JTextField titleTextField;
    private JTextField artistIDTextField;
    private JTextField typeTextField;
    private AdminDashboardController controller;
    
    // constructor receives a Controller class
    public ArtCreateView(AdminDashboardController controller) {
        this.controller= controller;
        
        // calling methods to make the window or the view
        attributesSetter();
        components();
        validation();
        
    }
    
    // method to set attributes
    private void attributesSetter(){
        this.setVisible(true);
        this.setSize(300,400);
        this.setTitle("Create Art");
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
        titleTextField = new JTextField(20);
        artistIDTextField = new JTextField(20);
        typeTextField = new JTextField(20);
        
        // button
        JButton createArt = new JButton("Create Art");
        createArt.addActionListener((ActionListener) controller);
        createArt.setActionCommand("create-art");
        
        // adding components to the panel
        panel.add(titleLabel);
        panel.add(titleTextField);
        panel.add(artistIDLabel);
        panel.add(artistIDTextField);
        panel.add(typeLabel);
        panel.add(typeTextField);
        panel.add(createArt);
 
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
