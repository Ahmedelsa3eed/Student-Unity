import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, Subscription, map } from 'rxjs';
import { AllCoursesService } from 'src/app/services/all-courses.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { User } from 'src/app/models/User';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/Course';
import { CoursesService } from 'src/app/services/courses.service';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
    selector: 'app-all-courses',
    templateUrl: './all-courses.component.html',
    styleUrls: ['./all-courses.component.css'],
})
export class AllCoursesComponent implements OnInit {
    private _courseSearch: string = '';
    sub!: Subscription;

    allCourses: Course[] = [];
    filteredCourses: Course[] = [];
    showFilteredList: boolean = false;
    private _filterByStatus: boolean = false;
    loggedInUser = new User();
    privilege: boolean = false;
    pageLoading: boolean = true;

    constructor(
        private router: Router,
        private signInOutService: SignInOutService,
        private allCoursesService: AllCoursesService,
        private coursesService: CoursesService
    ) {}

    ngOnInit(): void {
        this.getSignedInUser();
        this.sub = this.allCoursesService.getAllCourses().subscribe({
            next: (courses) => {
                console.log(courses);
                this.allCourses = courses;
                this.coursesService
                    .getUserRegisteredCourse()
                    .pipe(
                        map((list) => {
                            list.forEach((data: any) => {
                                this.allCourses.forEach((course) => {
                                    if (course.id == data[0]) {
                                        course.subscribed = true;
                                    }
                                });
                            });
                        })
                    )
                    .subscribe({
                        next: () => {},
                        error: (err) => console.log(err),
                        complete: () => {
                            this.pageLoading = false;
                        },
                    });
            },
            error: (err) => console.log(err),
        });
    }

    addCourse(): void {
        this.router.navigate(['home/addCourse']);
    }

    get filterByStatus(): boolean {
        return this._filterByStatus;
    }

    set filterByStatus(value: boolean) {
        this._filterByStatus = value;
        this.filterCourses();
    }

    set courseSearch(value: string) {
        this._courseSearch = value;
        this.filterCourses();
    }

    get courseSearch(): string {
        return this._courseSearch;
    }

    reverse(event: any): void {
        let expanded = event.target.ariaExpanded;
        let el = event.target.children[1];

        if (expanded == 'true') {
            el.classList.remove('down');
            el.classList.add('up');
        } else {
            el.classList.remove('up');
            el.classList.add('down');
        }
    }
    getSignedInUser() {
        this.loggedInUser = this.signInOutService.getSignedInUser();
        if (this.loggedInUser.role === 'admin') this.privilege = true;
        console.log(this.privilege);
    }

    filterCourses(): void {
        // filter by course code or course name
        this.filteredCourses = this.allCourses.filter(
            (course: Course) =>
                course.code.toLocaleLowerCase().includes(this._courseSearch.toLocaleLowerCase()) ||
                course.name.toLocaleLowerCase().includes(this._courseSearch.toLocaleLowerCase())
        );

        // filter by status
        if (this._filterByStatus) {
            this.filteredCourses = this.filteredCourses.filter((course: Course) => course.activeCourse !== null);
        }
        this.showFilteredList = this._filterByStatus || this._courseSearch.length > 0;
    }

    ngOnDestroy(): void {
        this.sub.unsubscribe();
    }
}
