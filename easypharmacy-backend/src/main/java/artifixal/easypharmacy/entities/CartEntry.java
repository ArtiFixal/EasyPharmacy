package artifixal.easypharmacy.entities;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity representing single cart entry.
 * 
 * @author ArtiFixal
 */
@Table("carts")
@Data
@EqualsAndHashCode(callSuper=true)
public class CartEntry extends BaseEntity<CartKey> implements Persistable<CartKey>{
    
    @Positive
    private long quantity;

    public CartEntry(CartKey id,long quantity){
        super(id);
        this.quantity=quantity;
    }

    @Override
    public boolean isNew(){
        // Else we will receive constraint violation.
        return true;
    }
}
