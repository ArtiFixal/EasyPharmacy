import { CommonModule } from '@angular/common';
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CategoryService } from '../shared/category.service';
import { CategoryDTO } from '../models/CategoryDTO';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-category-form',
  imports: [CommonModule,ReactiveFormsModule,RouterLink],
  templateUrl: './category-form.component.html',
  styleUrl: './category-form.component.css'
})
export class CategoryFormComponent implements OnInit,OnChanges{
  @Input() model?:CategoryDTO;
  
  categoryForm!: FormGroup

  constructor(private formBuilder:FormBuilder,private router:Router,private categoryService:CategoryService){}
  
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['model']&&this.model) {
      this.categoryForm = this.formBuilder.group({
        id: [this.model.id, [Validators.required]],
        name: [this.model.name, [Validators.required]]
      })
    }
  }
  
  ngOnInit(): void {
    this.categoryForm = this.formBuilder.group({
      name: ['', [Validators.required]]
    })
  }

  navigateToListPage(action:string,success:boolean){
    this.router.navigateByUrl("/employee/category",{
      state:{
        'action':action,
        'success':success
      }
    })
  }

  submitCallback() {
    if (this.categoryForm.valid) {
      let manufacturerData = new CategoryDTO(
        this.categoryForm.value.name,
        this.categoryForm.value.id
      );
      let request: Observable<CategoryDTO>;
      if (manufacturerData.id)
        request = this.categoryService.editEntity(manufacturerData);
      else
        request = this.categoryService.addEntity(manufacturerData);
      let sub = request.subscribe({
        next: response => {
          sub.unsubscribe()
          this.navigateToListPage("add", true)
        },
        error: err => {
          sub.unsubscribe()
          this.navigateToListPage("add", false)
        },
        complete() {
          sub.unsubscribe()
        }
      });
    }
    else
      console.log("Invalid form");
  }
}
