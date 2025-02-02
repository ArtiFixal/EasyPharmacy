package artifixal.easypharmacy.controllers;

import artifixal.easypharmacy.dtos.medicine.MedicineDTO;
import artifixal.easypharmacy.dtos.Page;
import artifixal.easypharmacy.dtos.medicine.MedicineCreationDTO;
import artifixal.easypharmacy.entities.Medicine;
import artifixal.easypharmacy.services.MedicineService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for medicines v1.
 * 
 * @version 1.0
 * @author ArtiFixal
 */
@RestController
@RequestMapping("/v1/medicines")
@AllArgsConstructor
public class MedicineController{
    
    private final MedicineService medicineService;
    
    @PostMapping("/add")
    public Mono<Medicine> addMedicine(@RequestBody MedicineCreationDTO medicine) throws IOException{
        return medicineService.addMedicineWithImage(medicine);
    }
    
    @GetMapping("/page/{phrase}")
    public Flux<MedicineDTO> getMedicnesByPhrase(@PathVariable String phrase, Page page){
        return medicineService.getMedicinesByPhrase(phrase,page);
    }
    
    @GetMapping("/page")
    public Flux<MedicineDTO> getAllMedicines(Page page){
        return medicineService.getEntitiesDtoPage(page);
    }
    
    @GetMapping("/{medicineID}")
    public Mono<MedicineDTO> getMedicine(@PathVariable Long medicineID){
        return medicineService.getEntityDto(medicineID);
    }
    
    @GetMapping("/{medicineID}/delete")
    public void deleteMedicine(@PathVariable Long medicineID){
        
    }
    
    @GetMapping("/count")
    public Mono<Long> getMedicineCount(){
        return medicineService.getEntityCount();
    }
}
