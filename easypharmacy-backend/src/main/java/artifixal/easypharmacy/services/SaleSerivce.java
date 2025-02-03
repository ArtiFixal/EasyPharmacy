package artifixal.easypharmacy.services;

import artifixal.easypharmacy.dtos.sale.SaleCreationDTO;
import artifixal.easypharmacy.dtos.sale.SaleDTO;
import artifixal.easypharmacy.entities.CartEntry;
import artifixal.easypharmacy.entities.CartKey;
import artifixal.easypharmacy.entities.Sale;
import artifixal.easypharmacy.repositories.SaleRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Service related to sales.
 * 
 * @author ArtiFixal
 */
@Service
@AllArgsConstructor
public class SaleSerivce {

    private final SaleRepository repo;
    private final AddressService addressService;
    private final CartService cartService;
    
    public Mono<Sale> addSale(SaleCreationDTO entityData){
        return addressService.addEntity(entityData.getAddress())
            .flatMap((adress)->repo.save(converSaleCreationDtoToEntity(entityData,adress.getId())))
            .doOnSuccess((sale)->{
                List cartItems=entityData.getCart()
                    .stream()
                    .map((cartEntry)->new CartEntry(
                        new CartKey(sale.getId(),cartEntry.getMedicineID()),
                        cartEntry.getQuantity())
                    )
                    .collect(Collectors.toList());
                cartService.addSaleCart(cartItems);
            });
    }
    
    public Mono<SaleDTO> getEntityDto(Long saleID){
        return repo.findById(saleID)
            .map((saleEntity)->convertEntityToDto(saleEntity));
    }
    
    public Sale converSaleCreationDtoToEntity(SaleCreationDTO entityData,Long addressID){
        return new Sale(null,
            entityData.getClientID(),
            addressID,
            Timestamp.from(Instant.now()),
            null,
            1l
        );
    }
    
    public SaleDTO convertEntityToDto(Sale entity){
        
        return new SaleDTO(Optional.of(entity.getId()),
            entity.getClientID(),
            entity.getAddressID(),
            entity.getStatusID(),
            entity.getPurchased(),
            Optional.of(entity.getFinalised())
        );
    }
}
