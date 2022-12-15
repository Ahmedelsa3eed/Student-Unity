import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CookieService } from 'ngx-cookie-service';

import { AppComponent } from './app.component';
import { AccountComponent } from './components/account/account.component';
import { AccountPageComponent } from './components/account-page/account-page.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignInOutService } from './services/sign-in-out.service';
import { HomeComponent } from './components/home/home.component';
import { SingUpComponent } from './components/sing-up/sing-up.component';
import { SuccessSignUpComponent } from './components/success-sign-up/success-sign-up.component';
import { MyCoursesComponent } from './components/my-courses/my-courses.component';
import { CourseComponent } from './components/course/course.component';

@NgModule({
  declarations: [
    AppComponent,
    AccountComponent,
    AccountPageComponent,
    NavigationBarComponent,
    SignInComponent,
    HomeComponent,
    SingUpComponent,
    SuccessSignUpComponent,
    CourseComponent,
    MyCoursesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [SignInOutService, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
