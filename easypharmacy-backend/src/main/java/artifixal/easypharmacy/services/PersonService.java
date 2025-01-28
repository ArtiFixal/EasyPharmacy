package artifixal.easypharmacy.services;

import artifixal.easypharmacy.auth.AuthCredentialsDTO;
import artifixal.easypharmacy.auth.AuthenticationException;
import artifixal.easypharmacy.auth.JWTAuthResponseDTO;
import artifixal.easypharmacy.auth.jwt.JwtService;
import artifixal.easypharmacy.dtos.PersonDTO;
import artifixal.easypharmacy.entities.Person;
import artifixal.easypharmacy.exceptions.EntityNotFoundException;
import artifixal.easypharmacy.exceptions.InvalidDTOException;
import artifixal.easypharmacy.repositories.common.PersonRepository;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * Base class for services using personal data.
 * 
 * @author ArtiFixal
 * 
 * @param <ET> Type of person
 * @param <DTO> DTO Type of person
 */
public abstract class PersonService<ET extends Person,DTO extends PersonDTO> extends BaseService<PersonRepository<ET>,ET,Long,DTO>{
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    protected PasswordEncoder passwordEncoder;
    
    @Autowired
    protected R2dbcEntityTemplate template;
    
    public abstract void enableUser(String user);
    public abstract void disableUser(String user);
    
    public Mono<ET> getUserEntityByEmail(String email){
        return repo.findUserByEmail(email)
            .switchIfEmpty(Mono.error(new EntityNotFoundException(entityName,email)));
    }
    
    protected Mono<Boolean> verifyLogin(AuthCredentialsDTO userAuth){
        return repo.findUserByEmail(userAuth.getEmail())
            .switchIfEmpty(Mono.error(new AuthenticationException("Failed to authenticate user")))
            .map((user)->passwordEncoder.matches(userAuth.getPassword(),user.getPassword()));
    }
    
    public Mono<ResponseEntity<JWTAuthResponseDTO>> login(AuthCredentialsDTO userAuth){
        return verifyLogin(userAuth).map((success)->{
            if(!success)
                throw new AuthenticationException("Invalid credentials");
            String token=jwtService.generateToken(userAuth.getEmail());
            return ResponseEntity.ok(new JWTAuthResponseDTO("Success",Optional.of(token)));
        }).onErrorReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new JWTAuthResponseDTO("Invalid credentials",Optional.empty()))
        );
    }
    
    /**
     * Checks if DTO contains password and encodes it.
     * 
     * @param data DTO containing password.
     * 
     * @return Encoded password.
     * @throws InvalidDTOException If password doesn't exits.
     */
    public String getEncodedPassword(PersonDTO data){
        String password=data.getPassword().orElseThrow(()->
            new InvalidDTOException("User password not found"));
        return passwordEncoder.encode(password);
    }
    
    protected void updatePersonalData(DTO entityNewData,ET entity){
        entity.setEmail(entityNewData.getEmail());
        entity.setFirstName(entityNewData.getFirstName());
        entity.setLastName(entityNewData.getLastName());
        entity.setActive(entityNewData.isActive());
    }
}
