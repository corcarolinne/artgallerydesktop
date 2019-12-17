
package Register;

import Entities.User;
import Login.LoginController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterController implements ActionListener {
    
    // This class has to have a reference to all elements of the program
    RegisterModel model;
    RegisterView view;
    
    // When the controller starts, we need a new model and a new view
    public RegisterController(){
        view = new RegisterView(this);
        model = new RegisterModel();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // AFTER THE USER INFORMATION HAS BEEN GATHERED FROM THE VIEW,
        // NOW WE CAN GO AND DO WHAT EACH BUTTON IS SUPPOSED TO DO
        
        // When the button is clicked...
        if(e.getActionCommand().equals("register")){
            
        // getting values from view
        String firstName = view.getFirstName();    
        String lastName = view.getLastName();  
        String username = view.getUsername();
        String email = view.getEmail();  
        String address = view.getAddress();  
        String password = view.getPassword();

        // Create an instance of the user class with the data collated
        User newUser = new User(firstName, lastName, username, email, address, password, false);
        
        // create a model for 
        model.register(newUser);
        new LoginController();
        
        
            
            
        }
    }
    
}
