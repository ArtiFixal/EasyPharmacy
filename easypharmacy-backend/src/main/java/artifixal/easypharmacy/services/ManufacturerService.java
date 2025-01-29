package artifixal.easypharmacy.services;

import artifixal.easypharmacy.dtos.ManufacturerDTO;
import artifixal.easypharmacy.entities.Manufacturer;
import artifixal.easypharmacy.repositories.ManufacturerRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service related to manufacturer.
 * 
 * @author ArtiFixal
 */
@Service
public class ManufacturerService extends BaseService<ManufacturerRepository,Manufacturer,Long,ManufacturerDTO>{

    @Override
    protected void updateEntityValues(ManufacturerDTO entityNewData,Manufacturer entity){
        entity.setName(entityNewData.getName());
    }

    @Override
    protected ManufacturerDTO convertEntityToDto(Manufacturer entity){
        return new ManufacturerDTO(Optional.of(entity.getId()),entity.getName());
    }

    @Override
    protected Manufacturer convertDtoToEntity(ManufacturerDTO entityData){
        return new Manufacturer(null,entityData.getName());
    }
}
