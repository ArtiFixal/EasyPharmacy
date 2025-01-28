package artifixal.easypharmacy.dtos;

import java.util.Optional;

/**
 * DTO transporting client user.
 * 
 * @author ArtiFixal
 */
public class ClientDTO extends PersonDTO{
    
    public ClientDTO(Optional<Long> id,String email,String firstName,String lastName,boolean active,Optional<String> password){
        super(id,email,firstName,lastName,active,password);
    }
}
