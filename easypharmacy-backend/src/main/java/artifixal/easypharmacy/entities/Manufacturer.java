package artifixal.easypharmacy.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author Acer
 */
@Table("manufacturers")
@Data
@EqualsAndHashCode(callSuper=true)
public class Manufacturer extends BaseEntity<Long>{
    
    public final static int MAX_NAME_LENGTH=80;
    public String name;

    public Manufacturer(){
        super(0l);
    }
    
    public Manufacturer(Long id,String name){
        super(id);
        this.name=name;
    }
}
