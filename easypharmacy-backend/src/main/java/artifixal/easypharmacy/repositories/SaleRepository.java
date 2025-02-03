package artifixal.easypharmacy.repositories;

import artifixal.easypharmacy.entities.Sale;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository related to sales.
 * 
 * @author ArtiFixal
 */
@Repository
public interface SaleRepository extends R2dbcRepository<Sale,Long>{

}
