package artifixal.easypharmacy.dtos.medicine;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.Getter;

/**
 * Dto used to create Medicine Entity. Handles image as a {@link FilePart}.
 * 
 * @author ArtiFixal
 */
@Getter
public class MedicineCreationDTO extends MedicineCommonDTO{
    
    private Optional<Long> imageID;

    public MedicineCreationDTO(Optional<Long> id,String name,Long manufacturerID,Long categoryID,Long medicineFormID,BigDecimal price,boolean receiptRequired,Optional<Long> imageID){
        super(id,name,manufacturerID,categoryID,medicineFormID,price,receiptRequired);
        this.imageID=imageID;
    }
}
