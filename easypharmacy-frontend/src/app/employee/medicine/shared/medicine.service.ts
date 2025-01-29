import { Injectable } from '@angular/core';
import { BaseService } from '../../../shared/services/base.service';
import { MedicineCreateDTO } from '../models/MedicineCreateDTO';
import { HttpClient } from '@angular/common/http';
import { MedicineDTO } from '../models/MedicineDTO';
import { enviroment } from '../../../enviroment';

@Injectable({
  providedIn: 'root'
})
export class MedicineService extends BaseService<MedicineDTO>{

  constructor(http:HttpClient) {
    super(http,"medicines")
  }

  public addMedicine(medicine:MedicineCreateDTO){
    return this.http.post<MedicineCreateDTO>(`${enviroment.apiUrl}/addMedicine`,medicine)
  }

  public editMedicine(medicine:MedicineCreateDTO){
    return this.http.patch<MedicineCreateDTO>(`${enviroment.apiUrl}/editMedicine`,medicine)
  }
}
