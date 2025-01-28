package artifixal.easypharmacy.repositories;

import artifixal.easypharmacy.repositories.common.PersonRepository;
import artifixal.easypharmacy.entities.Client;
import org.springframework.stereotype.Repository;

/**
 * Repository related to clients.
 * 
 * @author ArtiFixal
 */
@Repository
public interface ClientRepository extends PersonRepository<Client>{

}
