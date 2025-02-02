import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
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
import { DefaultImagePipe } from "../../../shared/default-image.pipe";

@Component({
  selector: 'app-medicine-form',
  imports: [CommonModule, ReactiveFormsModule, SelectFormComponent, RouterLink, DefaultImagePipe],
  templateUrl: './medicine-form.component.html',
  styleUrl: './medicine-form.component.css'
})
export class MedicineFormComponent implements OnInit,OnChanges{
  @Input() model?:MedicineDTO

  medicineForm!:FormGroup
  manufacturerControl!:FormControl
  categoryControl!:FormControl
  medicineFormControl!:FormControl

  manufacturers:SelectOption[]=[]
  categories:SelectOption[]=[]
  medicineForms:SelectOption[]=[]

  newImageSrc?:string

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
    this.manufacturerControl=new FormControl('',[Validators.required]);
    this.categoryControl=new FormControl('',[Validators.required]);
    this.medicineFormControl=new FormControl('',[Validators.required]);
    this.medicineForm=this.formBuilder.group({
      name: ['', [Validators.required]],
      image: ['', []],
      manufacturer: this.manufacturerControl,
      category: this.categoryControl,
      form: this.medicineFormControl,
      price:[0.0,[Validators.required]],
      prescriptionRequired:[true,[Validators.required]]
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['model']&&this.model)
    {
      this.manufacturerControl=new FormControl(this.model.manufacturerID,[Validators.required]);
      this.categoryControl=new FormControl(this.model.categoryID,[Validators.required]);
      this.medicineFormControl=new FormControl(this.model.medicineFormID,[Validators.required]);
      this.medicineForm=this.formBuilder.group({
        id: [this.model.id, [Validators.required]],
        name: [this.model.name, [Validators.required]],
        image: [this.model.image, []],
        manufacturer: this.manufacturerControl,
        category: this.categoryControl,
        form: this.medicineFormControl,
        price:[this.model.price,[Validators.required]],
        prescriptionRequired:[this.model.prescriptionRequired?'true':'false',[Validators.required]]
      })
    }
  }

  onImageSelect(event:Event){
    const fileSelect=event.target as HTMLInputElement;
    if(fileSelect.files&&fileSelect.files[0]){
      const file=fileSelect.files[0];
      if(this.newImageSrc)
        URL.revokeObjectURL(this.newImageSrc);
      this.newImageSrc=URL.createObjectURL(file);
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
        this.medicineForm.value.manufacturer,
        this.medicineForm.value.category,
        this.medicineForm.value.form,
        this.medicineForm.value.price,
        this.medicineForm.value.prescriptionRequired,
        this.medicineForm.value.id,
        undefined
      );
      let request:Observable<MedicineCreateDTO>;
      let action;
      if(!medicineData.id){
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
