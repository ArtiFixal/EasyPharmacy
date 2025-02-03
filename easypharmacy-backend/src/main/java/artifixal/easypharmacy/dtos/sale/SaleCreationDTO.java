package artifixal.easypharmacy.dtos.sale;

import artifixal.easypharmacy.dtos.AddressDTO;
import artifixal.easypharmacy.dtos.cart.CartEntryCreationDTO;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO transporting data used to create single user purchase.
 * 
 * @author ArtiFixal
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleCreationDTO {
    private Long clientID;
    private AddressDTO address;
    private Set<CartEntryCreationDTO> cart;
}
