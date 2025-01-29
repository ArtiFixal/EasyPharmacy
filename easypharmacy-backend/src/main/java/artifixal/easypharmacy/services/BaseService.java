package artifixal.easypharmacy.services;

import artifixal.easypharmacy.dtos.BaseDTO;
import artifixal.easypharmacy.dtos.Page;
import artifixal.easypharmacy.entities.BaseEntity;
import artifixal.easypharmacy.exceptions.EntityNotFoundException;
import artifixal.easypharmacy.exceptions.InvalidDTOException;
import java.lang.reflect.ParameterizedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class used as base for Services.
 *
 * @author ArtiFixal
 *
 * @param <R> Type of repository used to retreive entities.
 * @param <ET> Entity type recived from DB.
 * @param <ID> Type of entity ID.
 * @param <DTO> DTO used int transport type.
 */
public abstract class BaseService<R extends R2dbcRepository<ET,ID>,ET extends BaseEntity,ID,DTO extends BaseDTO<ID>>{

    /**
     * Repository used to retrieve entities.
     */
    @Autowired
    protected R repo;

    protected final String entityName;

    @Autowired(required=true)
    protected BaseService(){
        // Get type name of ET generic.
        entityName=((ParameterizedType)getClass().getGenericSuperclass())
            .getActualTypeArguments()[1].getTypeName();
    }

    protected Mono<ET> getEntity(ID id) throws EntityNotFoundException{
        return repo.findById(id)
            .onErrorResume(
                (t)->Mono.error(new EntityNotFoundException(entityName,id))
            );
    }

    public Mono<DTO> getEntityDto(ID id) throws EntityNotFoundException{
        return getEntity(id).map((entity)->convertEntityToDto(entity));
    }

    public Flux<DTO> getEntitiesDtoPage(Page page){
        System.out.println(page.toString());
        return repo.findAll()
            .skip(page.getOffset())
            .take(page.getPageSize())
            .map((entity)->convertEntityToDto(entity));
    }

    public Flux<DTO> getAllEntitiesDto(){
        return repo.findAll()
            .map((entity)->convertEntityToDto(entity));
    }

    public Mono<ET> addEntity(DTO entityData){
        ET enity=convertDtoToEntity(entityData);
        return repo.save(enity);
    }
    
    public Mono<Long> getEntityCount(){
        return repo.count();
    }

    /**
     * @param entityNewData Data used to update entity.
     *
     * @return Saved entity.
     *
     * @throws EntityNotFoundException If entity with given ID wasn't found.
     * @throws InvalidDTOException If transported data doesn't contain ID.
     */
    public Mono<ET> editEntity(DTO entityNewData) throws EntityNotFoundException,InvalidDTOException{
        final ID id=entityNewData.getId().orElseThrow(()
            ->new InvalidDTOException("Entity edit request has no ID"));
        return getEntity(id).flatMap((entityToUpdate)->{
            updateEntityValues(entityNewData,entityToUpdate);
            return repo.save(entityToUpdate);
        });
    }

    /**
     * Updates entity data with those received from DTO.
     *
     * @param entityNewData New values.
     * @param entity Where to change.
     */
    protected abstract void updateEntityValues(DTO entityNewData,ET entity);

    protected abstract DTO convertEntityToDto(ET entity);

    protected abstract ET convertDtoToEntity(DTO entityData);
}
