import { Component } from '@angular/core';
import { LoginFormComponent } from "../login-form/login-form.component";
import { LoginDTO } from '../models/LoginDTO';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-client-login',
  imports: [LoginFormComponent],
  templateUrl: './client-login.component.html',
  styleUrl: './client-login.component.css'
})
export class ClientLoginComponent {


  constructor(private authService:AuthService){}

  login(loginData:LoginDTO){
    console.log(loginData);
    this.authService.sendLoginRequest(loginData,`/client/login`);
  }
}
