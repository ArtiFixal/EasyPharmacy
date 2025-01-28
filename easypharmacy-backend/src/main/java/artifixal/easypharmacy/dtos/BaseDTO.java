package artifixal.easypharmacy.dtos;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Base class for DTOs.
 * 
 * @author ArtiFixal
 * @param <T> DTO ID type
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDTO<T>{
    
    private Optional<T> id;
}
