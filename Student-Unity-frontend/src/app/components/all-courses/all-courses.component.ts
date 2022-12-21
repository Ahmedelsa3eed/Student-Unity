import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, Subscription } from 'rxjs';
import { AllCoursesService } from 'src/app/services/all-courses.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { AccountService } from 'src/app/services/account.service';
import { User } from 'src/app/models/User';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/Course';

@Component({
    selector: 'app-all-courses',
    templateUrl: './all-courses.component.html',
    styleUrls: ['./all-courses.component.css'],
})
export class AllCoursesComponent implements OnInit {
    private _courseSearch: string = '';
    private numberOfTerms: number = 10;
    sub!: Subscription;
    courses?: Observable<Course[]>;
    courses$ = new BehaviorSubject<Course[]>([]);

    terms: Course[][] = [] as Course[][];
    filteredCourses: Course[] = [];
    showFilteredList: boolean = false;
    private _filterByStatus: boolean = false;
    loggedInUser = new User();
    privilege: boolean = false;

    constructor(
        private router: Router,
        private signInOutService: SignInOutService,
        private allCoursesService: AllCoursesService
    ) {}

    addCourse(): void {
        this.router.navigate(['home/addCourse']);
    }

    ngOnInit(): void {
        this.getSignedInUser();
        this.sub = this.allCoursesService.getAllCourses().subscribe({
            next: (courses) => {
                console.log(courses);
                this.courses$.next(courses);
                this.courses = this.courses$.asObservable();
            },
            error: (err) => console.log(err),
        });
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
        this.signInOutService.getSignedInUser().subscribe(
            (res) => {
                console.log(res);
                if (res.body) {
                    this.loggedInUser = res.body;
                    if (this.loggedInUser.role === 'admin') this.privilege = true;
                    console.log(this.privilege);
                }
            },
            (err) => {
                console.log(err);
            }
        );
    }

    filterCourses(): void {
        // filter by course code or course name
        this.filteredCourses = this.courses$.value.filter(
            (course: Course) =>
                course.code.toLocaleLowerCase().includes(this._courseSearch.toLocaleLowerCase()) ||
                course.name.toLocaleLowerCase().includes(this._courseSearch.toLocaleLowerCase())
        );

        // filter by status
        if (this._filterByStatus) {
            this.filteredCourses = this.filteredCourses.filter((course: Course) => course.status === true);
        }
        this.showFilteredList = this._filterByStatus || this._courseSearch.length > 0;
    }

    ngOnDestroy(): void {
        this.sub.unsubscribe();
    }
}
