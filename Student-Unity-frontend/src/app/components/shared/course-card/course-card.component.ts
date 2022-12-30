import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/Course';
import { User } from 'src/app/models/User';
import { AllCoursesService } from 'src/app/services/all-courses.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
    selector: 'app-course-card',
    templateUrl: './course-card.component.html',
    styleUrls: ['./course-card.component.css'],
})
export class CourseCardComponent implements OnInit {
    @Input() course: Course = {} as Course;
    @Input() privilege: boolean = false;
    @Input() subscribed: boolean = false;

    deleteLoading: boolean = false;
    subscribeLoading: boolean = false;

    constructor(
        private allCoursesService: AllCoursesService,
        private signInOutService: SignInOutService,
        private router: Router
    ) {}

    // method to print the error message from the backend

    deleteCourse(): void {
        this.deleteLoading = true;
        this.allCoursesService
            .deleteCourse(this.signInOutService.getSignedInUserSessionID(), this.course.code)
            .subscribe({
                next: (res) => {
                    console.log(res);
                    this.deleteLoading = false;
                    location.reload();
                },
                error: (err) => this.router.navigate(['home/allCourses']),
                complete: () => console.info('Course Submited'),
            });
    }
    subscribeCourse(): void {
        this.subscribeLoading = true;
        this.allCoursesService
            .registerCourse(this.signInOutService.getSignedInUserSessionID(), this.course.id)
            .subscribe({
                next: (res) => {
                    console.log(res);
                    this.subscribeLoading = false;
                    this.subscribed = true;
                },
                error: (err) => this.router.navigate(['home/allCourses']),
                complete: () => console.info('Course Submited'),
            });
    }
    ngOnInit(): void {}
}
