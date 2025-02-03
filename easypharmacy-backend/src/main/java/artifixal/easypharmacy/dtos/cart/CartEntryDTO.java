package artifixal.easypharmacy.dtos.cart;

import artifixal.easypharmacy.dtos.BaseDTO;
import artifixal.easypharmacy.entities.CartKey;
import java.util.Optional;
import lombok.Getter;

/**
 * DTO transportiong data of single cart entry with saleID.
 * 
 * @author ArtiFixal
 */
@Getter
public class CartEntryDTO extends BaseDTO<CartKey>{
    
    private Long quantity;

    public CartEntryDTO(Optional<CartKey> id,Long quantity){
        super(id);
        this.quantity=quantity;
    }
}
