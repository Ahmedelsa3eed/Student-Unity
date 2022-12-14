import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/app/models/Course';
import { Material } from 'src/app/models/Material';
import { MaterialCategory } from 'src/app/models/MaterialCategory';
import { CoursesService } from 'src/app/services/courses.service';
import { MaterialsService } from 'src/app/services/materials.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { CourseMaterialCategoriesService } from 'src/app/shared/course-material-categories.service';

@Component({
    selector: 'app-course-page',
    templateUrl: './course-page.component.html',
    styleUrls: ['./course-page.component.css'],
})
export class CoursePageComponent implements OnInit {
    constructor(
        private coursesService: CoursesService,
        private activatedRoute: ActivatedRoute,
        private materialsService: MaterialsService,
        private courseMaterialCategoriesService: CourseMaterialCategoriesService,
        private signInOutService: SignInOutService,
        private router: Router
    ) {}

    addCategoryLoading: boolean = false;
    addMaterialLoading: boolean = false;
    course: Course = new Course();
    materialCategories: MaterialCategory[] = [];
    signedInUserRole: string = this.signInOutService.getSignedInUserRole();

    ngOnInit(): void {
        this.activatedRoute.queryParams.subscribe((params) => {
            let courseId = params['courseId'];
            this.course.id = courseId;
            this.coursesService.getCourseById(courseId).subscribe(
                (response) => {
                    this.course.code = response.code;
                    this.course.name = response.name;
                    this.course.telegramLink = response.activeCourse?.telegramLink;
                    this.course.timeTable = response.activeCourse?.timeTable;
                },
                (error: HttpErrorResponse) => {
                    if (error.status == 404) alert('Course Not Found');
                }
            );
            this.materialsService.getCourseMaterialCategories(courseId).subscribe(
                (response) => {
                    this.materialCategories = response;
                    this.courseMaterialCategoriesService.updateMaterialCategories(response);
                },
                (error: HttpErrorResponse) => {
                    alert("Can't load the material categories!");
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
                document.getElementById('closeAddCategoryBtn')?.click();
                window.location.reload();
            },
            (error: HttpErrorResponse) => {
                this.addCategoryLoading = false;
                alert('Something is wrong, the category may be already existed!');
            }
        );
    }

    onAddMaterial(materialTitle: string, materialURL: string, materialCategoryId: string) {
        this.addMaterialLoading = true;
        let material: Material = new Material();
        material.materialCategoryId = Number(materialCategoryId);
        material.title = materialTitle;
        material.url = materialURL;
        this.materialsService.addMaterial(material).subscribe(
            () => {
                this.addMaterialLoading = false;
                window.location.reload();
            },
            (error: HttpErrorResponse) => {
                this.addMaterialLoading = false;
                alert('Something is wrong, the material title may be already existed!');
            }
        );
    }

    openCourseAnnouncements(): void {
        this.router.navigate(['announcementPage'], {
            queryParams: { courseId: this.course.id },
            relativeTo: this.activatedRoute.parent,
        });
    }

    openCourseTasks(): void {
        this.router.navigate(['tasksPage'], {
            queryParams: { courseId: this.course.id },
            relativeTo: this.activatedRoute.parent,
        });
    }
}
