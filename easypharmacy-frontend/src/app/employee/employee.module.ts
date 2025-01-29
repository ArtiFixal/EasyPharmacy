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

export const employeeRoutes: Routes = [
  // Manufacturer
  {path:"employee", component:EmployeeComponent,},
  {path:"employee/manufacturer", component:ManufacturerComponent,title:`${enviroment.title} - manufacturers list`},
  {path:"employee/manufacturer/:page", component:ManufacturerComponent,title:`${enviroment.title} - manufacturers list`},
  {path:"employee/manufacturer/add", component:ManufacturerAddComponent,title:`${enviroment.title} - add manufacturer`},
  {path:"employee/manufacturer/edit/:id", component:ManufacturerEditComponent,title:`${enviroment.title} - edit manufacturer`},
  {path:"employee/manufacturer/details/:id", component:ManufacturerDetailsComponent,title:`${enviroment.title} - manufacturer info`},
]

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    ManufacturerFormComponent,
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
  ],
  exports:[
    EmployeeComponent,
    ManufacturerComponent,
    ManufacturerAddComponent,
    ManufacturerEditComponent,
    ManufacturerDetailsComponent,
  ]
})
export class EmployeeModule { }
