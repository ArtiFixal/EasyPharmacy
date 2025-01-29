import { Component, Input } from '@angular/core';
import { CategoryDTO } from '../models/CategoryDTO';
import { CategoryService } from '../shared/category.service';
import { take } from 'rxjs';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { employeeRoutes } from '../../employee.module';

@Component({
  standalone: false,
  selector: 'app-details-category',
  templateUrl: './details-category.component.html',
  styleUrl: './details-category.component.css',
  providers:[
    [provideRouter(employeeRoutes,withComponentInputBinding())]
  ]
})
export class DetailsCategoryComponent{

  data?:CategoryDTO

  constructor(private categoryService:CategoryService){}

  @Input()
  set id(categoryID:number){
    this.categoryService.getEntity(categoryID).pipe(take(1)).subscribe({
      next: response=> this.data=response
    });
  }
}
