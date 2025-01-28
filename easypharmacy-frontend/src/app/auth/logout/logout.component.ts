import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { interval } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  imports: [],
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent implements OnInit{

  constructor(private authService:AuthService,private router:Router){}

  ngOnInit(): void {
    this.authService.logout();
    let task = interval(2000).subscribe({
            next: () => {
              task.unsubscribe();
              this.router.navigateByUrl("/");
            },
          })
  }

}
