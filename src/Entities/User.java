
package Entities;


// user object
public class User {
    
    
    // properties
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;
    private String password;
    public boolean isAdmin;
    
    public User(String firstName, String lastName, String username, String email, String address, String password, boolean isAdmin){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.address = address;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getFirstName() {
        return firstName;
    }
     public String getLastName(){
        return lastName;
    } 
    public String getUsername() {
        return username;
    }
    public String getEmail(){
        return email;
    }
     public String getAddress(){
        return address;
    } 
    public String getPassword() {
        return password;
    }
    public boolean getIsAdmin() {
        return isAdmin;
    }
}
