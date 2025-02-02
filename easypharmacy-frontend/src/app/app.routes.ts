import { Routes } from '@angular/router';
import { ClientLoginComponent } from './auth/client-login/client-login.component';
import { LogoutComponent } from './auth/logout/logout.component';
import { ClientRegisterComponent } from './auth/client-register/client-register.component';
import { RegisterSuccessComponent } from './auth/register-success/register-success.component';
import { HomeComponent } from './core/home/home.component';
import { ProductPageComponent } from './core/product-page/product-page.component';
import { CartPageComponent } from './core/cart-page/cart-page.component';

export const routes: Routes = [
    {path:"",component:HomeComponent,title:"Easypharmacy"},
    {path:"login", component:ClientLoginComponent, title:"- Login"},
    {path:"logout",component:LogoutComponent,title:"Logout"},
    {path:"register",component:ClientRegisterComponent,title:"Register"},
    {path:"register/success",component:RegisterSuccessComponent,title:"Register success"},
    {path:"product/:id",component:ProductPageComponent},
    {path:"cart",component:CartPageComponent,title:"Cart"},
];
