package artifixal.easypharmacy.services;

import artifixal.easypharmacy.dtos.EmployeeDTO;
import artifixal.easypharmacy.entities.Employee;
import java.util.Optional;
import org.springframework.data.relational.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * Service related to employees.
 * 
 * @author ArtiFixal
 */
@Service
public class EmployeeService extends PersonService<Employee,EmployeeDTO>{

    @Override
    protected void updateEntityValues(EmployeeDTO entityNewData,Employee entity){
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected EmployeeDTO convertEntityToDto(Employee entity){
        return new EmployeeDTO(Optional.of(entity.getId()),
            entity.getEmail(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.isActive(),
            Optional.empty()
        );
    }

    @Override
    protected Employee convertDtoToEntity(EmployeeDTO entityData){
        return new Employee(null,
            entityData.getFirstName(),
            entityData.getLastName(),
            entityData.getEmail(),
            getEncodedPassword(entityData),
            false
        );
    }

    @Override
    public void enableUser(String user){
        template.update(Employee.class).apply(Update.update("active",true));
    }

    @Override
    public void disableUser(String user){
        template.update(Employee.class).apply(Update.update("active",false));
    }
}
