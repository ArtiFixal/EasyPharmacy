import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../auth/services/auth.service';
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
  private loginSub:Subscription;

  constructor(private authService:AuthService){
    this.loginSub=authService.isUserLoggedIn().subscribe({
      next:status=>{
        this.logged=status
        this.changeButtonAction();
      }
    })
  }

  ngOnDestroy(): void {
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
