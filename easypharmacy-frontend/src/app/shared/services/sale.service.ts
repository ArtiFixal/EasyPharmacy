import { Injectable } from '@angular/core';
import { SaleDTO } from '../model/SaleDTO';
import { HttpClient } from '@angular/common/http';
import { SaleCreationDTO } from '../model/SaleCreationDTO';
import { enviroment } from '../../enviroment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SaleService{

  private readonly resourceUrl=`${enviroment.apiUrl}/${enviroment.currentApiVer}/sales`

  constructor(private http:HttpClient) { }

  public addSale(saleData:SaleCreationDTO): Observable<SaleDTO>{
    return this.http.post<SaleDTO>(`${this.resourceUrl}/add`,saleData);
  }
}
