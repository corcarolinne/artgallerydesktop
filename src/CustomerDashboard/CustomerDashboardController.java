
package CustomerDashboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Login.LoginController;
import Entities.User;

public class CustomerDashboardController implements ActionListener {
    
    // This class has to have a reference to all elements of the program
    public CustomerDashboardModel model;
    CustomerDashboardView view;
    CustomerProfileView profileUpdate;
    SearchResultsView searchResultsView;
    User userLogged;
    LoginController login;
    String selectedFilter;
    String searchInput;
    String[][] searchResult;
    
    public CustomerDashboardController(User userLogged){
        model = new CustomerDashboardModel();
        view = new CustomerDashboardView(this);
        this.userLogged = userLogged;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("profile")){
            profileUpdate = new CustomerProfileView(this, this.userLogged);
        } else if (e.getActionCommand().equals("update-profile")) {
            // getting values from view  
            String firstName = profileUpdate.getFirstName();    
            String lastName = profileUpdate.getLastName();  
            String username = profileUpdate.getUsername();
            String email = profileUpdate.getEmail();  
            String address = profileUpdate.getAddress();  
            String password = profileUpdate.getPassword();

            // Create an instance of the user class with the data collated
            User editUser = new User(firstName, lastName, username, email, address, password, false);
            
            this.model.updateProfile(editUser, userLogged);
            profileUpdate.dispose();
//            view.showView();
        } else if(e.getActionCommand().equals("logout")){
            login = new LoginController();
            view.dispose();
        }  else if(e.getActionCommand().equals("filter")) {
            this.selectedFilter =  view.getDropdownItem();
        } else if(e.getActionCommand().equals("search")){
            // getting values from input
            this.searchInput = view.getSearchInput();
            searchResult= this.model.showArtTable(this.searchInput, this.selectedFilter);
            searchResultsView = new SearchResultsView(this, searchResult);
            //this.searchResultsView.setSearchData(searchResult);
            //view = new CustomerDashboardView(this);
            
            
            
            
        }
        
    }    
    
    public CustomerDashboardView getView() {
        return this.view;
    }
}