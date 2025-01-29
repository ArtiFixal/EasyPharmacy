package artifixal.easypharmacy.dtos;

import java.util.Optional;
import lombok.Getter;

/**
 * DTO transporting form of medicine data.
 * 
 * @author ArtiFixal
 */
@Getter
public class MedicineFormDTO extends BaseDTO<Long>{
    
    public String name;

    public MedicineFormDTO(Optional<Long> id,String name){
        super(id);
        this.name=name;
    }
}
