
package Entities;

public class Art {
    
    // properties
    private int artID;
    private String title;
    private int artistID;
    private String artistFirstName;
    private String artistLastName;
    private String type;
    
    // constructors
    public Art(String title, int artistID, String type){
        this.title = title;
        this.artistID = artistID;
        this.type = type;
    }
    public Art() {
        
    }

    // getters
    public int getArtID() {
       return this.artID;
    }
    
    public String getTitle() {
        return this.title;
    }
     public int getArtistID(){
        return artistID;
    } 
      public String getArtistFirstName(){
        return this.artistFirstName;
    }  
       public String getArtistLastName(){
        return this.artistLastName;
    }
    public String getArtType() {
        return this.type;
    }

    //setters 
     public void setArtID(int artID) {
        this.artID = artID;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public void setArtType(String type) {
        this.type = type;
    }

    public void setArtistFirstName(String artistFirstName) {
        this.artistFirstName = artistFirstName;
    }

    public void setArtistLastName(String artistLastName) {
       this.artistLastName = artistLastName;
    }
    
}
