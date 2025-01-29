package artifixal.easypharmacy.controllers;

import artifixal.easypharmacy.dtos.MedicineFormDTO;
import artifixal.easypharmacy.dtos.Page;
import artifixal.easypharmacy.entities.MedicineForm;
import artifixal.easypharmacy.services.MedicineFormService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author ArtiFixal
 */
@RestController
@RequestMapping("/v1/medicineForms")
@AllArgsConstructor
public class MedicineFormController {

    private MedicineFormService medicineFormService;
    
    @PostMapping("/add")
    public Mono<MedicineForm> addMedicineForm(@RequestBody MedicineFormDTO form){
        return medicineFormService.addEntity(form);
    }
    
    @PatchMapping("/edit")
    public Mono<MedicineForm> editMedicineForm(MedicineFormDTO form){
        return medicineFormService.editEntity(form);
    }
    
    @GetMapping("/page")
    public Flux<MedicineFormDTO> getMedicineForms(Page page){
        return medicineFormService.getEntitiesDtoPage(page);
    }
    
    @GetMapping("/{id}")
    public Mono<MedicineFormDTO> getMedicineForm(@PathVariable Long id){
        return medicineFormService.getEntityDto(id);
    }
    
    @GetMapping("/count")
    public Mono<Long> getMedicineFormCount(){
        return medicineFormService.getEntityCount();
    }
}
