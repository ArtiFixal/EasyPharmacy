import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { enviroment } from '../../enviroment';
import { StatusDTO } from '../model/StatusDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatusService {

  constructor(private http:HttpClient) { }

  public getStatusLocalized(entityID:number):Observable<StatusDTO>{
    return this.http.get<StatusDTO>(`${enviroment.apiUrl}/${enviroment.currentApiVer}/statuses/${entityID}`)
  }
}
