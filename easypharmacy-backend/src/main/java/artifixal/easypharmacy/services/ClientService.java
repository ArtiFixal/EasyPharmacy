package artifixal.easypharmacy.services;

import artifixal.easypharmacy.dtos.ClientDTO;
import artifixal.easypharmacy.entities.Client;
import java.util.Optional;
import org.springframework.data.relational.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * Service related to clients.
 * 
 * @author ArtiFixal
 */
@Service
public class ClientService extends PersonService<Client,ClientDTO>{

    @Override
    protected void updateEntityValues(ClientDTO entityNewData,Client entity){
        updatePersonalData(entityNewData,entity);
    }

    @Override
    protected ClientDTO convertEntityToDto(Client entity){
        return new ClientDTO(Optional.of(entity.getId()),
            entity.getEmail(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.isActive(),
            Optional.empty()
        );
    }

    @Override
    protected Client convertDtoToEntity(ClientDTO entityData){
        return new Client(null,
            entityData.getFirstName(),
            entityData.getLastName(),
            entityData.getEmail(),
            getEncodedPassword(entityData),
            false);
    }

    @Override
    public void enableUser(String user){
        template.update(Client.class).apply(Update.update("active",true));
    }

    @Override
    public void disableUser(String user){
        template.update(Client.class).apply(Update.update("active",false));
    }

}
