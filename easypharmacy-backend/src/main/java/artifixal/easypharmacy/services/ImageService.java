package artifixal.easypharmacy.services;

import artifixal.easypharmacy.entities.Image;
import artifixal.easypharmacy.repositories.ImageRepository;
import java.io.File;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Service related to images.
 * 
 * @author ArtiFixal
 */
@Service
public class ImageService{
    public final static File IMAGE_DIR=new File("/images");
    
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository){
        this.imageRepository=imageRepository;
        if(!IMAGE_DIR.exists())
            IMAGE_DIR.mkdir();
    }
    
    public Mono<Resource> getImage(Long id){
        return getEntity(id).map((image)->{
            try{
                return new UrlResource(image.getPath());
            }catch(Exception e){
                throw new RuntimeException("Image malformed URL");
            }
        });
    }
    
    public Mono<Image> getEntityByPath(String path){
        return imageRepository.findByPath(path);
    }

    public Mono<Image> getEntity(Long id){
        return imageRepository.findById(id);
    }
    
    public String getExtension(String filename){
        int lastDot=filename.lastIndexOf('.');
        return filename.substring(lastDot);
    }
    
    public Mono<Image> addEntity(FilePart image){
        String extension=getExtension(image.filename());
        File destination=new File(IMAGE_DIR,UUID.randomUUID().toString()+extension);
        image.transferTo(destination);
        return imageRepository.save(new Image(null,destination.toURI().toString()));
    }
}
