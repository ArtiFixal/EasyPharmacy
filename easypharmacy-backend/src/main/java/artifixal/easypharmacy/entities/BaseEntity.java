package artifixal.easypharmacy.entities;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Base class for entities.
 * 
 * @author ArtiFixal
 * @param <T> Entity ID type.
 */
@Data
public abstract class BaseEntity<T> implements Serializable{
    
    @Id
    private T id;

    public BaseEntity(T id){
        this.id=id;
    }
}
