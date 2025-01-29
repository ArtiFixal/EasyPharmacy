import { Injectable } from '@angular/core';
import { BaseService } from '../../../shared/services/base.service';
import { HttpClient } from '@angular/common/http';
import { CategoryDTO } from '../models/CategoryDTO';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends BaseService<CategoryDTO>{

  constructor(http:HttpClient) {
    super(http,"categories")
  }
}
