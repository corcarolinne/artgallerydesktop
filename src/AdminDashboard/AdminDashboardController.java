
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
    
    // This class has to have a reference to all elements of the program
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
        }else if(e.getActionCommand().equals("logout")){
            login = new LoginController();
            view.dispose();
        }
        else if(e.getActionCommand().equals("go-to-art-create")) {
           artCreateView = new ArtCreateView(this);  
        }  if(e.getActionCommand().equals("create-art")){
        // getting values from view  
        String title = artCreateView.getArtTitle();
        String artistIDString = artCreateView.getArtistID();
        int artistID = Integer.parseInt(artistIDString);
        String type = artCreateView.getArtType();

        // Create an instance of the user class with the data collated
        Art newArt = new Art(title, artistID, type);
        
        // create a model for 
        this.model.createArt(newArt);
        view.dispose();
        view = new AdminDashboardView(this);
        artCreateView.dispose();
        
        
            
        } else if(e.getActionCommand().equals("go-to-artist-create")) {
           artistCreateView = new ArtistCreateView(this);  
        
        }  if(e.getActionCommand().equals("create-artist")){
        // getting values from view  
        String firstName = artistCreateView.getFirstName();
        String lastName = artistCreateView.getLastName();
        String address = artistCreateView.getAddress();
        String website = artistCreateView.getWebsite();

        // Create an instance of the user class with the data collated
        Artist newArtist = new Artist(firstName, lastName, address, website);
        
        // create a model for 
        this.model.createArtist(newArtist);
        view.dispose();
        view = new AdminDashboardView(this);
        artistCreateView.dispose();
        
        
            
        } else if(e.getActionCommand().equals("go-to-admin-create")) {
           adminCreateView = new AdminCreateView(this);
        } if(e.getActionCommand().equals("create-admin")){
        // getting values from view  
        // getting values from view  
        String firstName = adminCreateView.getFirstName();    
        String lastName = adminCreateView.getLastName();  
        String username = adminCreateView.getUsername();
        String email = adminCreateView.getEmail();  
        String address = adminCreateView.getAddress();  
        String password = adminCreateView.getPassword();

        // Create an instance of the user class with the data collated
        User newUser = new User(firstName, lastName, username, email, address, password, true);
        
         // create a model for 
        this.model.createAdmin(newUser);
        view.dispose();
        view = new AdminDashboardView(this);
        adminCreateView.dispose();
        }
        else if (e.getActionCommand().equals("update-profile")) {
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
             view.dispose();
            view = new AdminDashboardView(this);
        } else if (e.getActionCommand().equals("delete-art")) {
            this.artToBeDeleted = view.getSelectedArt();
            model.deleteArt(artToBeDeleted);
             view.dispose();
            view = new AdminDashboardView(this);
        } else if (e.getActionCommand().equals("delete-artist")) {
            this.artistToBeDeleted = view.getSelectedArtist();
            model.deleteArtist(artistToBeDeleted);
            view.dispose();
            view = new AdminDashboardView(this);
        } else if(e.getActionCommand().equals("go-to-art-update")) {
            this.artToEdit = view.getSelectedArt();
            artUpdateView = new ArtUpdateView(this, artToEdit);
        } else if (e.getActionCommand().equals("update-art")) {
            String title = artUpdateView.getArtTitle();
            String artistIDString = artUpdateView.getArtistID();
            int artistID = Integer.parseInt(artistIDString);
            String type = artUpdateView.getArtType();

            // Create an instance of the user class with the data collated
            Art editArt = new Art(title, artistID, type);
            
            this.model.updateArt(editArt, this.artToEdit);
            view.dispose();
            view = new AdminDashboardView(this);
            artUpdateView.dispose();
        } else if(e.getActionCommand().equals("go-to-artist-update")) {
            this.artistToEdit = view.getSelectedArtist();
            artistUpdateView = new ArtistUpdateView(this, artistToEdit);
        } else if (e.getActionCommand().equals("update-artist")) {
            String firstName = artistUpdateView.getFirstName();
            String lastName = artistUpdateView.getLastName();
            String address = artistUpdateView.getAddress();
            String website = artistUpdateView.getWebsite();

            // Create an instance of the user class with the data collated
            Artist editArtist = new Artist(firstName, lastName, address, website);
            
            this.model.updateArtist(editArtist, this.artistToEdit);
            view.dispose();
            view = new AdminDashboardView(this);
            artistUpdateView.dispose();
        }
    }   
    
    public AdminDashboardView getView() {
        return this.view;
    }
    
}
