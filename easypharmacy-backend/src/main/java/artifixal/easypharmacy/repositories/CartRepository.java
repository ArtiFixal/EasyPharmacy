package artifixal.easypharmacy.repositories;

import artifixal.easypharmacy.entities.CartEntry;
import artifixal.easypharmacy.entities.CartKey;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository related to cart entries.
 * 
 * @author ArtiFixal
 */
@Repository
public interface CartRepository extends R2dbcRepository<CartEntry,CartKey>{

}
