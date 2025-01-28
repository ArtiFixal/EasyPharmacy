package artifixal.easypharmacy.dtos;

import java.util.Optional;
import lombok.Getter;

/**
 * DTO transporting employee user.
 * 
 * @author ArtiFixal
 */
@Getter
public class EmployeeDTO extends PersonDTO{

    public EmployeeDTO(Optional<Long> id,String email,String firstName,String lastName,boolean active,Optional<String> password){
        super(id,email,firstName,lastName,active,password);
    }
}
