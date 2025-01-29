package artifixal.easypharmacy.controllers;

import artifixal.easypharmacy.dtos.ManufacturerDTO;
import artifixal.easypharmacy.dtos.Page;
import artifixal.easypharmacy.entities.Manufacturer;
import artifixal.easypharmacy.services.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for manufacturers v1.
 * 
 * @version 1.0
 * @author ArtiFixal
 */
@RestController
@RequestMapping("/v1/manufacturers")
@AllArgsConstructor
public class ManufacturerController{
    
    private ManufacturerService manufacturerService;
    
    @PostMapping("/add")
    public Mono<Manufacturer> addManufacturer(@RequestBody ManufacturerDTO manufacturer){
        return manufacturerService.addEntity(manufacturer);
    }
    
    @PatchMapping("/edit")
    public Mono<Manufacturer> editManufacturer(ManufacturerDTO manufacturer){
        return manufacturerService.editEntity(manufacturer);
    }
    
    @GetMapping("/{id}")
    public Mono<ManufacturerDTO> getManufacturer(@PathVariable Long id){
        return manufacturerService.getEntityDto(id);
    }
    
    @GetMapping("/page")
    public Flux<ManufacturerDTO> getManufacturers(@ModelAttribute Page page){
        return manufacturerService.getEntitiesDtoPage(page);
    }
    
    @GetMapping("/count")
    public Mono<Long> getManufacturerCount(){
        return manufacturerService.getEntityCount();
    }
}
