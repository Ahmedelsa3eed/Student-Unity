import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/app/models/Course';

@Component({
  selector: 'app-course-page',
  templateUrl: './course-page.component.html',
  styleUrls: ['./course-page.component.css']
})
export class CoursePageComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router) {}

  courseId: number = 0;

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.courseId = params['courseId'];
      console.log(this.courseId);
    });
  }

}
