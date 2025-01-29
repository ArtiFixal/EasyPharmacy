package artifixal.easypharmacy.services;

import artifixal.easypharmacy.dtos.MedicineFormDTO;
import artifixal.easypharmacy.entities.MedicineForm;
import artifixal.easypharmacy.repositories.MedicineFormRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service related to form of medicine.
 * 
 * @author ArtiFixal
 */
@Service
public class MedicineFormService extends BaseService<MedicineFormRepository,MedicineForm,Long,MedicineFormDTO>{

    @Override
    protected void updateEntityValues(MedicineFormDTO entityNewData,MedicineForm entity){
        entity.setName(entityNewData.getName());
    }

    @Override
    protected MedicineFormDTO convertEntityToDto(MedicineForm entity){
        return new MedicineFormDTO(Optional.of(entity.getId()),entity.getName());
    }

    @Override
    protected MedicineForm convertDtoToEntity(MedicineFormDTO entityData){
        return new MedicineForm(null,entityData.getName());
    }    
}
