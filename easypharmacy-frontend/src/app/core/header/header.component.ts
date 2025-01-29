import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../auth/services/auth.service';

@Component({
  selector: 'app-header',
  imports: [CommonModule,RouterLink,RouterLinkActive],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
  schemas: []
})
export class HeaderComponent implements OnInit{

  logged: boolean=false;
  currentString: string=$localize`:Button name|@@header.login:login`;
  currentAction: string="/login";

  constructor(private authService:AuthService){}

  ngOnInit(): void {
    this.logged=this.authService.getToken()!==null;
    if(this.logged){
      this.currentString=$localize`:Logout button name|@@header.logout:logout`
      this.currentAction="/logout"
    }else{
      this.currentString=$localize`:Login button name|@@header.login:login`
      this.currentAction="/login"
    }
  }
}
