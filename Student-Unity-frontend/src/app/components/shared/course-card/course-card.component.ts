import { Component, OnInit, OnChanges, Input, Output } from '@angular/core';
import { CourseCard } from 'src/app/models/course-card';

@Component({
  selector: 'app-course-card',
  templateUrl: './course-card.component.html',
  styleUrls: ['./course-card.component.css']
})
export class CourseCardComponent implements OnInit {

  @Input() course: CourseCard = {name:"", code:"", status:true, term: 0};

  constructor() { }

  ngOnInit(): void {

  }

  ngOnChanges(): void {
    console.log(this.course);
  }


}
