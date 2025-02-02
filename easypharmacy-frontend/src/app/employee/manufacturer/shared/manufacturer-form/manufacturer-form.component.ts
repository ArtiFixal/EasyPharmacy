import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ManufacturerDTO } from '../../models/ManufacturerDTO';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ManufacturerService } from '../../services/manufacturer.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-manufacturer-form',
  imports: [CommonModule,ReactiveFormsModule,RouterModule],
  templateUrl: './manufacturer-form.component.html',
  styleUrl: './manufacturer-form.component.css'
})
export class ManufacturerFormComponent implements OnInit,OnChanges{
  @Input() model?:ManufacturerDTO;

  manufacturerForm!: FormGroup

  constructor(private router:Router,private formBuilder:FormBuilder,private manufacturerService:ManufacturerService){}

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['model'])
    if(this.model){
      this.manufacturerForm=this.formBuilder.group({
        id:[this.model.id,[Validators.required]],
        name:[this.model.name,[Validators.required]]
      })
    }
  }

  ngOnInit(): void {
    this.manufacturerForm=this.formBuilder.group({
      name:['',[Validators.required]]
    })
  }

  navigateToListPage(action:string,success:boolean){
    this.router.navigateByUrl("/employee/category",{
      state:{
        'action':action,
        'success':success
      }
    })
  }

  submitCallback(){
    if(this.manufacturerForm.valid)
    {
      let manufacturerData=new ManufacturerDTO(
        this.manufacturerForm.value.name,
        this.manufacturerForm.value.id
      );
      let request:Observable<ManufacturerDTO>;
      let action
      if(manufacturerData.id){
        request=this.manufacturerService.editEntity(manufacturerData);
        action="edit"
      }
      else{
        request=this.manufacturerService.addEntity(manufacturerData);
        action="add"
      }
      let sub=request.subscribe({
        next:response=>{
          this.navigateToListPage(action,true)
        },
        error: err=> {
          this.navigateToListPage(action,false)
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
