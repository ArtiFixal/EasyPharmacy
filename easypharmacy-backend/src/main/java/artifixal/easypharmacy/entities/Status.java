package artifixal.easypharmacy.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity containig status data.
 * 
 * @author ArtiFixal
 */
@Table("statuses")
@Data
@EqualsAndHashCode(callSuper=true)
public class Status extends BaseEntity<Long>{

    public String name;

    public Status(Long id,String name){
        super(id);
        this.name=name;
    }
}
