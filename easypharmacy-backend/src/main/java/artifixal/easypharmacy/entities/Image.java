package artifixal.easypharmacy.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity containing data about uploaded image.
 * 
 * @author ArtiFixal
 */
@Table("images")
@Data
@EqualsAndHashCode(callSuper=true)
public class Image extends BaseEntity<Long>{
    public final static int MAX_PATH_LENGTH=256;
    
    private String path;

    public Image(){
        super(null);
    }

    public Image(Long id,String path){
        super(id);
        this.path=path;
    }
}
