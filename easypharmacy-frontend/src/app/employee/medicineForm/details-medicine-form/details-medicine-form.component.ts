import { Component, Input } from '@angular/core';
import { MedicineFormDTO } from '../models/MedicineFormDTO';
import { MedicineFormService } from '../shared/medicine-form.service';
import { take } from 'rxjs';

@Component({
  standalone:false,
  selector: 'app-details-medicine-form',
  templateUrl: './details-medicine-form.component.html',
  styleUrl: './details-medicine-form.component.css'
})
export class DetailsMedicineFormComponent {

  data?:MedicineFormDTO

  constructor(private medicineFormService:MedicineFormService){}

  @Input()
  set id(medicineFormID:number){
    this.medicineFormService.getEntity(medicineFormID).pipe(take(1)).subscribe({
      next: response=> this.data=response
    });
  }
}
