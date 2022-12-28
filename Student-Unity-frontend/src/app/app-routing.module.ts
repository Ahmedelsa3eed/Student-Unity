import { AddAnnouncementComponent } from './components/add-announcement/add-announcement.component';
import { AccountPageComponent } from './components/account-page/account-page.component';
import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SingUpComponent } from './components/sing-up/sing-up.component';
import { SuccessSignUpComponent } from './components/success-sign-up/success-sign-up.component';
import { MyCoursesComponent } from './components/my-courses/my-courses.component';
import { AllCoursesComponent } from './components/all-courses/all-courses.component';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { TasksPageComponent } from './components/tasks-page/tasks-page.component';
import { CoursePageComponent } from './components/course-page/course-page.component';
import { AnnouncementPageComponent } from './components/announcement-page/announcement-page.component';
import { AddTaskComponent } from './components/addtask/add-task.component';
import { VerficationComponent } from './components/verfication/verfication.component';
import {SettingsComponent} from "./components/settings/settings.component";
import { MaterialPageComponent } from './components/material-page/material-page.component';
import { MaterialCategoryPageComponent } from './components/material-category-page/material-category-page.component';

const routes: Routes = [
    { path: 'sign-in', component: SignInComponent },
    { path: 'sign-up', component: SingUpComponent },
    { path: 'verfication', component: VerficationComponent },
    { path: 'success-sign-up', component: SuccessSignUpComponent },
    {
        path: 'home',
        component: HomeComponent,
        children: [
            {
                path: 'accountsPage',
                component: AccountPageComponent,
            },
            {
                path: 'myCourses',
                component: MyCoursesComponent,
            },
            {
                path: 'allCourses',
                component: AllCoursesComponent,
            },
            {
                path: 'addCourse',
                component: AddCourseComponent,
            },
            {
                path: 'tasksPage',
                component: TasksPageComponent,
            },
            {
                path: 'course',
                component: CoursePageComponent,
                children: [
                    {
                        path: 'material',
                        component: MaterialPageComponent,
                    },
                    {
                        path: '**',
                        component: MaterialCategoryPageComponent,
                    },
                ],
            },
            {
                path: 'addTask',
                component: AddTaskComponent,
            },
            {
                path: 'announcementPage',
                component: AnnouncementPageComponent,
            },
            {
                path: 'add-announcement',
                component: AddAnnouncementComponent,
            },
            {
                path: 'settings',
                component: SettingsComponent,
            }

        ],
    },
    { path: '', redirectTo: 'sign-in', pathMatch: 'full' },
    { path: '**', component: SignInComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}
