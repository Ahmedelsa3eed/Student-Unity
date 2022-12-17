import { Component, OnInit, OnChanges, Input, Output } from '@angular/core';
import { User } from 'src/app/models/User';
import { CourseCard } from 'src/app/models/course-card';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
  selector: 'app-course-card',
  templateUrl: './course-card.component.html',
  styleUrls: ['./course-card.component.css']
})
export class CourseCardComponent implements OnInit {

  loggedInUser = new User();

  @Input() course: CourseCard = {name:"", code:"", status:true, term: 0};

  constructor(private signInOutService: SignInOutService) { }

  getSignedInUser(){
    this.signInOutService.getSignedInUser().subscribe(
      res => {
        console.log(res);
        if (res.body) {
          this.loggedInUser = res.body;
        }
    },
      err => {
        console.log(err);
      }
      );
  }

  ngOnInit(): void {
    this.getSignedInUser();
  }

  ngOnChanges(): void {
    console.log(this.course);
  }


}
