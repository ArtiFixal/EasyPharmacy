package artifixal.easypharmacy.auth;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO used to transport JWT.
 * 
 * @author ArtiFixal 
 */
@Getter
@AllArgsConstructor
public class JWTAuthResponseDTO {
    
    /**
     * Short status or error description.
     */
    private String message;
    private Optional<String> token;
}
