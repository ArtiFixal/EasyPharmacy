package artifixal.easypharmacy.controllers;

import artifixal.easypharmacy.dtos.CategoryDTO;
import artifixal.easypharmacy.dtos.Page;
import artifixal.easypharmacy.entities.Category;
import artifixal.easypharmacy.services.CategoryService;
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
 * Controller for categories v1.
 * 
 * @version 1.0
 * @author ArtiFixal
 */
@RestController
@RequestMapping("/v1/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    
    @PostMapping("/add")
    public Mono<Category> addCategory(@RequestBody CategoryDTO category){
        return categoryService.addEntity(category);
    }
    
    @PatchMapping("/edit")
    public Mono<Category> editCategory(CategoryDTO category){
        return categoryService.editEntity(category);
    }
    
    @GetMapping("/page")
    public Flux<CategoryDTO> getCategories(Page page){
        return categoryService.getEntitiesDtoPage(page);
    }
    
    @GetMapping("/{id}")
    public Mono<CategoryDTO> getCategory(@PathVariable Long id){
        return categoryService.getEntityDto(id);
    }
    
    @GetMapping("/count")
    public Mono<Long> getMaxPage(){
        return categoryService.getEntityCount();
    }
}
