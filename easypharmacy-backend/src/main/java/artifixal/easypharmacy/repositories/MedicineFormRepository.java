package artifixal.easypharmacy.repositories;

import artifixal.easypharmacy.entities.MedicineForm;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository related to form of medicine.
 * 
 * @author ArtiFixal
 */
@Repository
public interface MedicineFormRepository extends R2dbcRepository<MedicineForm,Long>{
    
}
