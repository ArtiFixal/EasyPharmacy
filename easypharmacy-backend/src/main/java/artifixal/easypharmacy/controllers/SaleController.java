package artifixal.easypharmacy.controllers;

import artifixal.easypharmacy.dtos.sale.SaleCreationDTO;
import artifixal.easypharmacy.entities.Sale;
import artifixal.easypharmacy.services.SaleSerivce;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Controller for sales v1
 * 
 * @author ArtiFixal
 * @version 1.0
 */
@RestController
@RequestMapping("/v1/sales")
@AllArgsConstructor
public class SaleController {
    
    private final SaleSerivce saleSerivce;

    @PostMapping("/add")
    public Mono<Sale> placeSale(@RequestBody SaleCreationDTO saleData){
        return saleSerivce.addSale(saleData);
    }
}
