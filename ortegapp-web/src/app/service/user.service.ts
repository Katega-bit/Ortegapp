import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenStorageService } from './token-storage-service';
import { environment } from 'src/environments/environment';
import { UserResponse } from '../interface/user.interface';
import { Observable } from 'rxjs';

const TOKEN = window.sessionStorage.getItem('auth-token');


const headers = new HttpHeaders({
  'Authorization': `Bearer  ${TOKEN}`
}).append('Content-Type', 'application/json');
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private tokenService : TokenStorageService) { }

  getUsers(page : number) : Observable<UserResponse>{
    console.log("blue label")
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.tokenService.getToken()}`,
    

    });
    console.log(this.tokenService.getToken())
    return this.http.get<UserResponse>(`${environment.urlBase}/user?page=${page}`, {headers} );

  }
}
