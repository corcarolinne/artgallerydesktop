
package AdminDashboard;

import Entities.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.SwingUtilities;



public class AdminDashboardController implements ActionListener {
    
    // This class has to have a reference to all elements of the program
    public AdminDashboardModel model;
    AdminDashboardView view;
    AdminProfileView adminProfileView;
    User userLogged;
    User userToEdit;
    User userToBeDeleted;
    AdminUpdateView adminUpdateView;
    
    public AdminDashboardController(User userLogged){
        model = new AdminDashboardModel();
        view = new AdminDashboardView(this);
        this.userLogged = userLogged;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("admin-profile")){
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
        } else if(e.getActionCommand().equals("update-item")) {
            this.userToEdit = view.getSelectedUser();
            adminUpdateView = new AdminUpdateView(this, userToEdit);
        } else if (e.getActionCommand().equals("update-admin")) {
            String firstName = adminUpdateView.getFirstName();    
            String lastName = adminUpdateView.getLastName();  
            String username = adminUpdateView.getUsername();
            String email = adminUpdateView.getEmail();  
            String address = adminUpdateView.getAddress();  
            String password = adminUpdateView.getPassword();

            // Create an instance of the user class with the data collated
            User editUser = new User(firstName, lastName, username, email, address, password, true);
            
            this.model.updateProfile(editUser, this.userToEdit);
            view.dispose();
            view = new AdminDashboardView(this);
            adminUpdateView.dispose();
        } else if (e.getActionCommand().equals("delete-item")) {
            this.userToBeDeleted = view.getSelectedUser();
            model.deleteAdmin(userToBeDeleted);
        }
        
        
      
    }    
    
    public AdminDashboardView getView() {
        return this.view;
    }
    
}
