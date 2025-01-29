package artifixal.easypharmacy.repositories;

import artifixal.easypharmacy.entities.Category;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository related to category.
 * 
 * @author ArtiFixal
 */
@Repository
public interface CategoryRepository extends R2dbcRepository<Category,Long>{
    
}
