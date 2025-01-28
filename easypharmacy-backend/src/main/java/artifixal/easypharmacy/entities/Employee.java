package artifixal.easypharmacy.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Employee entity.
 * 
 * @author ArtiFixal
 */
@Table("employees")
@Data
@EqualsAndHashCode(callSuper=true)
public class Employee extends Person{

    public Employee(Long id,String firstName,String lastName,String email,String password,boolean active){
        super(id,firstName,lastName,email,password,active);
    }
}
