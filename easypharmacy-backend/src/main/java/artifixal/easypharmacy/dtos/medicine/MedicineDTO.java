package artifixal.easypharmacy.dtos.medicine;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.Getter;

/**
 * DTO transporting medicine data. Describes single medicine. 
 * Contains image url.
 * 
 * @author ArtiFixal
 */
@Getter
public class MedicineDTO extends MedicineCommonDTO{
    
    private String imageUrl;

    public MedicineDTO(Optional<Long> id,String name,Long manufacturer,Long category,Long form,BigDecimal price,boolean receiptRequired,String imageUrl){
        super(id,name,manufacturer,category,form,price,receiptRequired);
        this.imageUrl=imageUrl;
    }
}
