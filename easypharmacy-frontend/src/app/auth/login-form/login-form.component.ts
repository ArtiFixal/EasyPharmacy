import { Component, output } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginDTO } from '../models/LoginDTO';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login-form',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent {

  loginAttempt=output<LoginDTO>();

  constructor(){}

  loginForm=new FormGroup({
    email: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required)
  });

  onSubmit(){
    if(this.loginForm.valid){
      let loginData=new LoginDTO(this.loginForm.value.email!,this.loginForm.value.password!);
      this.loginAttempt.emit(loginData);
    }
    else
      console.log("Invalid form");
  }
}