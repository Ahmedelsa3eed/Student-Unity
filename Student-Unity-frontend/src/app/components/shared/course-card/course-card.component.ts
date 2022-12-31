import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/Course';
import { User } from 'src/app/models/User';
import { AllCoursesService } from 'src/app/services/all-courses.service';
import { NotificationService } from 'src/app/services/notification.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
    selector: 'app-course-card',
    templateUrl: './course-card.component.html',
    styleUrls: ['./course-card.component.css'],
})
export class CourseCardComponent implements OnInit {
    @Input() course: Course = {} as Course;
    @Input() privilege: boolean = false;

    constructor(
        private allCoursesService: AllCoursesService,
        private signInOutService: SignInOutService,
        private router: Router,
        private notificationService: NotificationService
    ) {}

    // method to print the error message from the backend

    deleteCourse(): void {
        this.allCoursesService
            .deleteCourse(this.signInOutService.getSignedInUserSessionID(), this.course.code)
            .subscribe({
                next: (res) => {
                    console.log(res);
                    location.reload();
                },
                error: (err) => this.router.navigate(['home/allCourses']),
                complete: () => console.info('Course Submited'),
            });
    }
    subscribeCourse(): void {
        this.allCoursesService
            .registerCourse(this.signInOutService.getSignedInUserSessionID(), this.course.id)
            .subscribe({
                next: (res) => {
                    console.log(res);
                    this.router.navigate(['home/allCourses']);
                },
                error: (err) => this.router.navigate(['home/allCourses']),
                complete: () => console.info('Course Submited'),
            });
        this.notificationService.subscribeToTopic(this.course.name).subscribe({
            next: (res) => {
                console.log(res);
            },
            error: (err) => {
                console.log(err);
            }
        });
    }
    ngOnInit(): void {}

    ngOnChanges(): void {
        console.log(this.course);
    }
}
