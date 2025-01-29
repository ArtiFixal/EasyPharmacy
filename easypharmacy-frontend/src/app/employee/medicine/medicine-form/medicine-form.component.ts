import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SelectFormComponent } from "../../../shared/option-form/select-form.component";
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { MedicineCreateDTO } from '../models/MedicineCreateDTO';
import { MedicineService } from '../shared/medicine.service';
import { Observable, take } from 'rxjs';
import { SelectOption } from '../../../shared/option-form/SelectOption';
import { ManufacturerService } from '../../manufacturer/services/manufacturer.service';
import { CategoryService } from '../../category/shared/category.service';
import { MedicineFormService } from '../../medicineForm/shared/medicine-form.service';
import { MedicineDTO } from '../models/MedicineDTO';

@Component({
  selector: 'app-medicine-form',
  imports: [CommonModule,ReactiveFormsModule, SelectFormComponent,RouterLink],
  templateUrl: './medicine-form.component.html',
  styleUrl: './medicine-form.component.css'
})
export class MedicineFormComponent implements OnInit{
  @Input() model?:MedicineDTO

  medicineForm!:FormGroup
  manufacturerControl!:FormControl
  categoryControl!:FormControl
  medicineFormControl!:FormControl

  manufacturers:SelectOption[]=[]
  categories:SelectOption[]=[]
  medicineForms:SelectOption[]=[]

  constructor(private formBuilder:FormBuilder,private router:Router,private medicineService:MedicineService,
    private manufacturerService:ManufacturerService,private categoryService:CategoryService,private medicineFormService:MedicineFormService){}

  ngOnInit(): void {
    // Get relationships
    this.manufacturerService.getEntityPage(0).subscribe({
      next: response=>this.manufacturers=response.flatMap(man=>new SelectOption(man.id,man.name))
    });
    this.categoryService.getEntityPage(0).subscribe({
      next: response=>this.categories=response.flatMap(cat=>new SelectOption(cat.id,cat.name))
    });
    this.medicineFormService.getEntityPage(0).subscribe({
      next: response=>this.medicineForms=response.flatMap(form=>new SelectOption(form.id,form.name))
    });
    // Create form
    console.log(this.model);
    
    if(this.model){
      this.medicineForm=this.formBuilder.group({
        id: [this.model.id, [Validators.required]],
        name: [this.model.name, [Validators.required]],
        image: [this.model.image, []],
        manufacturer: [this.model.manufacturerID,[Validators.required]],
        category: [this.model.categoryID,[Validators.required]],
        form: [this.model.medicineFormID,[Validators.required]],
        price:[this.model.price,[Validators.required]],
        prescriptionRequired:[this.model.prescriptionRequired,[Validators.required]]
      })
    }
    else{
      this.medicineForm=this.formBuilder.group({
        name: ['', [Validators.required]],
        image: ['', []],
        manufacturer: [[Validators.required]],
        category: [[Validators.required]],
        form: [[Validators.required]],
        price:[0.0,[Validators.required]],
        prescriptionRequired:[true,[Validators.required]]
      })
    }
  }

  navigateToListPage(action:string,success:boolean){
    this.router.navigateByUrl("/employee/medicine",{
      state:{
        'action':action,
        'success':success
      }
    })
  }

  submitCallback(){
    if(this.medicineForm.valid){
      let medicineData=new MedicineCreateDTO(
        this.medicineForm.value.name,
        this.medicineForm.value.image,
        this.medicineForm.value.manufacturer,
        this.medicineForm.value.category,
        this.medicineForm.value.form,
        this.medicineForm.value.price,
        this.medicineForm.value.prescriptionRequired,
        this.medicineForm.value.id
      );
      let request:Observable<MedicineCreateDTO>;
      let action;
      if(medicineData.id){
        request=this.medicineService.addMedicine(medicineData);
        action="add";
      }else{
        request=this.medicineService.editMedicine(medicineData);
        action="edit";
      }
      request.pipe(take(1)).subscribe({
        next: ()=>this.navigateToListPage(action,true),
        error: ()=>this.navigateToListPage(action,false)
      });
    }
    else
      console.log("Invalid form");
  }
}
