import { AccountPageComponent } from './components/account-page/account-page.component';
import { AccountComponent } from './components/account/account.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SingUpComponent } from './components/sing-up/sing-up.component';
import { flush } from '@angular/core/testing';
import { SuccessSignUpComponent } from './components/success-sign-up/success-sign-up.component';

const routes: Routes = [
  { path: 'sign-in', component: SignInComponent },
  {
    path: 'home',
    component: HomeComponent,
    children: [
      {
        path: 'accountsPage',
        component: AccountPageComponent
      }
    ]
  },
  { path: 'sign-up', component: SingUpComponent },
  { path: 'success-sign-up', component: SuccessSignUpComponent },
  { path: '', redirectTo: 'sign-in', pathMatch: 'full'},
  { path: '**', component: SignInComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
