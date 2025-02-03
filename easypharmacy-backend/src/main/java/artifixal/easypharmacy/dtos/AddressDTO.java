package artifixal.easypharmacy.dtos;

import java.util.Optional;
import lombok.Getter;

/**
 * DTO transporting data about address.
 * 
 * @author ArtiFixal
 */
@Getter
public class AddressDTO extends BaseDTO<Long>{
    
    private String street;
    private String postalCode;
    private String city;

    public AddressDTO(Optional<Long> id,String street,String postalCode,String city){
        super(id);
        this.street=street;
        this.postalCode=postalCode;
        this.city=city;
    }
}
