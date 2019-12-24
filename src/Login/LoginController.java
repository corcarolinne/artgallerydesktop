
package Login;


import AdminDashboard.AdminDashboardController;
import CustomerDashboard.CustomerDashboardController;
import Entities.User;
import Register.RegisterController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
  
    private LoginModel model;
    private LoginView view;
    private RegisterController registerController;
    private CustomerDashboardController customerDashboardController;
    private AdminDashboardController adminDashboardController;
    private User userLogged;
    
    // constructor
     public LoginController(){
        this.model = new LoginModel();
        this.view = new LoginView(this);
    }

    // action listener
    @Override
    public void actionPerformed(ActionEvent e) {
       
        // When buttons are clicked, do these actions
        if(e.getActionCommand().equals("login")){
            // picking values from view
            String username = view.getUsername();
            String password = view.getPassword();

            // Create an instance of the user class with the data collected
            this.userLogged = new User(username, password);

            // Ask the model if the user is valid
            boolean login = model.login(userLogged);

            // if the it is valid
            if(login){
                // outputs
                System.out.println("Login Sucessful");
                // redirects to dashboard depending on the type of user 
                if(userLogged.getIsAdmin() == true) {
                    this.adminDashboardController = new AdminDashboardController(userLogged);
                } else {
                    this.customerDashboardController = new CustomerDashboardController(userLogged);
                }
                this.view.dispose();
            }
        }
        else if(e.getActionCommand().equals("register")){
            // redirects to create account page by creating a register controller
            registerController = new RegisterController();
            view.dispose();     
        }
    }
    
    public User getUserLogged () {
        return this.userLogged;
    }
    
    public LoginView getView () {
        return this.view;
    }
    
}
