import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/Course';
import { User } from 'src/app/models/User';
import { CoursesService } from 'src/app/services/courses.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
  selector: 'app-my-courses',
  templateUrl: './my-courses.component.html',
  styleUrls: ['./my-courses.component.css']
})
export class MyCoursesComponent implements OnInit {

  signedInUser: User = new User;
  registeredCourses: Course[] = [];

  constructor(private signInOutService: SignInOutService, private coursesService: CoursesService) { }

  ngOnInit(): void {
    this.signedInUser = this.signInOutService.getSignedInUser();
    this.coursesService.getUserRegisteredCourse(this.signedInUser.sessionID).subscribe(
      (response: Course[]) => {
        this.registeredCourses = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  openCoursePage(){

  }

  toggleCourseNotification(){

  }

  toggleCourseRevisionSystem(){
    
  }

}
