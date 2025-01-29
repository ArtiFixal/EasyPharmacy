import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManufacturerComponent } from './manufacturer/manufacturer-list/manufacturer-list.component';
import { provideRouter, RouterModule, Routes, withComponentInputBinding } from '@angular/router';
import { ManufacturerAddComponent } from './manufacturer/manufacturer-add/manufacturer-add.component';
import { ManufacturerFormComponent } from './manufacturer/shared/manufacturer-form/manufacturer-form.component';
import { ManufacturerEditComponent } from './manufacturer/manufacturer-edit/manufacturer-edit.component';
import { ManufacturerDetailsComponent } from './manufacturer/manufacturer-details/manufacturer-details.component';
import { EmployeeComponent } from './employee.component';
import { enviroment } from '../enviroment';
import { CategoryFormComponent } from './category/category-form/category-form.component';
import { ListCategoryComponent } from './category/list-category/list-category.component';
import { AddCategoryComponent } from './category/add-category/add-category.component';
import { DetailsCategoryComponent } from './category/details-category/details-category.component';
import { ListMedicineFormComponent } from './medicineForm/list-medicine-form/list-medicine-form.component';
import { AddMedicineFormComponent } from './medicineForm/add-medicine-form/add-medicine-form.component';
import { DetailsMedicineFormComponent } from './medicineForm/details-medicine-form/details-medicine-form.component';
import { MedicineFormFormComponent } from './medicineForm/medicine-form-form/medicine-form-form.component';

export const employeeRoutes: Routes = [
  // Manufacturer
  {path:"employee", component:EmployeeComponent,},
  {path:"employee/manufacturer", component:ManufacturerComponent,title:`${enviroment.title} - manufacturers list`},
  {path:"employee/manufacturer/:page", component:ManufacturerComponent,title:`${enviroment.title} - manufacturers list`},
  {path:"employee/manufacturer/add", component:ManufacturerAddComponent,title:`${enviroment.title} - add manufacturer`},
  {path:"employee/manufacturer/edit/:id", component:ManufacturerEditComponent,title:`${enviroment.title} - edit manufacturer`},
  {path:"employee/manufacturer/details/:id", component:ManufacturerDetailsComponent,title:`${enviroment.title} - manufacturer info`},
  // Category
  {path:"employee/category", component:ListCategoryComponent,title:`${enviroment.title} - categories list`},
  {path:"employee/category/:page", component:ListCategoryComponent,title:`${enviroment.title} - categories list`},
  {path:"employee/category/add", component:AddCategoryComponent,title:`${enviroment.title} - add category`},
  {path:"employee/category/edit/:id", component:ManufacturerEditComponent,title:`${enviroment.title} - edit category`},
  {path:"employee/category/details/:id", component:DetailsCategoryComponent,title:`${enviroment.title} - category info`},
  // MedicineForm
  {path:"employee/medicineForm", component:ListMedicineFormComponent,title:`${enviroment.title} - medicine form list`},
  {path:"employee/medicineForm/add", component:AddMedicineFormComponent,title:`${enviroment.title} - add medicine form`},
  {path:"employee/medicineForm/edit/:id", component:ManufacturerEditComponent,title:`${enviroment.title} - edit medicine form`},
  {path:"employee/medicineForm/details/:id", component:DetailsMedicineFormComponent,title:`${enviroment.title} - medicine form info`},
]

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    ManufacturerFormComponent,
    CategoryFormComponent,
    MedicineFormFormComponent,
],
  providers:[
    [provideRouter(employeeRoutes,withComponentInputBinding())]
  ],
  declarations: [
    EmployeeComponent,
    ManufacturerComponent,
    ManufacturerAddComponent,
    ManufacturerEditComponent,
    ManufacturerDetailsComponent,
    ListCategoryComponent,
    AddCategoryComponent,
    DetailsCategoryComponent,
    ListMedicineFormComponent,
    AddMedicineFormComponent,
    DetailsMedicineFormComponent,

  ],
  exports:[
    EmployeeComponent,
    ManufacturerComponent,
    ManufacturerAddComponent,
    ManufacturerEditComponent,
    ManufacturerDetailsComponent,
    ListCategoryComponent,
    AddCategoryComponent,
    DetailsCategoryComponent,
    ListMedicineFormComponent,
    AddMedicineFormComponent,
    DetailsMedicineFormComponent,
  ]
})
export class EmployeeModule { }
