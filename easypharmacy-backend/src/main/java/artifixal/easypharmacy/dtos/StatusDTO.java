package artifixal.easypharmacy.dtos;

import java.util.Optional;
import lombok.Getter;

/**
 * DTO transporting sale status.
 * 
 * @author ArtiFixal
 */
@Getter
public class StatusDTO extends BaseDTO<Long>{

    public String name;

    public StatusDTO(Optional<Long> id,String name){
        super(id);
        this.name=name;
    }
}
