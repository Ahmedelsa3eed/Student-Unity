import { Component, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { AllCoursesService } from 'src/app/services/all-courses.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { CourseCard } from 'src/app/models/course-card';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { AccountService } from 'src/app/services/account.service';
import { User } from 'src/app/models/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-courses',
  templateUrl: './all-courses.component.html',
  styleUrls: ['./all-courses.component.css']
})
export class AllCoursesComponent implements OnInit {
  private _courseSearch: string = '';
  private numberOfTerms: number = 10;
  sub!: Subscription;
  courses: CourseCard[] = [];
  terms: CourseCard[][] = [] as CourseCard[][];
  filteredCourses: CourseCard[] = [];
  showFilteredList: boolean = false;
  private _filterByStatus: boolean = false;
  loggedInUser = new User();
  privilege: boolean = false;

  constructor(private router: Router, private signInOutService: SignInOutService, private allCoursesService: AllCoursesService) { }

  addCourse(): void {
    this.router.navigate(['home/addCourse']);
  }

  ngOnInit(): void {
    this.getSignedInUser();
    this.sub = this.allCoursesService.getAllCourses().subscribe({
      next: courses => {
        this.courses = courses;
        this.filterTerms();
      },
      error: err => console.log(err)
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

  filterTerms(): void {
    for (let i = 0; i < this.numberOfTerms; i++) {
      this.terms.push( [] );
    }
    for (const course of this.courses) {
      this.terms[course.term].push( course );
    }
    for (let i = 0; i < this.numberOfTerms; i++) {
      console.log(this.terms[i]);
    }
    console.log(this.terms);
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
  getSignedInUser(){
    this.signInOutService.getSignedInUser().subscribe(
      res => {
        console.log(res);
        if (res.body) {
          this.loggedInUser = res.body;
          if (this.loggedInUser.role === 'admin') this.privilege = true;
          console.log(this.privilege);
        }
    },
      err => {
        console.log(err);
      }
      );
  }

  filterCourses(): void {
    // filter by course code or course name
    this.filteredCourses = this.courses.filter((course: CourseCard) =>
      course.code.toLocaleLowerCase().includes(this._courseSearch.toLocaleLowerCase()) ||
      course.name.toLocaleLowerCase().includes(this._courseSearch.toLocaleLowerCase())
      );

    // filter by status
    if (this._filterByStatus){
      this.filteredCourses = this.filteredCourses.filter((course: CourseCard) => course.status === true);
    }

    this.showFilteredList = this._filterByStatus || this._courseSearch.length > 0;
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
