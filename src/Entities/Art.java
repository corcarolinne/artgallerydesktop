
package Entities;

public class Art {
    
    // properties
    private String title;
    private int artistID;
    private String type;
    
    public Art(String title, int artistID, String type){
        this.title = title;
        this.artistID = artistID;
        this.type = type;
       
    }

    public String getTitle() {
        return title;
    }
     public int getArtistID(){
        return artistID;
    } 
    public String getType() {
        return type;
    }
    
}
