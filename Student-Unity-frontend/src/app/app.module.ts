import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from "@angular/forms";
import {CookieService} from 'ngx-cookie-service';

import { AppComponent } from './app.component';
import { AccountComponent } from './components/account/account.component';
import { AccountPageComponent } from './components/account-page/account-page.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignInOutService } from './services/sign-in-out.service';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    AccountComponent,
    AccountPageComponent,
    NavigationBarComponent,
    SignInComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [SignInOutService, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
