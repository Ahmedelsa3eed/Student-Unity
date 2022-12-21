import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';
import { Course } from 'src/app/models/Course';
import { CoursesService } from 'src/app/services/courses.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
  selector: 'app-my-courses',
  templateUrl: './my-courses.component.html',
  styleUrls: ['./my-courses.component.css'],
})
export class MyCoursesComponent implements OnInit {

  registeredCourses: Course[] = [];

  constructor(private signInOutService: SignInOutService, private coursesService: CoursesService,
    private activatedRoute: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.coursesService.getUserRegisteredCourse(this.signInOutService.getSignedInUserSessionID()).pipe(
      map(list => {list.forEach((data: any) => {
            let course: Course = new Course;
            course.id = data[0];
            course.name = data[1];
            course.code = data[2];
            course.revisionSubscription = data[3];
            this.registeredCourses.push(course);
          });})
    ).subscribe(
      () => {},
      (error: HttpErrorResponse) => {
        if(error.status == 404)
          alert("User Not Found");
      }
    );
  }

  unRegisterCourse(courseId: number){
    this.coursesService.unRegisterCourse(this.signInOutService.getSignedInUserSessionID(), courseId).subscribe(
      () => {
        this.registeredCourses.forEach((course, index) => {
          if (course.id == courseId)
            this.registeredCourses.splice(index, 1);
        });
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  toggleRVSubscription(course: Course){
    this.coursesService.toggleRVSubscription(this.signInOutService.getSignedInUserSessionID(), course.id, course.revisionSubscription).subscribe(
      () => {
        course.revisionSubscription = !course.revisionSubscription;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  
  openCoursePage(courseId: number){
    this.router.navigate(['course'], {queryParams: {courseId: courseId}, relativeTo: this.activatedRoute.parent});
  }

}
