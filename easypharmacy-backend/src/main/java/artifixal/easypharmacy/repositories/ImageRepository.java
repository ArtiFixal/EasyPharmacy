package artifixal.easypharmacy.repositories;

import artifixal.easypharmacy.entities.Image;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repository related to images.
 * 
 * @author ArtiFixal
 */
@Repository
public interface ImageRepository extends R2dbcRepository<Image,Long>{
    
    Mono<Image> findByPath(String path);
}
