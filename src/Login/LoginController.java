
package Login;


import CustomerDashboard.CustomerDashboardController;
import Entities.User;
import Register.RegisterController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The controller will have the action listener and will be the brain of the application, making the connection between model and view
public class LoginController implements ActionListener {
    
    // reference to all the other elements in the login
    LoginModel model;
    LoginView view;
    RegisterController registerController;
    CustomerDashboardController customerDashboardController;
    
    // constructor to create a new view
     public LoginController(){
        view = new LoginView(this);
        // creating a new connection to the DB
        model = new LoginModel();
    }

     
    @Override
    public void actionPerformed(ActionEvent e) {
       
        
        // AFTER THE USER INFORMATION HAS BEEN GATHERED FROM THE VIEW,
        // NOW WE CAN GO AND DO WHAT EACH BUTTON IS SUPPOSED TO DO
        
        // When the button is clicked
        if(e.getActionCommand().equals("login")){
            
            // We get username and password from the view
        String username = view.getUsername();
        String password = view.getPassword();

        // Create an instance of the user class with the data collated
        User userLogged = new User(null, null, username, null, null, password, false);

            // Ask the model if the user is valid
            boolean login = model.login(userLogged);
            
            // if the it is valid, show the welcome screen and close the login
            if(login){
                System.out.println("Login Sucessful");
                
                if(userLogged.isAdmin == true) {
                    System.out.println("Admin dashboard vai aqui");
                } else {
                    customerDashboardController = new CustomerDashboardController();
                }
                
                view.dispose();
            }
        }
        // When the button is clicked...
        // IMPORTANT, THIS PART IS FAIRLY INCOMPLETE, YOU SHOULD DOUBLE CHECK
        // FIRST IF THE USER ALREADY EXISTS IN THE DATABASE. 
        // TO DO THIS, YOU CAN CALL THE METHOD ABOVE!!!!!
        else if(e.getActionCommand().equals("register")){
            
            // redirects to create account page by creating a register controller
            registerController = new RegisterController();
            view.dispose();
            
            
        }
    }
    
}
