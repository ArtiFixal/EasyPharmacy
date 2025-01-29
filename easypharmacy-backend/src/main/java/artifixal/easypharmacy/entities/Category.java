package artifixal.easypharmacy.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity containing category data.
 * 
 * @author ArtiFixal
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Table("categories")
public class Category extends BaseEntity<Long>{
    
    public final static int MAX_NAME_LENGTH=60;
    
    public String name;

    public Category(){
        super(null);
    }
    
    public Category(Long id,String name){
        super(id);
        this.name=name;
    }    
}
