
package Register;

import Entities.User;
import Login.LoginController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterController implements ActionListener {
    
    RegisterModel model;
    RegisterView view;
    
    // When the controller starts, we need a new model and a new view
    public RegisterController(){
        view = new RegisterView(this);
        model = new RegisterModel();
    }
    
    // action listener
    @Override
    public void actionPerformed(ActionEvent e) {
     
        // when register button is clicked
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
        
        // calling method from model
        model.register(newUser);
        
        view.dispose();
        new LoginController();
            
        }
   }
    
}
