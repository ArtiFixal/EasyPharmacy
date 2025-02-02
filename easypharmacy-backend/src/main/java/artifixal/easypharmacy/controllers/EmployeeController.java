package artifixal.easypharmacy.controllers;

import artifixal.easypharmacy.auth.AuthCredentialsDTO;
import artifixal.easypharmacy.auth.JWTAuthResponseDTO;
import artifixal.easypharmacy.auth.jwt.JwtService;
import artifixal.easypharmacy.dtos.EmployeeDTO;
import artifixal.easypharmacy.entities.Employee;
import artifixal.easypharmacy.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Employee controller V1.
 * 
 * @version 1.0
 * @author ArtiFixal
 */
@RestController
@RequestMapping("/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    
    private final EmployeeService employeeService;
    private final JwtService JwtService;
    
    @PostMapping("/login")
    public Mono<ResponseEntity<JWTAuthResponseDTO>> login(@RequestBody AuthCredentialsDTO userAuth){
        return employeeService.login(userAuth);
    }
    
    @GetMapping("/who")
    public Mono<EmployeeDTO> whoAmI(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        return Mono.just(auth.substring(7))
            .map((token)->JwtService.getEmail(token))
            .flatMap((email)->employeeService.getUserDtoByEmail(email));
    }
    
    @PostMapping("/register")
    public Mono<Employee> registerEmployee(@RequestBody EmployeeDTO employee){
        return employeeService.addEntity(employee);
    }
    
    @GetMapping("/getEmployees")
    public Flux<EmployeeDTO> getEmployees(){
        return employeeService.getAllEntitiesDto();
    }
    
    @GetMapping("/getEmployee/{id}")
    public Mono<EmployeeDTO> getEmployee(@PathVariable Long id){
        return employeeService.getEntityDto(id);
    }
    
    @GetMapping("/getEmployeeCount")
    public Mono<Long> getEmployeeCount(){
        return employeeService.getEntityCount();
    }
}
