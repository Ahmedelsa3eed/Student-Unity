import { AccountPageComponent } from './components/account-page/account-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SingUpComponent } from './components/sing-up/sing-up.component';
import { SuccessSignUpComponent } from './components/success-sign-up/success-sign-up.component';
import { MyCoursesComponent } from './components/my-courses/my-courses.component';
import { AllCoursesComponent } from './components/all-courses/all-courses.component';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { VerficationComponent } from './components/verfication/verfication.component';

const routes: Routes = [
  { path: 'verfication', component: VerficationComponent},
  { path: 'sign-in', component: SignInComponent },
  { path: 'sign-up', component: SingUpComponent },
  { path: 'success-sign-up', component: SuccessSignUpComponent },
  {
    path: 'home',
    component: HomeComponent,
    children: [
      {
        path: 'accountsPage',
        component: AccountPageComponent
      },
      {
        path: 'myCourses',
        component: MyCoursesComponent
      },
      {
        path: 'allCourses',
        component: AllCoursesComponent
      },
      {
        path: 'addCourse',
        component: AddCourseComponent
      }
    ]
  },
  { path: '', redirectTo: 'sign-in', pathMatch: 'full'},
  { path: '**', component: SignInComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
