import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/Course';
import { User } from 'src/app/models/User';
import { AllCoursesService } from 'src/app/services/all-courses.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
  selector: 'app-course-card',
  templateUrl: './course-card.component.html',
  styleUrls: ['./course-card.component.css']
})
export class CourseCardComponent implements OnInit {

  loggedInUser = new User();

  @Input() course: Course = {} as Course;
  @Input() privilege: boolean = false;

  constructor(private allCoursesService : AllCoursesService, private signInOutService: SignInOutService, private router: Router) { }



  getSignedInUser(){
    this.signInOutService.getSignedInUser().subscribe(
      res => {
        console.log(res);
        if (res.body) {
          this.loggedInUser = res.body;
        }
    },
      err => {
        console.log(err);
      }
      );
  }
  // method to print the error message from the backend


  deleteCourse(): void {
    this.allCoursesService.deleteCourse(this.course).subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigate(['home/allCourses'])
      },
      error: (err) => this.router.navigate(['home/allCourses']),
      complete: () => console.info('Course Submited')
    })
  }

  ngOnInit(): void {
    this.getSignedInUser();
  }

  ngOnChanges(): void {
    console.log(this.course);
  }


}
