package artifixal.easypharmacy.repositories.common;

import artifixal.easypharmacy.entities.Person;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

/**
 * Repository related to person.
 * 
 * @author ArtiFixal
 * @param <T> Person type.
 */
public interface PersonRepository<T extends Person> extends R2dbcRepository<T,Long>{
    
    Mono<T> findUserByEmail(String email);
}
