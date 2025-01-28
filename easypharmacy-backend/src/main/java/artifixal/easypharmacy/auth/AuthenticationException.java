package artifixal.easypharmacy.auth;

/**
 *
 * @author ArtiFixal
 */
public class AuthenticationException extends org.springframework.security.core.AuthenticationException{

    public AuthenticationException(String msg){
        super(msg);
    }
}
