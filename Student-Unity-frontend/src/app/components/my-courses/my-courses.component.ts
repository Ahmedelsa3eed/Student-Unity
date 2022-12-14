import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/Course';
import { CoursesService } from 'src/app/services/courses.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
  selector: 'app-my-courses',
  templateUrl: './my-courses.component.html',
  styleUrls: ['./my-courses.component.css']
})
export class MyCoursesComponent implements OnInit {

  registeredCourses: Course[] = [];

  constructor(private signInOutService: SignInOutService, private coursesService: CoursesService) { }

  ngOnInit(): void {
    this.signInOutService.getSignedInUser();
    this.coursesService.getUserRegisteredCourse();
  }

  openCoursePage(){

  }

  toggleCourseNotification(){

  }

  toggleCourseRevisionSystem(){
    
  }

}
