package artifixal.easypharmacy.dtos;

import java.util.Optional;
import lombok.Getter;
import lombok.ToString;

/**
 * DTO transporting category data.
 * 
 * @author ArtiFixal
 */
@Getter
@ToString
public class CategoryDTO extends BaseDTO<Long>{
    
    public String name;

    public CategoryDTO(Optional<Long> id,String name){
        super(id);
        this.name=name;
    }
}
