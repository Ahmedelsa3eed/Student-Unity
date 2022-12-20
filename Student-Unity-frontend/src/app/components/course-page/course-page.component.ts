import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs';
import { Course } from 'src/app/models/Course';
import { CoursesService } from 'src/app/services/courses.service';

@Component({
  selector: 'app-course-page',
  templateUrl: './course-page.component.html',
  styleUrls: ['./course-page.component.css']
})
export class CoursePageComponent implements OnInit {

  constructor(private coursesService: CoursesService, private activatedRoute: ActivatedRoute, private router: Router) {}

  addCategoryResponse: string = "";
  addCategoryLoading: boolean = false;
  addMaterialResponse: string = "";
  addMaterialLoading: boolean = false;
  course: Course = new Course;

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      let courseId = params['courseId'];
      this.coursesService.getCourseById(courseId).subscribe(
        (response) => {
          console.log(response);
          this.course.id = response.id;
          this.course.code = response.code;
          this.course.name = response.name;
          this.course.telegramLink = response.activeCourse.telegramLink;
          this.course.timeTable = response.activeCourse.timeTable;
        },
        (error: HttpErrorResponse) => {
          if(error.status == 404)
            alert("Course Not Found");
        }
      );
    });
  }

  onAddCategory(newCategoryName: string){
    console.log(newCategoryName);
  }

  onAddMaterial(newCategoryName: string){
    console.log(newCategoryName);
  }

}
