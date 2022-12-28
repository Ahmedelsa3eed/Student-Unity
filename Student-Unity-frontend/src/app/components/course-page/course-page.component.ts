import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/app/models/Course';
import { MaterialCategory } from 'src/app/models/MaterialCategory';
import { CoursesService } from 'src/app/services/courses.service';
import { MaterialsService } from 'src/app/services/materials.service';

@Component({
    selector: 'app-course-page',
    templateUrl: './course-page.component.html',
    styleUrls: ['./course-page.component.css'],
})
export class CoursePageComponent implements OnInit {
    constructor(
        private coursesService: CoursesService,
        private activatedRoute: ActivatedRoute,
        private materialsService: MaterialsService
    ) {}

    addCategoryLoading: boolean = false;
    addMaterialResponse: string = '';
    addMaterialLoading: boolean = false;
    course: Course = new Course();

    ngOnInit(): void {
        this.activatedRoute.queryParams.subscribe((params) => {
            let courseId = params['courseId'];
            this.coursesService.getCourseById(courseId).subscribe(
                (response) => {
                    this.course.id = response.id;
                    this.course.code = response.code;
                    this.course.name = response.name;
                    this.course.telegramLink = response.activeCourse.telegramLink;
                    this.course.timeTable = response.activeCourse.timeTable;
                },
                (error: HttpErrorResponse) => {
                    if (error.status == 404) alert('Course Not Found');
                }
            );
        });
    }

    onAddCategory(newCategoryName: string) {
        this.addCategoryLoading = true;
        let materialCategory: MaterialCategory = new MaterialCategory();
        materialCategory.name = newCategoryName;
        materialCategory.courseId = this.course.id;
        this.materialsService.addMaterialCategory(materialCategory).subscribe(
            () => {
                this.addCategoryLoading = false;
                document.getElementById('openAddCategoryBtn')?.click();
                window.location.reload();
            },
            (error: HttpErrorResponse) => {
                this.addCategoryLoading = false;
                alert('Something is wrong, the category may be already existed!');
            }
        );
    }

    onAddMaterial(newCategoryName: string) {}
}
