import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MedicineFormDTO } from '../models/MedicineFormDTO';
import { MedicineFormService } from '../shared/medicine-form.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-medicine-form-form',
  imports: [CommonModule,ReactiveFormsModule,RouterLink],
  templateUrl: './medicine-form-form.component.html',
  styleUrl: './medicine-form-form.component.css',
})
export class MedicineFormFormComponent implements OnInit{
  @Input() model?:MedicineFormDTO;

  medicineFormForm!: FormGroup;

  constructor(private formBuilder:FormBuilder,private router:Router,private medicineFormService:MedicineFormService){}

  ngOnInit(): void {
    if (this.model) {
      this.medicineFormForm = this.formBuilder.group({
        id: [this.model.id, [Validators.required]],
        name: [this.model.name, [Validators.required]]
      })
    }
    else {
      this.medicineFormForm = this.formBuilder.group({
        name: ['', [Validators.required]]
      })
    }
  }

  navigateToListPage(action:string,success:boolean){
      this.router.navigateByUrl("/employee/category",{
        state:{
          'action':action,
          'success':success
        }
      })
    }
  
    submitCallback() {
      if (this.medicineFormForm.valid) {
        let manufacturerData = new MedicineFormDTO(
          this.medicineFormForm.value.name,
          this.medicineFormForm.value.id
        );
        let request: Observable<MedicineFormDTO>;
        if (manufacturerData.id)
          request = this.medicineFormService.editEntity(manufacturerData);
        else
          request = this.medicineFormService.addEntity(manufacturerData);
        let sub = request.subscribe({
          next: response => {
            sub.unsubscribe()
            this.navigateToListPage("add", true)
          },
          error: err => {
            sub.unsubscribe()
            this.navigateToListPage("add", false)
          },
          complete() {
            sub.unsubscribe()
          }
        });
      }
      else
        console.log("Invalid form");
    }
}
