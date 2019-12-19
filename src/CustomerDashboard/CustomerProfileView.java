
package CustomerDashboard;

//import Entities.User;
import Register.RegisterController;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class CustomerProfileView extends JFrame {
    
    //private User loggedUser;
    
    // controller
    private CustomerDashboardController controller;
    
    // constructor receives a Controller class
    public CustomerProfileView(CustomerDashboardController controller) {
        
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
        this.setSize(400,400);
        this.setTitle("View Profile");
    }
    
    // method to organize components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        System.out.println("Estou aqui");
        
        
         
    }
    
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
    
    
    
}
