import { Injectable } from '@angular/core';
import { LoginDTO } from '../models/LoginDTO';
import { HttpClient } from '@angular/common/http';
import { enviroment } from '../../enviroment';
import { BehaviorSubject, take } from 'rxjs';
import { jwtDecode } from 'jwt-decode';
import { JwtTokenDTO } from '../models/JwtTokenDTO';
import { RegisterDTO } from '../models/RegisterDTO';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly TOKEN_KEY="authToken"
  private loggedIn: BehaviorSubject<boolean>;
  private userSubject: BehaviorSubject<string>=new BehaviorSubject("")

  constructor(private http:HttpClient,private router:Router) {
    this.loggedIn=new BehaviorSubject(this.getToken!==null)
    // to do parse jwt token
  }

  public getToken():string|null{
    return localStorage.getItem(this.TOKEN_KEY)
  }

  protected setToken(token: string){
    localStorage.setItem(this.TOKEN_KEY,token)
  }

  /**
   * Sends login request at given endpoint.
   * 
   * @returns JWT token.
   */
  public sendLoginRequest(loginData:LoginDTO,loginEndpoint:string) {
    //{observe:'response',responseType:'json'}
    return this.http.post<JwtTokenDTO>(`${enviroment.apiUrl}${loginEndpoint}`,loginData,{observe:'response',responseType:'json'}).pipe(take(1)).subscribe({
      next: response=> {
        console.log(response);
        if(response.status===200)
        {
          this.setToken(response.body!.token!);
          console.log("Logged in");
          this.loggedIn.next(true);
          this.userSubject.next(loginData.email);
          this.router.navigateByUrl("/");
        }
      }
    })
  }

  public sendRegisterRequest(registerData:RegisterDTO,loginEndpoint:string){
    return this.http.post(`${enviroment.apiUrl}${loginEndpoint}`,registerData).pipe(take(1)).subscribe({
      next: response=>{
        this.router.navigateByUrl("/register/success")
      }
    })
  }

  public isTokenExpired(token:string): boolean{
    const payload=jwtDecode(token);
    return payload.exp!<Date.now()/1000;
  }

  public logout(){
    this.loggedIn.next(false);
    this.userSubject.next("");
    localStorage.removeItem(this.TOKEN_KEY);
  }
}
