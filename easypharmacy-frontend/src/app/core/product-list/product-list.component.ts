import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MedicineService } from '../../employee/medicine/shared/medicine.service';
import { MedicineDTO } from '../../employee/medicine/models/MedicineDTO';
import { ProductComponent } from "../product/product.component";

@Component({
  selector: 'app-product-list',
  imports: [CommonModule, RouterModule, ProductComponent],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit{

  medicines:MedicineDTO[]=[]

  constructor(private medicineService:MedicineService){}

  ngOnInit(): void {
    this.medicineService.getEntityPage(0).subscribe({
      next: response=>this.medicines=response
    })
  }
}
