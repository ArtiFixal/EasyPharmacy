import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ManufacturerDTO } from '../../models/ManufacturerDTO';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ManufacturerService } from '../../services/manufacturer.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-manufacturer-form',
  imports: [CommonModule,ReactiveFormsModule,RouterLink],
  templateUrl: './manufacturer-form.component.html',
  styleUrl: './manufacturer-form.component.css'
})
export class ManufacturerFormComponent {

  @Input() model:ManufacturerDTO|undefined;
  @Output() successEvent:EventEmitter<boolean>=new EventEmitter<boolean>();

  manufacturerForm: FormGroup

  constructor(private formBuilder:FormBuilder,private manufacturerService:ManufacturerService){
    if(this.model){
      this.manufacturerForm=formBuilder.group({
        id:[this.model.id,[Validators.required]],
        name:[this.model.name,[Validators.required]]
      })
    }
    else{
      this.manufacturerForm=formBuilder.group({
        name:['',[Validators.required]]
      })
    }
  }

  submitCallback(){
    if(this.manufacturerForm.valid)
    {
      let manufacturerData=new ManufacturerDTO(
        this.manufacturerForm.value.name,
        this.manufacturerForm.value.id
      );
      let request:Observable<ManufacturerDTO>;
      if(manufacturerData.id)
        request=this.manufacturerService.editEntity(manufacturerData);
      else
        request=this.manufacturerService.addEntity(manufacturerData);
      let sub=request.subscribe({
        next:response=>{
          this.successEvent.emit(true)
        },
        error: err=> {
          this.successEvent.emit(false);
        },
        complete(){
          sub.unsubscribe()
        }
      });
    }
    else
      console.log("Invalid form");
      
  }
}
