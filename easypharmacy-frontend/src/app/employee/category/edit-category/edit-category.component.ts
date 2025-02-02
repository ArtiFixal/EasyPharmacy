import { Component, Input } from '@angular/core';
import { CategoryService } from '../shared/category.service';
import { take } from 'rxjs';
import { CategoryDTO } from '../models/CategoryDTO';

@Component({
  standalone:false,
  selector: 'app-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrl: './edit-category.component.css'
})
export class EditCategoryComponent {
  category?:CategoryDTO

  constructor(private categoryService:CategoryService){}

  @Input()
  set id(entityID:number){
    this.categoryService.getEntity(entityID).pipe(take(1)).subscribe({
      next:response=>this.category=response
    })
  }
}
