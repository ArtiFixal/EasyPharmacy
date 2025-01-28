package artifixal.easypharmacy.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO transporing user login attempt.
 * 
 * @author ArtiFixal
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCredentialsDTO {
    
    @Email
    private String email;
    
    @NotBlank
    private String password;
}
