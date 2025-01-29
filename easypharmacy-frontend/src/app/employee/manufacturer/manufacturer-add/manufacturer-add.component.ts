import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  standalone:false,
  selector: 'app-manufacturer-add',
  templateUrl: './manufacturer-add.component.html',
  styleUrl: './manufacturer-add.component.css',
})
export class ManufacturerAddComponent {

  constructor(private router:Router){}

  onSubmit(success:boolean){
    this.router.navigateByUrl("/employee/manufacturer",{
      state:{
        'action':'add',
        'success':success
      }
    })
  }
}
