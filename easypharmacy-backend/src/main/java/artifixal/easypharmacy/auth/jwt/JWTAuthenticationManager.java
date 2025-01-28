package artifixal.easypharmacy.auth.jwt;

import artifixal.easypharmacy.entities.Employee;
import artifixal.easypharmacy.entities.Person;
import artifixal.easypharmacy.entities.Role;
import artifixal.easypharmacy.services.ClientService;
import artifixal.easypharmacy.services.EmployeeService;
import io.jsonwebtoken.JwtException;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 *
 * @author ArtiFixal
 */
@Component
@AllArgsConstructor
public class JWTAuthenticationManager implements ReactiveAuthenticationManager {
    
    private EmployeeService employeeService;
    private ClientService clientService;
    
    @Override
    public Mono<Authentication> authenticate(Authentication authentication){
        String token=(String)authentication.getCredentials();
        return Mono.justOrEmpty(authentication)
            .cast(UsernamePasswordAuthenticationToken.class)
            .map((auth)->(String)auth.getPrincipal())
            .flatMap((email)->{
                return clientService.getUserEntityByEmail(email)
                    .ofType(Person.class).switchIfEmpty(employeeService.getUserEntityByEmail(email))
                    .switchIfEmpty(Mono.error(new JwtException("Invalid credentials")))
                    .map((person)->{
                        if(person instanceof Employee)
                            return new UsernamePasswordAuthenticationToken(email,token,Collections.singleton(new SimpleGrantedAuthority(Role.ADMIN.getValue())));
                        else
                            return new UsernamePasswordAuthenticationToken(email,token,Collections.singleton(new SimpleGrantedAuthority(Role.CLIENT.getValue())));
                    });
            });
    }
}
