import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { MedicineService } from '../shared/medicine.service';
import { take } from 'rxjs';
import { MedicineDTO } from '../models/MedicineDTO';

@Component({
  standalone:false,
  selector: 'app-edit-medicine',
  templateUrl: './edit-medicine.component.html',
  styleUrl: './edit-medicine.component.css'
})
export class EditMedicineComponent {
  toEdit?:MedicineDTO

  constructor(private router:Router,private medicineService:MedicineService){}

  @Input()
    set id(medicineID:number){
      this.medicineService.getEntity(medicineID).pipe(take(1)).subscribe({
        next:response=> {          
          this.toEdit=response;
        }
      })
    }
}
