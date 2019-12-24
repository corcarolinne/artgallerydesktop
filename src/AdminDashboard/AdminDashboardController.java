
package AdminDashboard;

import Entities.Art;
import Entities.Artist;
import Entities.User;
import Login.LoginController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.SwingUtilities;



public class AdminDashboardController implements ActionListener {
    
    // properties
    public AdminDashboardModel model;
    AdminDashboardView view;
    LoginController login;
    AdminProfileView adminProfileView;
    ArtCreateView artCreateView;
    ArtistCreateView artistCreateView;
    AdminCreateView adminCreateView;
    User userLogged;
    User userToEdit;
    User userToBeDeleted;
    AdminUpdateView adminUpdateView;
    Art artToEdit;
    Art artToBeDeleted;
    Artist artistToEdit;
    Artist artistToBeDeleted;
    ArtUpdateView artUpdateView;
    ArtistUpdateView artistUpdateView;
    
    // constructor receives user logged in
    public AdminDashboardController(User userLogged){
        model = new AdminDashboardModel();
        view = new AdminDashboardView(this);
        this.userLogged = userLogged;
    }

    // action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // if a certain button is clicked do the actions inside the brackets
        if(e.getActionCommand().equals("admin-profile")){
            // redirects to admin profile page
            adminProfileView = new AdminProfileView(this, this.userLogged);
        }else if(e.getActionCommand().equals("logout")){
            login = new LoginController();
            view.dispose();
        } else if(e.getActionCommand().equals("go-to-art-create")) {
            // redirects to art create page
           artCreateView = new ArtCreateView(this);  
        } else if(e.getActionCommand().equals("create-art")){
            // getting values from view  
            String title = artCreateView.getArtTitle();
            String artistIDString = artCreateView.getArtistID();
            int artistID = Integer.parseInt(artistIDString);
            String type = artCreateView.getArtType();
            // create an instance of the art class with the data collated
            Art newArt = new Art(title, artistID, type);
            // using model to call method
            this.model.createArt(newArt);
            // dispose current view and call a new one to refresh table
            view.dispose();
            view = new AdminDashboardView(this);
            artCreateView.dispose();
        } else if(e.getActionCommand().equals("go-to-artist-create")) {
            // redirects to artist create page
            artistCreateView = new ArtistCreateView(this);  
        } else if(e.getActionCommand().equals("create-artist")){
            // getting values from view  
            String firstName = artistCreateView.getFirstName();
            String lastName = artistCreateView.getLastName();
            String address = artistCreateView.getAddress();
            String website = artistCreateView.getWebsite();
            // Create an instance of the artist class with the data collated
            Artist newArtist = new Artist(firstName, lastName, address, website);
            // cusing model to call method
            this.model.createArtist(newArtist);
            // dispose current view and call a new one to refresh table
            view.dispose();
            view = new AdminDashboardView(this);
            artistCreateView.dispose();
        } else if(e.getActionCommand().equals("go-to-admin-create")) {
            // redirects to artist create page
            adminCreateView = new AdminCreateView(this);
        } else if(e.getActionCommand().equals("create-admin")){
            // getting values from view  
            String firstName = adminCreateView.getFirstName();    
            String lastName = adminCreateView.getLastName();  
            String username = adminCreateView.getUsername();
            String email = adminCreateView.getEmail();  
            String address = adminCreateView.getAddress();  
            String password = adminCreateView.getPassword();
            // Create an instance of the user class with the data collated
            User newUser = new User(firstName, lastName, username, email, address, password, true);
            // using model to call method passing newUser
            this.model.createAdmin(newUser);
            // dispose current view and call a new one to refresh table
            view.dispose();
            view = new AdminDashboardView(this);
            adminCreateView.dispose();
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
            // using model to call method passing user logged and creating a new instance for the user when the profile is edited
            this.model.updateProfile(editUser, userLogged);
            adminProfileView.dispose();
        } else if(e.getActionCommand().equals("update-item")) {
            // call method in view to get selected user in the table
            this.userToEdit = view.getSelectedUser();
            // redirects to view with form to update admin account
            adminUpdateView = new AdminUpdateView(this, userToEdit);
        } else if (e.getActionCommand().equals("update-admin")) {
            // getting values from view
            String firstName = adminUpdateView.getFirstName();    
            String lastName = adminUpdateView.getLastName();  
            String username = adminUpdateView.getUsername();
            String email = adminUpdateView.getEmail();  
            String address = adminUpdateView.getAddress();  
            String password = adminUpdateView.getPassword();
            // Create an instance of the user class with the data collated
            User editUser = new User(firstName, lastName, username, email, address, password, true);
            // calling method from model
            this.model.updateProfile(editUser, this.userToEdit);
            // dispose current view and call a new one to refresh table
            view.dispose();
            view = new AdminDashboardView(this);
            adminUpdateView.dispose();
        } else if (e.getActionCommand().equals("delete-item")) {
            // calling method from view to get user selected
            this.userToBeDeleted = view.getSelectedUser();
            // calling method from model passing User instance
            model.deleteAdmin(userToBeDeleted);
            // dispose and calling a new view to refresh table
            view.dispose();
            view = new AdminDashboardView(this);
        } else if (e.getActionCommand().equals("delete-art")) {
            // calling method from view to get art selected
            this.artToBeDeleted = view.getSelectedArt();
            // calling method from model to delete art
            model.deleteArt(artToBeDeleted);
            // dispose and calling a new view to refresh table
            view.dispose();
            view = new AdminDashboardView(this);
        } else if (e.getActionCommand().equals("delete-artist")) {
            // calling method from view to get artist selected
            this.artistToBeDeleted = view.getSelectedArtist();
            // calling method in model to delete artist
            model.deleteArtist(artistToBeDeleted);
            // dispose and calling a new view to refresh table
            view.dispose();
            view = new AdminDashboardView(this);
        } else if(e.getActionCommand().equals("go-to-art-update")) {
            // calling method from view to get art selected from table
            this.artToEdit = view.getSelectedArt();
            // redirects to art update page
            artUpdateView = new ArtUpdateView(this, artToEdit);
        } else if (e.getActionCommand().equals("update-art")) {
            // getting values from view
            String title = artUpdateView.getArtTitle();
            String artistIDString = artUpdateView.getArtistID();
            int artistID = Integer.parseInt(artistIDString);
            String type = artUpdateView.getArtType();
            // Create an instance of the art class with the data collated
            Art editArt = new Art(title, artistID, type);
            // calling method to update this art
            this.model.updateArt(editArt, this.artToEdit);
            // dispose view and creating a new one to refresh table
            view.dispose();
            view = new AdminDashboardView(this);
            artUpdateView.dispose();
        } else if(e.getActionCommand().equals("go-to-artist-update")) {
            // call method from view to get artist selected
            this.artistToEdit = view.getSelectedArtist();
            // redirects to artist update page
            artistUpdateView = new ArtistUpdateView(this, artistToEdit);
        } else if (e.getActionCommand().equals("update-artist")) {
            // getting values from view
            String firstName = artistUpdateView.getFirstName();
            String lastName = artistUpdateView.getLastName();
            String address = artistUpdateView.getAddress();
            String website = artistUpdateView.getWebsite();
            // Create an instance of the artist class with the data collated
            Artist editArtist = new Artist(firstName, lastName, address, website);
            // call method in model to update artist
            this.model.updateArtist(editArtist, this.artistToEdit);
            // dispose view and creating a new one to refresh table
            view.dispose();
            view = new AdminDashboardView(this);
            artistUpdateView.dispose();
        }
    }   
    
    public AdminDashboardView getView() {
        return this.view;
    }
    
}
