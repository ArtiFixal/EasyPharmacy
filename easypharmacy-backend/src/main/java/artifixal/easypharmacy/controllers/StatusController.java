package artifixal.easypharmacy.controllers;

import artifixal.easypharmacy.dtos.StatusDTO;
import artifixal.easypharmacy.services.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for statuses v1.
 * 
 * @version 1.0
 * @author ArtiFixal
 */
@RestController
@RequestMapping("/v1/statuses")
@AllArgsConstructor
public class StatusController {

    private final StatusService statusService;
    
    @GetMapping("/page")
    public Flux<StatusDTO> getAllStatuses(){
        return statusService.getAllEntitiesDto();
    }
    
    @GetMapping("/{id}")
    public Mono<StatusDTO> getStatus(Long id){
        return statusService.getEntityDto(id);
    }
}
