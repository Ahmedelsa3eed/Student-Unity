import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { NgOtpInputModule } from 'ng-otp-input';
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
import { AllCoursesComponent } from './components/all-courses/all-courses.component';
import { CourseCardComponent } from './components/shared/course-card/course-card.component';
import { CommonModule } from '@angular/common';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { RemoveSpacesPipe } from './shared/remove-spaces.pipe';
import { TaskComponent } from './components/task/task.component';
import { TasksPageComponent } from './components/tasks-page/tasks-page.component';
import { AddTaskComponent } from './components/addtask/add-task.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { CoursePageComponent } from './components/course-page/course-page.component';
import { VerficationComponent } from './components/verfication/verfication.component';
import { AnnouncementPageComponent } from './components/announcement-page/announcement-page.component';
import { AnnouncementComponent } from './components/announcement/announcement.component';
import { AddAnnouncementComponent } from './components/add-announcement/add-announcement.component';
import { SettingsComponent } from './components/settings/settings.component';
import { MaterialPageComponent } from './components/material-page/material-page.component';
import { MaterialCategoryPageComponent } from './components/material-category-page/material-category-page.component';
import { environment } from '../environments/environment';
import { initializeApp } from 'firebase/app';
import { NotificationComponent } from './components/notification/notification.component';
initializeApp(environment.firebase);

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
        MyCoursesComponent,
        AllCoursesComponent,
        CourseCardComponent,
        CourseCardComponent,
        AddCourseComponent,
        RemoveSpacesPipe,
        CoursePageComponent,
        TaskComponent,
        TasksPageComponent,
        AddTaskComponent,
        VerficationComponent,
        AnnouncementPageComponent,
        AnnouncementComponent,
        AddAnnouncementComponent,
        SettingsComponent,
        MaterialPageComponent,
        MaterialCategoryPageComponent,
        NotificationComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
        CommonModule,
        BsDatepickerModule.forRoot(),
        BrowserAnimationsModule,
        MatFormFieldModule,
        MatInputModule,
        MatDatepickerModule,
        MatSlideToggleModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatSelectModule,
        MatAutocompleteModule,
        NgOtpInputModule,
    ],

    providers: [SignInOutService, CookieService],
    bootstrap: [AppComponent],
})
export class AppModule {}
