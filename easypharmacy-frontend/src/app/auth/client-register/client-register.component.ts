import { Component } from '@angular/core';
import { RegisterFormComponent } from "../register-form/register-form.component";
import { AuthService } from '../services/auth.service';
import { RegisterDTO } from '../models/RegisterDTO';

@Component({
  selector: 'app-client-register',
  imports: [RegisterFormComponent],
  templateUrl: './client-register.component.html',
  styleUrl: './client-register.component.css'
})
export class ClientRegisterComponent {

    constructor(private authService:AuthService){}

    register(registerData:RegisterDTO){
      this.authService.sendRegisterRequest(registerData,"/client/register")
    }
}
