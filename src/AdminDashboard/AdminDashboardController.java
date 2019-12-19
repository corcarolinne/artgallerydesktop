
package AdminDashboard;

import CustomerDashboard.CustomerDashboardModel;
import CustomerDashboard.CustomerDashboardView;
import CustomerDashboard.CustomerProfileView;
import Entities.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class AdminDashboardController implements ActionListener {
    
    // This class has to have a reference to all elements of the program
    public AdminDashboardModel model;
    AdminDashboardView view;
    AdminProfileView adminProfileView;
    User userLogged;
    
    public AdminDashboardController(User userLogged){
        model = new AdminDashboardModel();
        view = new AdminDashboardView(this);
        this.userLogged = userLogged;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("profile")){
            adminProfileView = new AdminProfileView(this, this.userLogged);
//            view.dispose();
        } else if (e.getActionCommand().equals("update-profile")) {
            // getting values from view  
            String firstName = adminProfileView.getFirstName();    
            String lastName = adminProfileView.getLastName();  
            String username = adminProfileView.getUsername();
            String email = adminProfileView.getEmail();  
            String address = adminProfileView.getAddress();  
            String password = adminProfileView.getPassword();

            // Create an instance of the user class with the data collated
            User editUser = new User(firstName, lastName, username, email, address, password, true);
            
            this.model.updateProfile(editUser, userLogged);
            adminProfileView.dispose();
//            view.showView();
        }
      
    }    
    
    public AdminDashboardView getView() {
        return this.view;
    }
    
}
