import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../auth/services/auth.service';
import { CartService } from '../services/cart.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  imports: [CommonModule,RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
  schemas: []
})
export class HeaderComponent implements OnInit,OnDestroy{

  logged: boolean=false;
  currentString: string=$localize`:Button name|@@header.login:login`;
  currentAction: string="/login";
  cartCount:number=0;
  private cartCountSub:Subscription;
  private loginSub:Subscription;

  constructor(private authService:AuthService,private cartService:CartService){
    this.cartCountSub=cartService.getCartItemsCount().subscribe({
      next:count=>this.cartCount=count
    });
    this.loginSub=authService.isUserLoggedIn().subscribe({
      next:status=>{
        this.logged=status
        this.changeButtonAction();
      }
    })
  }

  ngOnDestroy(): void {
    this.cartCountSub.unsubscribe();
    this.loginSub.unsubscribe();
  }

  ngOnInit(): void {
    this.logged=this.authService.getToken()!==null;
    this.changeButtonAction();
  }

  private changeButtonAction(){
    if(this.logged){
      this.currentString=$localize`:Logout button name|@@header.logout:logout`;
      this.currentAction="/logout";
    }else{
      this.currentString=$localize`:Login button name|@@header.login:login`;
      this.currentAction="/login";
    }
  }
}
