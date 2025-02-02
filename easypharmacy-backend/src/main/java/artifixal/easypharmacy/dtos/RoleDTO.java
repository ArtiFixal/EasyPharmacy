package artifixal.easypharmacy.dtos;

import java.util.Optional;
import lombok.Getter;

/**
 *
 * @author ArtiFixal
 */
@Getter
public class RoleDTO extends BaseDTO<Long>{

    public String name;

    public RoleDTO(Optional<Long> id,String name){
        super(id);
        this.name=name;
    }
}
