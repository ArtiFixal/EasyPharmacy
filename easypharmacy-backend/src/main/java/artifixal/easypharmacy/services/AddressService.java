package artifixal.easypharmacy.services;

import artifixal.easypharmacy.dtos.AddressDTO;
import artifixal.easypharmacy.entities.Address;
import artifixal.easypharmacy.repositories.AddressRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service related to addresses.
 * 
 * @author ArtiFixal
 */
@Service
public class AddressService extends BaseService<AddressRepository,Address,Long,AddressDTO>{

    @Override
    protected void updateEntityValues(AddressDTO entityNewData,Address entity){
        entity.setStreet(entityNewData.getStreet());
        entity.setPostalCode(entityNewData.getPostalCode());
        entity.setCity(entityNewData.getCity());
    }

    @Override
    protected AddressDTO convertEntityToDto(Address entity){
        return new AddressDTO(Optional.of(entity.getId()),
            entity.getStreet(),
            entity.getPostalCode(),
            entity.getCity()
        );
    }

    @Override
    protected Address convertDtoToEntity(AddressDTO entityData){
        return new Address(null,
            entityData.getStreet(),
            entityData.getPostalCode(),
            entityData.getCity()
        );
    }
}
