
package Entities;


// user object
public class User {
    
    
    // properties
    private int userID;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;
    private String password;
    private boolean isAdmin;
    
    // 2 ways to create an instance of this class
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String username, String email, String address, String password, boolean isAdmin){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.address = address;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
    public int getUserID() {
        return userID;
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

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
