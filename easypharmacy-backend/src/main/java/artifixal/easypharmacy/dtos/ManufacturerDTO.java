package artifixal.easypharmacy.dtos;

import java.util.Optional;
import lombok.Getter;

/**
 * DTO transporting manufacturer data.
 * 
 * @author ArtiFixal
 */
@Getter
public class ManufacturerDTO extends BaseDTO<Long>{
    
    public String name;

    public ManufacturerDTO(){
        super();
    }

    public ManufacturerDTO(Optional<Long> id,String name){
        super(id);
        this.name=name;
    }
}
