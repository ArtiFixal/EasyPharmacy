package artifixal.easypharmacy.services;

import artifixal.easypharmacy.dtos.Page;
import artifixal.easypharmacy.dtos.medicine.MedicineDTO;
import artifixal.easypharmacy.entities.Image;
import artifixal.easypharmacy.entities.Medicine;
import artifixal.easypharmacy.repositories.MedicineRepository;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Service related to medicines.
 * 
 * @author ArtiFixal
 */
@Service
@AllArgsConstructor
public class MedicineService extends BaseService<MedicineRepository,Medicine,Long,MedicineDTO>{
    
    private ImageService imageService;
    private R2dbcEntityTemplate template;
    
    public Flux<MedicineDTO> getMedicinesByPhrase(String phrase,Page page){
        final String query="SELECT m.* FROM medicnes m JOIN manufacturers man ON m.manufacturer_id=man.id "
            + "JOIN categories c ON m.category_id=c.id "
            + "JOIN medicine_forms f ON m.form_id=f.id "
            + "WHERE m.name LIKE ? OR man.name LIKE ? OR c.name LIKE ? OR f.name LIKE ? LIMIT ? OFFSET ?;";
        return template.getDatabaseClient().sql(query)
            .bind(0,phrase)
            .bind(1,phrase)
            .bind(2,phrase)
            .bind(3,phrase)
            .bind(4,page.getPageSize())
            .bind(5,page.getOffset())
            .map((row,rowMetadata)->{
                return new MedicineDTO(Optional.of(row.get("id",Long.class)),
                    row.get("name",String.class),
                    row.get("manufacturer_id",Long.class),
                    row.get("category_id",Long.class),
                    row.get("form_id",Long.class),
                    row.get("price",BigDecimal.class),
                    row.get("prescription_required",Boolean.class),
                    row.get("image",String.class));
            }).all();
    }

    @Override
    protected void updateEntityValues(MedicineDTO entityNewData,Medicine entity){
        entity.setName(entityNewData.getName());
        entity.setPrice(entityNewData.getPrice());
    }

    @Override
    protected MedicineDTO convertEntityToDto(Medicine entity){
        Image image;
        if(entity.getImageID()==null)
            image=new Image();
        else
            image=imageService.getEntity(entity.getImageID()).block();
        return new MedicineDTO(Optional.of(entity.getId()),
            entity.getName(),
            entity.getManufacturerID(),
            entity.getCategoryID(),
            entity.getFormID(),
            entity.getPrice(),
            entity.isPrescriptionRequired(),
            image.getPath()
        );
    }

    @Override
    protected Medicine convertDtoToEntity(MedicineDTO entityData){
        Image image;
        if(entityData.getImageUrl()==null)
            image=new Image();
        else
            image=imageService.getEntityByPath(entityData.getImageUrl()).block();
        return new Medicine(null,
            entityData.getName(),
            entityData.getManufacturerID(),
            entityData.getCategoryID(),
            entityData.getMedicineFormID(),
            image.getId(),
            entityData.getPrice(),
            entityData.isReceiptRequired()
        );
    }
}
