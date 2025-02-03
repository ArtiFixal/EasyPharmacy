package artifixal.easypharmacy.dtos.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO transpotring single cart entry send by user to create sale.
 * 
 * @author ArtiFixal
 */
@Getter
@AllArgsConstructor
public class CartEntryCreationDTO {
    private Long medicineID;
    private Long quantity;
}
