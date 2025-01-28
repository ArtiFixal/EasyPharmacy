package artifixal.easypharmacy.entities;

import org.springframework.data.relational.core.mapping.Table;

/**
 * Client entity.
 *
 * @author ArtiFixal
 */
@Table("clients")
public class Client extends Person{
    
    public Client(Long id,String firstName,String lastName,String email,String password,boolean active){
        super(id,firstName,lastName,email,password,active);
    }
}
