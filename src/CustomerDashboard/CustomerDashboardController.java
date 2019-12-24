
package CustomerDashboard;

import Entities.Art;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Login.LoginController;
import Entities.User;

public class CustomerDashboardController implements ActionListener {
    
    public CustomerDashboardModel model;
    CustomerDashboardView view;
    CustomerProfileView profileUpdate;
    SearchResultsView searchResultsView;
    FavouritesView favouritesView;
    User userLogged;
    LoginController login;
    String selectedFilter;
    String searchInput;
    String[][] searchResult;
    String[][] favouritesData;
    Art artToLike;
    
    // constructor
    public CustomerDashboardController(User userLogged){
        model = new CustomerDashboardModel();
        view = new CustomerDashboardView(this);
        this.userLogged = userLogged;
    }

    // action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        // if a button is clicked, do the following actions
        if(e.getActionCommand().equals("profile")){
            // redirects to profile page
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
            // calling method from model to update profile
            this.model.updateProfile(editUser, userLogged);
            profileUpdate.dispose();
        } else if(e.getActionCommand().equals("logout")){
            login = new LoginController();
            view.dispose();
        }  else if(e.getActionCommand().equals("filter")) {
            // call method in view to get selected item in dropdown
            this.selectedFilter =  view.getDropdownItem();
        } else if(e.getActionCommand().equals("search")){
            // getting values from input
            this.searchInput = view.getSearchInput();
            // call method to pick data for art table passing the search inputs and filter and saving this into a 2d array
            searchResult= this.model.showArtTable(this.searchInput, this.selectedFilter);
            // call the view to show  search results passing 2d array
            searchResultsView = new SearchResultsView(this, searchResult);            
        } else if (e.getActionCommand().equals("like")){
           // get select art
           this.artToLike = view.getSelectedArt();
           // call method to send art to favourites table
           model.createFavourite(this.artToLike, this.userLogged);
        }else if (e.getActionCommand().equals("go-to-favourites")){
           // call method to show favourites table and assign result of it to array 2d
           favouritesData= this.model.showFavourites(this.userLogged);
           // redirects to favourite page passing array 2d
           favouritesView = new FavouritesView(this, this.favouritesData);
        }
        
    }   
    
    public CustomerDashboardView getView() {
        return this.view;
    }
}