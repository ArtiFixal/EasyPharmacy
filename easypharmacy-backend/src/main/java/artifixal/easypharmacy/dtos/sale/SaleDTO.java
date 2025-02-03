package artifixal.easypharmacy.dtos.sale;

import artifixal.easypharmacy.dtos.BaseDTO;
import java.sql.Timestamp;
import java.util.Optional;
import lombok.Getter;

/**
 * DTO transporting data about single user purchase.
 * 
 * @author ArtiFixal
 */
@Getter
public class SaleDTO extends BaseDTO<Long>{

    private Long clientID;
    private Long addressID;
    private Long statusID;
    private Timestamp purchased;
    private Optional<Timestamp> finalised;

    public SaleDTO(Optional<Long> id,Long clientID,Long addressID,Long statusID,Timestamp purchased,Optional<Timestamp> finalised){
        super(id);
        this.clientID=clientID;
        this.addressID=addressID;
        this.statusID=statusID;
        this.purchased=purchased;
        this.finalised=finalised;
    }
}
