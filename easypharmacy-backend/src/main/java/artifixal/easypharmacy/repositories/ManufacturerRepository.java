package artifixal.easypharmacy.repositories;

import artifixal.easypharmacy.entities.Manufacturer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository related to manufacturer.
 * 
 * @author ArtiFixal
 */
@Repository
public interface ManufacturerRepository extends R2dbcRepository<Manufacturer,Long>{
    
}
