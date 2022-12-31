import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';
import { Course } from 'src/app/models/Course';
import { CoursesService } from 'src/app/services/courses.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
    selector: 'app-my-courses',
    templateUrl: './my-courses.component.html',
    styleUrls: ['./my-courses.component.css'],
})
export class MyCoursesComponent implements OnInit {
    registeredCourses: Course[] = [];

    constructor(
        private coursesService: CoursesService,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private notificationService: NotificationService
    ) {}

    ngOnInit(): void {
        this.coursesService
            .getUserRegisteredCourse()
            .pipe(
                map((list) => {
                    list.forEach((data: any) => {
                        let course: Course = new Course();
                        course.id = data[0];
                        course.name = data[1];
                        course.code = data[2];
                        course.revisionSubscription = data[3];
                        this.registeredCourses.push(course);
                    });
                })
            )
            .subscribe(
                () => {},
                (error: HttpErrorResponse) => {
                    if (error.status == 404) alert('User Not Found');
                }
            );
    }

    unRegisterCourse(courseId: number) {
        let course = new Course();
        for (let i=0; i<this.registeredCourses.length; i++){
            if (this.registeredCourses[i].id == courseId){
                course = this.registeredCourses[i];
                break;
            }
        }
        this.coursesService.unRegisterCourse(courseId).subscribe(
            () => {
                this.registeredCourses.forEach((course, index) => {
                    if (course.id == courseId) this.registeredCourses.splice(index, 1);
                });
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
            }
        );
        
        this.notificationService.unSubscribe(course?.name).subscribe();
    }

    toggleRVSubscription(course: Course) {
        this.coursesService.toggleRVSubscription(course.id, course.revisionSubscription).subscribe(
            () => {
                course.revisionSubscription = !course.revisionSubscription;
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
            }
        );
    }

    openCoursePage(courseId: number) {
        this.router.navigate(['course'], {
            queryParams: { courseId: courseId },
            relativeTo: this.activatedRoute.parent,
        });
    }
}
