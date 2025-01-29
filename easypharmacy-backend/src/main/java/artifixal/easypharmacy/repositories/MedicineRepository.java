package artifixal.easypharmacy.repositories;

import artifixal.easypharmacy.entities.Medicine;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository related to medicines.
 * 
 * @author ArtiFixal
 */
@Repository
public interface MedicineRepository extends R2dbcRepository<Medicine,Long>{
    
}
