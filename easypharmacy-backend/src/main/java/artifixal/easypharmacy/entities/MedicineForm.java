package artifixal.easypharmacy.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity containing data about form of medicine.
 * 
 * @author ArtiFixal
 */
@Table("medicine_forms")
@Data
@EqualsAndHashCode(callSuper=true)
public class MedicineForm extends BaseEntity<Long>{
    public final static int MAX_NAME_LENGTH=40;

    public String name;

    public MedicineForm(Long id,String name){
        super(id);
        this.name=name;
    }
}
