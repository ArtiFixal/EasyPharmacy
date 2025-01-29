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
    private Long manufacturer;
    private Long category;
    private Long form;
    public BigDecimal price;
    private boolean receiptRequired;

    public MedicineCommonDTO(Optional<Long> id,String name,Long manufacturer,Long category,Long form,BigDecimal price,boolean receiptRequired){
        super(id);
        this.name=name;
        this.manufacturer=manufacturer;
        this.category=category;
        this.form=form;
        this.price=price;
        this.receiptRequired=receiptRequired;
    }
}
