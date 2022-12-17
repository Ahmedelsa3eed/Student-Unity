import { AccountPageComponent } from './components/account-page/account-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SingUpComponent } from './components/sing-up/sing-up.component';
import { SuccessSignUpComponent } from './components/success-sign-up/success-sign-up.component';
import { MyCoursesComponent } from './components/my-courses/my-courses.component';
import { TasksPageComponent } from './components/tasks-page/tasks-page.component';
import { CoursePageComponent } from './components/course-page/course-page.component';

const routes: Routes = [
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
        path: 'tasksPage',
        component: TasksPageComponent
      },
      {
        path: 'course',
        component: CoursePageComponent
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
