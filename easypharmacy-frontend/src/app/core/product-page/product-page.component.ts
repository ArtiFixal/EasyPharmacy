import { Component, Input, OnInit } from '@angular/core';
import { MedicineDTO } from '../../employee/medicine/models/MedicineDTO';
import { YesNoPipe } from "../../shared/yes-no.pipe";
import { MedicineService } from '../../employee/medicine/shared/medicine.service';
import { BehaviorSubject, concatMap, forkJoin, take } from 'rxjs';
import { DefaultImagePipe } from "../../shared/default-image.pipe";
import { ManufacturerDTO } from '../../employee/manufacturer/models/ManufacturerDTO';
import { CategoryDTO } from '../../employee/category/models/CategoryDTO';
import { MedicineFormDTO } from '../../employee/medicineForm/models/MedicineFormDTO';
import { ManufacturerService } from '../../employee/manufacturer/services/manufacturer.service';
import { CategoryService } from '../../employee/category/shared/category.service';
import { MedicineFormService } from '../../employee/medicineForm/shared/medicine-form.service';
import { CartItem } from '../models/CartItem';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-product-page',
  imports: [YesNoPipe, DefaultImagePipe],
  templateUrl: './product-page.component.html',
  styleUrl: './product-page.component.css',
})
export class ProductPageComponent {
  product?:MedicineDTO
  manufacturer?:ManufacturerDTO
  category?:CategoryDTO
  medicineForm?:MedicineFormDTO

  constructor(private medicineService:MedicineService,private manufacturerService:ManufacturerService,
    private categoryService:CategoryService,private medicineFormService:MedicineFormService,
    private cartService:CartService){}

  @Input("id")
  set id(productID:number){
    this.medicineService.getEntity(productID).pipe(take(1)).subscribe({
      next:response=>{this.product=response
        if(this.product)
          {
            this.manufacturerService.getEntity(this.product.manufacturerID).pipe(take(1)).subscribe({
              next:response=>this.manufacturer=response
            })
            this.categoryService.getEntity(this.product.categoryID).pipe(take(1)).subscribe({
              next:response=>this.category=response
            })
            this.medicineFormService.getEntity(this.product.medicineFormID).pipe(take(1)).subscribe({
              next:response=>this.medicineForm=response
            })
          }
      }
    })
  }

  addToCart(){
    if(this.product)
    {
      const item=new CartItem(this.product.id!,this.product.name,this.product.price,1,this.product.image)
      this.cartService.addToCart(item)
    }
  }
}
