import { Component, Input } from '@angular/core';
import { MedicineFormDTO } from '../models/MedicineFormDTO';
import { MedicineFormService } from '../shared/medicine-form.service';
import { take } from 'rxjs';

@Component({
  standalone:false,
  selector: 'app-edit-medicine-form',
  templateUrl: './edit-medicine-form.component.html',
  styleUrl: './edit-medicine-form.component.css'
})
export class EditMedicineFormComponent {
  medicineForm?:MedicineFormDTO

  constructor(private medicineFormService:MedicineFormService){}

  @Input()
  set id(entityID:number){
    this.medicineFormService.getEntity(entityID).pipe(take(1)).subscribe({
      next:response=>this.medicineForm=response
    })
  }
}
