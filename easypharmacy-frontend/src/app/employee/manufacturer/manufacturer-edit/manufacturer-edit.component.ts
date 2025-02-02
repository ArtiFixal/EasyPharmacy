import { Component, Input } from '@angular/core';
import { ManufacturerService } from '../services/manufacturer.service';
import { ManufacturerDTO } from '../models/ManufacturerDTO';
import { take } from 'rxjs';

@Component({
  standalone:false,
  selector: 'app-manufacturer-edit',
  templateUrl: './manufacturer-edit.component.html',
  styleUrl: './manufacturer-edit.component.css',
})
export class ManufacturerEditComponent {
  
  toEdit!:ManufacturerDTO

  constructor(private manufacturerService:ManufacturerService){}

  @Input()
  set id(manufacturerID:number){
    this.manufacturerService.getEntity(manufacturerID).pipe(take(1)).subscribe({
      next:response=> {
        this.toEdit=response;
      }
    })
  }
}
