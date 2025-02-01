package artifixal.easypharmacy.dtos.medicine;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.Getter;
import org.springframework.http.codec.multipart.FilePart;

/**
 * Dto used to create Medicine Entity. Handles image as a {@link FilePart}.
 * 
 * @author ArtiFixal
 */
@Getter
public class MedicineCreationDTO extends MedicineCommonDTO{
    
    private FilePart image;

    public MedicineCreationDTO(Optional<Long> id,String name,Long manufacturerID,Long categoryID,Long medicineFormID,BigDecimal price,boolean receiptRequired,FilePart image){
        super(id,name,manufacturerID,categoryID,medicineFormID,price,receiptRequired);
        this.image=image;
    }
}
