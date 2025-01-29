import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ManufacturerDTO } from '../models/ManufacturerDTO';
import { BaseService } from '../../../shared/services/base.service';

@Injectable({
  providedIn: 'root'
})
export class ManufacturerService extends BaseService<ManufacturerDTO> {
  
  constructor(http:HttpClient) {
    super(http,"manufacturers")
  }
}
