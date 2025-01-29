package artifixal.easypharmacy.services;

import artifixal.easypharmacy.dtos.CategoryDTO;
import artifixal.easypharmacy.entities.Category;
import artifixal.easypharmacy.repositories.CategoryRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service related to categories.
 * 
 * @author ArtiFixal
 */
@Service
public class CategoryService extends BaseService<CategoryRepository,Category,Long,CategoryDTO>{

    @Override
    protected void updateEntityValues(CategoryDTO entityNewData,Category entity){
        entity.setName(entityNewData.getName());
    }

    @Override
    protected CategoryDTO convertEntityToDto(Category entity){
        return new CategoryDTO(Optional.of(entity.getId()),entity.getName());
    }

    @Override
    protected Category convertDtoToEntity(CategoryDTO entityData){
        return new Category(null,entityData.getName());
    }
}
