
package AdminDashboard;

import Entities.User;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class AdminProfileView extends JFrame {

    // properties
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField usernameTextField;
    private JTextField emailTextField;
    private JTextField addressTextField;
    private JPasswordField passwordTextField;
    private User userLogged;

    // controller
    private AdminDashboardController controller;
    
    // constructor receives a Controller class and an instance of User
    public AdminProfileView(AdminDashboardController controller, User userLogged) {
 
        this.controller= controller;
        this.userLogged = userLogged;
        
        // calling methods to make the view
        attributesSetter();
        components();
        validation();    
    }
    
    // method to set attributes
    private void attributesSetter(){
        this.setVisible(true);
        this.setSize(325,400);
        this.setTitle("Profile");
    }
    
    // method to organize components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        // creating text fields and labels
        JLabel firstNameLabel, lastNameLabel, usernameLabel, emailLabel, addressLabel, passwordLabel; 
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        usernameLabel = new JLabel("Username");
        emailLabel = new JLabel("Email");
        addressLabel = new JLabel("Address");
        passwordLabel = new JLabel("Password");        
        firstNameTextField = new JTextField(this.userLogged.getFirstName(), 20);
        lastNameTextField = new JTextField(this.userLogged.getLastName(), 20);
        usernameTextField = new JTextField(this.userLogged.getUsername(), 20);
        emailTextField = new JTextField(this.userLogged.getEmail(), 20);
        addressTextField = new JTextField(this.userLogged.getAddress(), 20);
        passwordTextField = new JPasswordField (this.userLogged.getPassword(), 20);
        
        // button
        JButton registerButton = new JButton("Update Profile");
        registerButton.addActionListener((ActionListener) controller);
        registerButton.setActionCommand("update-profile");
        
        // adding components to the panel
        panel.add(firstNameLabel);
        panel.add(firstNameTextField);
        panel.add(lastNameLabel);
        panel.add(lastNameTextField);
        panel.add(usernameLabel);
        panel.add(usernameTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(addressLabel);
        panel.add(addressTextField);
        panel.add(passwordLabel); 
        panel.add(passwordTextField);
        panel.add(registerButton);   
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
    public String getUsername(){
        return usernameTextField.getText();
    }
    public String getEmail(){
        return emailTextField.getText();
    }
     public String getAddress(){
        return addressTextField.getText();
    } 
    public String getPassword(){  
        // converting password into string to pick the password value
        String passwordAsString = String.valueOf(passwordTextField.getPassword());
        return passwordAsString;
    }

    // setters
    public void setFirstName(String newFirstName) {
        firstNameTextField.setText(newFirstName);
    
  }
    
}
