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

  registeredCourses: Course[] = [];

  constructor(private signInOutService: SignInOutService, private coursesService: CoursesService) {}

  ngOnInit(): void {
    this.coursesService.getUserRegisteredCourse(this.signInOutService.getSignedInUserSessionID()).subscribe(
      (response: Course[]) => {
        this.registeredCourses = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  unRegisterCourse(courseId: number){
    this.coursesService.unRegisterCourse(this.signInOutService.getSignedInUserSessionID(), courseId).subscribe(
      () => {
        this.registeredCourses.forEach((course, index) => {
          console.log(course.id);
          if (course.id == courseId)
            this.registeredCourses.splice(index, 1);
          console.log(this.registeredCourses);
        });
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
