package artifixal.easypharmacy.repositories;

import artifixal.easypharmacy.repositories.common.PersonRepository;
import artifixal.easypharmacy.entities.Employee;
import org.springframework.stereotype.Repository;

/**
 * Repository related to employees.
 * 
 * @author ArtiFixal
 */
@Repository
public interface EmployeeRepository extends PersonRepository<Employee>{

}
