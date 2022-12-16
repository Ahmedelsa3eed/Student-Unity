import { Component, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { AllCoursesService } from 'src/app/services/all-courses.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { CourseCard } from 'src/app/models/course-card';

@Component({
  selector: 'app-all-courses',
  templateUrl: './all-courses.component.html',
  styleUrls: ['./all-courses.component.css']
})
export class AllCoursesComponent implements OnInit {

  showFilters: boolean = false;
  checkedItems: string[] = [];
  sub!: Subscription;
  courses: CourseCard[] = [];

  terms: CourseCard[][] = [];

  constructor(private signInOutService: SignInOutService, private allCoursesService: AllCoursesService) { }


  ngOnInit(): void {
    this.signInOutService.getSignedInUser();
    this.sub = this.allCoursesService.getAllCourses().subscribe({
      next: courses => {
        this.courses = courses;
      },
      error: err => console.log(err)
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }


  filterTerms(): void {
    
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



}
