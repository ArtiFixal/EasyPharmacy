import { Injectable } from '@angular/core';
import { BaseService } from '../../../shared/services/base.service';
import { MedicineFormDTO } from '../models/MedicineFormDTO';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MedicineFormService extends BaseService<MedicineFormDTO>{

  constructor(http:HttpClient){
    super(http,"medicineForms")
  }
}
