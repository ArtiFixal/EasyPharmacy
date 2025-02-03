package artifixal.easypharmacy.entities;

import java.sql.Timestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity representing single user purchase.
 * 
 * @author ArtiFixal
 */
@Table("sales")
@Data
@EqualsAndHashCode(callSuper=true)
public class Sale extends BaseEntity<Long>{

    private Long clientID;
    private Long addressID;
    private Timestamp purchased;
    private Timestamp finalised;
    private Long statusID;

    public Sale(Long id,Long clientID,Long addressID,Timestamp purchased,Timestamp finalised,Long statusID){
        super(id);
        this.clientID=clientID;
        this.addressID=addressID;
        this.purchased=purchased;
        this.finalised=finalised;
        this.statusID=statusID;
    }
}
