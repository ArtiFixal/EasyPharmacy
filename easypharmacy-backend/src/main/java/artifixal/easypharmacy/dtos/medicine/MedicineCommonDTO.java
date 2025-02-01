package artifixal.easypharmacy.dtos.medicine;

import artifixal.easypharmacy.dtos.BaseDTO;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.Getter;

/**
 * Base class for Medicine DTOs.
 * 
 * @author ArtiFixal
 */
@Getter
public abstract class MedicineCommonDTO extends BaseDTO<Long>{
    
    public String name;
    private Long manufacturerID;
    private Long categoryID;
    private Long medicineFormID;
    public BigDecimal price;
    private boolean receiptRequired;

    public MedicineCommonDTO(Optional<Long> id,String name,Long manufacturerID,Long categoryID,Long medicineFormID,BigDecimal price,boolean receiptRequired){
        super(id);
        this.name=name;
        this.manufacturerID=manufacturerID;
        this.categoryID=categoryID;
        this.medicineFormID=medicineFormID;
        this.price=price;
        this.receiptRequired=receiptRequired;
    }
}
