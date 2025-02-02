package artifixal.easypharmacy.entities;

/**
 * Available static roles.
 * 
 * @author ArtiFixal
 */
public enum Role{
    CLIENT("CLIENT"),
    ADMIN("ADMIN");
    
    private String value;

    private Role(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
