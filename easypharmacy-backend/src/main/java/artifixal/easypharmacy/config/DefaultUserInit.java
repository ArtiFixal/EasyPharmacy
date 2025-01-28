package artifixal.easypharmacy.config;

import artifixal.easypharmacy.dtos.ClientDTO;
import artifixal.easypharmacy.dtos.EmployeeDTO;
import artifixal.easypharmacy.services.ClientService;
import artifixal.easypharmacy.services.EmployeeService;
import java.time.Duration;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reactor.core.publisher.Mono;

/**
 * Inits default users for testing purposes.
 * 
 * @author ArtiFixal
 */
@Profile("dev")
@Configuration
@AllArgsConstructor
public class DefaultUserInit {
    
    private final ClientService clientService;
    private final EmployeeService employeeService;
    
    @Bean
    InitializingBean insertUsers(){
        return ()->{
            try{
                initUsers();
            }catch(Exception e){
                System.out.println(e);
                Mono.delay(Duration.ofSeconds(60))
                    .doOnTerminate(()->{initUsers();});
            }
        };
    }
    
    private void initUsers(){
        if(clientService.getEntityCount().block()<=0){
                ClientDTO user=new ClientDTO(Optional.empty(),"user","Joe","Doe",true,Optional.of("user"));
                clientService.addEntity(user).block();
            }
            if(employeeService.getEntityCount().block()<=0){
                EmployeeDTO user=new EmployeeDTO(Optional.empty(),"admin","John","Smith",true,Optional.of("admin"));
                employeeService.addEntity(user).block();
            }
    }
}
