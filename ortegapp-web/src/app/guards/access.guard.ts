import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenStorageService } from 'src/app/service/token-storage-service';

@Injectable({
  providedIn: 'root'
})
export class AccessGuard implements CanActivate {
  constructor(
    private router: Router,
    private tokenService: TokenStorageService
  ) {}
  canActivate(
    
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.checkAccess(route)
  }

  checkAccess(route: ActivatedRouteSnapshot): boolean{

    console.log(this.tokenService.getUser());
    const { roles = [] } = this.tokenService.getUser();

    if (this.tokenService.getToken() == null) {
      this.router.navigate(['login']);
      return false;
    } 
    console.log(this.tokenService.getToken());
    return true;
  }
  }
  
  
