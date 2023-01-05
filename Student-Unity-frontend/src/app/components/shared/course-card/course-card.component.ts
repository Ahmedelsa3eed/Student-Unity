import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/app/models/Course';
import { User } from 'src/app/models/User';
import { AllCoursesService } from 'src/app/services/all-courses.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { ifStmt } from '@angular/compiler/src/output/output_ast';
import { NotificationService } from 'src/app/services/notification.service';


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
    postSubscribeError: boolean = false;
    postDeleteError: boolean = false;
    errorMessage: string = '';

    constructor(
        private allCoursesService: AllCoursesService,
        private signInOutService: SignInOutService,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private notificationService: NotificationService
    ) {}

    // method to print the error message from the backend

    deleteCourse(): void {
        this.deleteLoading = true;
        this.allCoursesService
            .deleteCourse(this.signInOutService.getSignedInUserSessionID(), this.course.code)
            .subscribe({
                next: (res) => {
                    this.deleteLoading = false;
                    location.reload();
                },
                error: (err) => {
                    this.deleteLoading = false;
                    this.postDeleteError = true;
                    this.errorMessage = err.error;
                },
                complete: () => {
                    this.deleteLoading = false;
                },
            });
    }

    editCourse(): void {
        this.router.navigate(['home/addCourse'], {
            queryParams: {
                courseName: this.course.name,
                courseCode: this.course.code,
                status: this.course.activeCourse !== null,
                term: this.course.term,
                telegramLink: this.course.telegramLink,
                timeTable: this.course.timeTable,
            },
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
                error: (err) => {
                    this.subscribeLoading = false;
                    this.postSubscribeError = true;
                    this.errorMessage = err.error;
                },
                complete: () => {
                    this.subscribeLoading = false;
                    console.info('Course Submitted');
                },
            });
        this.notificationService.subscribeToTopic(this.course.code).subscribe();
    }
    openCoursePage(courseId: number) {
        this.router.navigate(['course'], {
            queryParams: { courseId: courseId },
            relativeTo: this.activatedRoute.parent,
        });
    }

    ngOnInit(): void {}
}
