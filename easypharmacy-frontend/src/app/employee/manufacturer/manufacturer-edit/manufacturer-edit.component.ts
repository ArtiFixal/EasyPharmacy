import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
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

  constructor(private router:Router,private manufacturerService:ManufacturerService){}

  onSubmit(success:boolean){
    this.router.navigateByUrl("/employee/manufacturer",{
      state:{
        'action':'edit',
        'success':success
      }
    })
  }

  @Input()
  set id(manufacturerID:number){
    this.manufacturerService.getEntity(manufacturerID).pipe(take(1)).subscribe({
      next:response=> {
        this.toEdit=response;
      }
    })
  }
}
