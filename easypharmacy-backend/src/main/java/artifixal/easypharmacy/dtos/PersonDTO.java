package artifixal.easypharmacy.dtos;

import java.util.Optional;
import lombok.Getter;

/**
 * Base DTO class for transporting personal data.
 * 
 * @author ArtiFixal
 */
@Getter
public abstract class PersonDTO extends BaseDTO<Long>{
    
    private String email;
    private String firstName;
    private String lastName;
    private Optional<String> password;
    private boolean active;

    public PersonDTO(Optional<Long> id,String email,String firstName,String lastName,boolean active,Optional<String> password){
        super(id);
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.active=active;
        this.password=password;
    }
}
