package artifixal.easypharmacy.repositories;

import artifixal.easypharmacy.entities.Address;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository related to addresses.
 * 
 * @author ArtiFixal
 */
@Repository
public interface AddressRepository extends R2dbcRepository<Address,Long>{

}
