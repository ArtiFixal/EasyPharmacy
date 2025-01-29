package artifixal.easypharmacy.entities;

import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author Acer
 */
@Table("medicines")
@Data
@EqualsAndHashCode(callSuper=true)
public class Medicine extends BaseEntity<Long>{
    
    public final static int MAX_NAME_LENGTH=120;
    
    public String name;
    public Long manufacturerID;
    public Long categoryID;
    public Long formID;
    private Long imageID;
    public BigDecimal price;
    private boolean prescriptionRequired;
    
    public Medicine(){
        super(null);
    }

    public Medicine(Long id,String name,Long manufacturerID,Long categoryID,Long formID,Long imageID,BigDecimal price,boolean receiptRequired){
        super(id);
        this.name=name;
        this.manufacturerID=manufacturerID;
        this.categoryID=categoryID;
        this.formID=formID;
        this.imageID=imageID;
        this.price=price;
        this.prescriptionRequired=receiptRequired;
    }
}
