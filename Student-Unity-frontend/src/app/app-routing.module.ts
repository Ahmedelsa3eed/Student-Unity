import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { ViewPortComponent } from './components/view-port/view-port.component';

const routes: Routes = [
  {path: 'sign-in', component: SignInComponent},
  {path: 'home', component: ViewPortComponent},
  {path: '**', component: SignInComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
