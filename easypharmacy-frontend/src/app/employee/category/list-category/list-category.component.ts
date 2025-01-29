import { Component } from '@angular/core';
import { CategoryDTO } from '../models/CategoryDTO';
import { EntityListComponent } from '../../../shared/entity-list/entity-list.component';
import { CategoryService } from '../shared/category.service';

@Component({
  standalone:false,
  selector: 'app-list-category',
  templateUrl: './list-category.component.html',
  styleUrl: './list-category.component.css'
})
export class ListCategoryComponent extends EntityListComponent<CategoryDTO,CategoryService> {

  constructor(categoryService:CategoryService){
    super(categoryService)
  }

  protected override localizeAddSuccess(): string {
    return $localize`:|@@category.add.success:New category added successfully`
  }
  protected override localizeAddFail(): string {
    return $localize`:|@@category.add.fail:Failed to add category`
  }
  protected override localizeEditSuccess(): string {
    return $localize`:|@@category.edit.success:Category updated successfully`
  }
  protected override localizeEditFail(): string {
    return $localize`:|@@category.edit.fail:Failed to update category`
  }
}
