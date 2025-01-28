import { Routes } from '@angular/router';
import { ClientLoginComponent } from './auth/client-login/client-login.component';
import { LogoutComponent } from './auth/logout/logout.component';
import { ClientRegisterComponent } from './auth/client-register/client-register.component';
import { RegisterSuccessComponent } from './auth/register-success/register-success.component';

export const routes: Routes = [
    {path:"login", component:ClientLoginComponent, title:"- Login"},
    {path:"logout",component:LogoutComponent,title:"Logout"},
    {path:"register",component:ClientRegisterComponent,title:"Register"},
    {path:"register/success",component:RegisterSuccessComponent,title:"Register success"},
];
