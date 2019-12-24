
package Login;

import java.awt.LayoutManager;
import java.awt.event.ActionListener;
//import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame {
    
    // creating properties to make username and password accesible for other parts of the program
    private JTextField usernameTextField;
    private JPasswordField passwordTextField; 
    // controller
    private LoginController controller;
    
    // constructor receives a LoginController class
    public LoginView(LoginController controller) {
        // Putting the reference of the controller in the local reference
        this.controller = controller;
        // calling methods to make the window or the view
        attributesSetter();
        components();
        validation();
    }
    
    // method to set attributes
    private void attributesSetter(){
        this.setVisible(true);
        this.setSize(300,300);
        this.setTitle("Login");
    }
    
    // method to organize components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        // setting layout
        //BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        //panel.setLayout(boxLayout);
        
        // creating labels and textfields
        JLabel usernameLabel, passwordLabel;
        usernameLabel = new JLabel("Username");  
        usernameLabel.setBounds(50,50, 100,30);  
        usernameTextField = new JTextField(20);
        passwordLabel = new JLabel("Password");  
        passwordLabel.setBounds(50,100, 100,30);  
        passwordTextField = new JPasswordField (20);
        
        // creating login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener((ActionListener) controller);
        loginButton.setActionCommand("login");
        
        // creating register button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener((ActionListener) controller);
        registerButton.setActionCommand("register");
        
        
        panel.add(usernameLabel);
        panel.add(usernameTextField);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(loginButton);
        panel.add(registerButton);  
        
    }
    
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
    
    // getters for username and password
    public String getUsername(){
        return usernameTextField.getText();
    }
    public String getPassword(){
        
        // converting password into string to pick the password value
        String passwordAsString = String.valueOf(passwordTextField.getPassword());
        return passwordAsString;
    }
    
    
}
