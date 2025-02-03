package artifixal.easypharmacy.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity representing single address.
 * 
 * @author ArtiFixal
 */
@Table("addresses")
@Data
@EqualsAndHashCode(callSuper=true)
public class Address extends BaseEntity<Long>{
    
    public String street;
    public String postalCode;
    public String city;

    public Address(Long id,String street,String postalCode,String city){
        super(id);
        this.street=street;
        this.postalCode=postalCode;
        this.city=city;
    }
}
