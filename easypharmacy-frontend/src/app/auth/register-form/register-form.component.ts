import { Component, output } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RegisterDTO } from '../models/RegisterDTO';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-register-form',
  imports: [CommonModule,ReactiveFormsModule,RouterModule],
  templateUrl: './register-form.component.html',
  styleUrl: './register-form.component.css'
})
export class RegisterFormComponent {

  registerAttempt=output<RegisterDTO>()

  registerForm=new FormGroup({
    firstName:new FormControl('',Validators.required),
    lastName:new FormControl('',Validators.required),
    email:new FormControl('',Validators.required),
    password: new FormControl('',Validators.required),
    confirmPassword: new FormControl('',Validators.required)
  });

  onSubmit(){
    if(this.registerForm.valid){
      let registerData=new RegisterDTO(
        this.registerForm.value.firstName!,
        this.registerForm.value.lastName!,
        this.registerForm.value.email!,
        this.registerForm.value.password!,
        this.registerForm.value.confirmPassword!
      )
      this.registerAttempt.emit(registerData);
    }
    else
      console.log("Invalid form");
  }
}
