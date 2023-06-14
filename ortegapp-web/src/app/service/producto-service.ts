import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductoResponse } from '../interface/producto.interface';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from './token-storage-service';


const TOKEN = window.sessionStorage.getItem('auth-token');
const headers = new HttpHeaders({
  'Authorization': `Bearer  ${TOKEN}`
}).append('Content-Type', 'application/json');
@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  

  constructor(private http: HttpClient, private tokenService : TokenStorageService) { }

  getProductos(page : number) : Observable<ProductoResponse>{
    console.log("blue label")
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.tokenService.getToken()}`,
    

    });
    console.log(this.tokenService.getToken())
    return this.http.get<ProductoResponse>(`${environment.urlBase}/producto?page=${page}`, {headers} );

  }
}
