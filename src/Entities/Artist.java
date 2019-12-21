
package Entities;


public class Artist {
    
     // properties
    private int artistID;
    private String firstName;
    private String lastName;
    private String address;
    private String website;
    
    // constructors
    public Artist(String firstName, String lastName, String address, String website){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.website = website;
    }
    public Artist() {
        
    }

    // getters
    public int getArtistID() {
       return this.artistID;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
     public String getLastName(){
        return this.lastName;
    } 
      public String getAddress(){
        return this.address;
    }  
       public String getWebsite(){
        return this.website;
    }

    //setters 
     public void setArtistID(int artistID) {
        this.artistID = artistID;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    
}
