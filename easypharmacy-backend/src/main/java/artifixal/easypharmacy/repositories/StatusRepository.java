package artifixal.easypharmacy.repositories;

import artifixal.easypharmacy.entities.Status;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository related to statuses.
 * 
 * @author ArtiFixal
 */
@Repository
public interface StatusRepository extends R2dbcRepository<Status,Long>{

}
