import { Component, OnInit } from '@angular/core';
import { EntityDetailsPage } from '../../shared/EntityDetailsPage';
import { MedicineService } from '../shared/medicine.service';
import { CategoryDTO } from '../../category/models/CategoryDTO';
import { ManufacturerDTO } from '../../manufacturer/models/ManufacturerDTO';
import { MedicineFormDTO } from '../../medicineForm/models/MedicineFormDTO';
import { CategoryService } from '../../category/shared/category.service';
import { ManufacturerService } from '../../manufacturer/services/manufacturer.service';
import { MedicineFormService } from '../../medicineForm/shared/medicine-form.service';
import { take } from 'rxjs';
import { MedicineDTO } from '../models/MedicineDTO';

@Component({
  standalone: false,
  selector: 'app-details-medicine',
  templateUrl: './details-medicine.component.html',
  styleUrl: './details-medicine.component.css'
})
export class DetailsMedicineComponent extends EntityDetailsPage<MedicineDTO,MedicineService> implements OnInit{

  category?:CategoryDTO
  manufacturer?:ManufacturerDTO
  form?:MedicineFormDTO

  constructor(medicineService:MedicineService,private categoryService:CategoryService,
    private manufacturerService:ManufacturerService,private medicineFormService:MedicineFormService){
      super(medicineService)
  }

  ngOnInit(): void {
    if(this.data)
    {
      this.categoryService.getEntity(this.data?.categoryID).pipe(take(1)).subscribe({
        next: response=>this.category=response
      });
      this.manufacturerService.getEntity(this.data.manufacturerID).pipe(take(1)).subscribe({
        next: response=>this.manufacturer=response
      })
      this.medicineFormService.getEntity(this.data.medicineFormID).pipe(take(1)).subscribe({
        next: response=>this.form=response
      })
    }
  }
}
