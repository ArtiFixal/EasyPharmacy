import { Component } from '@angular/core';
import { EntityListComponent } from '../../../shared/entity-list/entity-list.component';
import { MedicineFormDTO } from '../models/MedicineFormDTO';
import { MedicineFormService } from '../shared/medicine-form.service';

@Component({
  standalone:false,
  selector: 'app-list-medicine-form',
  templateUrl: './list-medicine-form.component.html',
  styleUrl: './list-medicine-form.component.css'
})
export class ListMedicineFormComponent extends EntityListComponent<MedicineFormDTO,MedicineFormService> {

  constructor(medicineFormService:MedicineFormService){
    super(medicineFormService)
  }

  protected override localizeAddSuccess(): string {
    return $localize`:|@@medicineForm.add.success:New medicine form added successfully`
  }
  protected override localizeAddFail(): string {
    return $localize`:|@@medicineForm.add.fail:Failed to add medicine form`
  }
  protected override localizeEditSuccess(): string {
    return $localize`:|@@medicineForm.edit.success:Medicine form updated successfully`
  }
  protected override localizeEditFail(): string {
    return $localize`:|@@medicineForm.edit.fail:Failed to update medicine form`
  }
}
