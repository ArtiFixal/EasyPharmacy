package artifixal.easypharmacy.services;

import artifixal.easypharmacy.dtos.StatusDTO;
import artifixal.easypharmacy.entities.Status;
import artifixal.easypharmacy.repositories.StatusRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service related to statuses.
 * 
 * @author ArtiFixal
 */
@Service
public class StatusService extends BaseService<StatusRepository,Status,Long,StatusDTO>{

    @Override
    protected void updateEntityValues(StatusDTO entityNewData,Status entity){
        entity.setName(entityNewData.getName());
    }

    @Override
    protected StatusDTO convertEntityToDto(Status entity){
        return new StatusDTO(Optional.of(entity.getId()),entity.getName());
    }

    @Override
    protected Status convertDtoToEntity(StatusDTO entityData){
        return new Status(null,entityData.getName());
    }
}
