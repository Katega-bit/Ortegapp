import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './pages/about/about.component';
import { HelpComponent } from './pages/help/help.component';
import { HomeComponent } from './pages/home/home.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { AccessGuard } from './guards/access.guard';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login',
  },
  {
    path: 'home',
    canActivate : [AccessGuard],
    component: HomeComponent,
  },
  {
    path: 'profile',
    canActivate : [AccessGuard],
    component: ProfileComponent,
  },
  {
    path: 'about',
    canActivate : [AccessGuard],
    component: AboutComponent,
  },
  {
    path: 'help',
    canActivate : [AccessGuard],
    component: HelpComponent,
  },
  {
    path: "login",
    pathMatch : "full",
    component : LoginPageComponent
  },
  {
    path: '**',
    component: NotFoundComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
