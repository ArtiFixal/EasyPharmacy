package artifixal.easypharmacy.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Key of the {@link CartEntry} entity.
 * 
 * @author ArtiFixal
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class CartKey {
    private Long saleID;
    private Long medicineID;
}
