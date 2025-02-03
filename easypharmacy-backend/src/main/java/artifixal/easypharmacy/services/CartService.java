package artifixal.easypharmacy.services;

import artifixal.easypharmacy.dtos.cart.CartEntryDTO;
import artifixal.easypharmacy.entities.CartEntry;
import artifixal.easypharmacy.entities.CartKey;
import artifixal.easypharmacy.exceptions.InvalidDTOException;
import artifixal.easypharmacy.repositories.CartRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Service related to cart and its entries.
 * 
 * @author ArtiFixal
 */
@Service
public class CartService extends BaseService<CartRepository,CartEntry,CartKey,CartEntryDTO>{

    @Override
    protected void updateEntityValues(CartEntryDTO entityNewData,CartEntry entity){
        entity.setId(new CartKey(entity.getId().getSaleID(),
            entityNewData.getId()
                .orElseThrow(()->new InvalidDTOException("No cart key"))
                .getMedicineID())
        );
        entity.setQuantity(entityNewData.getQuantity());
    }
    
    public Flux<CartEntry> addSaleCart(Iterable<CartEntry> saleCart){
        return repo.saveAll(saleCart);
    }

    @Override
    protected CartEntryDTO convertEntityToDto(CartEntry entity){
        return new CartEntryDTO(
            Optional.of(entity.getId()),
            entity.getQuantity()
        );
    }

    @Override
    protected CartEntry convertDtoToEntity(CartEntryDTO entityData){
        return new CartEntry(
            entityData.getId().orElseThrow(),
            entityData.getQuantity()
        );
    }

}
