import { Component, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { AllCoursesService } from 'src/app/services/all-courses.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { CourseCard } from 'src/app/models/course-card';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-all-courses',
  templateUrl: './all-courses.component.html',
  styleUrls: ['./all-courses.component.css']
})
export class AllCoursesComponent implements OnInit {
  private numberOfTerms: number = 10;
  showFilters: boolean = false;
  checkedItems: string[] = [];
  sub!: Subscription;
  courses: CourseCard[] = [];
  terms: CourseCard[][] = [] as CourseCard[][];

  constructor(private signInOutService: SignInOutService, private allCoursesService: AllCoursesService) { }


  ngOnInit(): void {
    this.signInOutService.getSignedInUser();
    this.sub = this.allCoursesService.getAllCourses().subscribe({
      next: courses => {
        this.courses = courses;
        this.filterTerms();
      },
      error: err => console.log(err)
    });

  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
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

  dropDownFilters(): void {
      this.showFilters = !this.showFilters;
  }

  itemCheck(event: any): void {
    let item = event.target;
    if (!this.checkedItems.includes(item.innerText)) {
      item.classList.remove('btn-light');
      item.classList.add('btn-primary');
      this.checkedItems.push(item.innerText);
    } else {
      item.classList.remove('btn-primary');
      item.classList.add('btn-light');
      this.checkedItems.splice(this.checkedItems.indexOf(item.innerText), 1);
    }
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

  filterCourses(): void {
    
  }


}
