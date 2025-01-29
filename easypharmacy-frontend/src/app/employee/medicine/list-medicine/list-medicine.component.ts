import { Component } from '@angular/core';
import { MedicineCreateDTO } from '../models/MedicineCreateDTO';
import { EntityListComponent } from '../../../shared/entity-list/entity-list.component';
import { MedicineService } from '../shared/medicine.service';
import { MedicineDTO } from '../models/MedicineDTO';

@Component({
  standalone:false,
  selector: 'app-list-medicine',
  templateUrl: './list-medicine.component.html',
  styleUrl: './list-medicine.component.css'
})
export class ListMedicineComponent extends EntityListComponent<MedicineDTO,MedicineService>{

  constructor(medicineService:MedicineService){
    super(medicineService)
  }

  protected override localizeAddSuccess(): string {
    return $localize`:|@@medicine.add.success:New medicine added successfully`
  }

  protected override localizeAddFail(): string {
    return $localize`:|@@medicine.add.fail:Failed to add medicine`
  }

  protected override localizeEditSuccess(): string {
    return $localize`:|@@medicine.edit.success:Medicine updated successfully`
  }

  protected override localizeEditFail(): string {
    return $localize`:|@@medicine.edit.fail:Failed to update medicine`
  }
}
