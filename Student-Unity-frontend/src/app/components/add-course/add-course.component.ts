import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignUpData } from 'src/app/models/sign-up-data.model';
import { AddCourseService } from 'src/app/services/add-course.service';
import { ConfirmedValidator } from '../shared/match.validator';
import { Course } from 'src/app/models/Course';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {

  registerForm!: FormGroup;
  postError: boolean = false;
  postErrorMessage: string = "";
  course: Course = {} as Course;
  constructor(private fb: FormBuilder, private addCourseService: AddCourseService, private router: Router) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group ({
      courseName: this.fb.control(null, [Validators.required, Validators.pattern("[a-zA-Z0-9]*")]),
      courseCode: this.fb.control(null, [Validators.required, Validators.pattern("[a-zA-Z0-9]*")]),
      status: this.fb.control(null, [Validators.required, Validators.pattern("^[A-Za-z0-9._%+-]+@alexu\.edu\.eg")]),
      term: this.fb.control(null, [Validators.required, Validators.min(0), Validators.max(10)])
    });
  }

  // on destroy of component
  ngOnDestroy() {
    this.registerForm.reset();
  }

  // Method to register a new user
  registerSubmitted() {
    this.course.name = this.registerForm.get('courseName')?.value;
    this.course.code = this.registerForm.get('courseCode')?.value;
    this.course.status = this.registerForm.get('status')?.value;
    this.course.term = this.registerForm.get('term')?.value;

    this.addCourseService.postCourseData(this.course).subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigate(['home/allCourses'])
      },
      error: (err) => this.httpError(err),
      complete: () => console.info('Course Submited')
    })
  }

  // method to print the error message from the backend
  httpError(httpError: any) {
    console.error(httpError);
    this.postError = true;
    this.postErrorMessage = httpError.error;
    console.log(this.postErrorMessage);
  }

  // getters for the form controls for every field to get the error messages
  get courseName(): FormControl{
    return this.registerForm.get('courseName') as FormControl;
  }
  get courseCode(): FormControl{
    return this.registerForm.get('courseCode') as FormControl;
  }
  get status(): FormControl{
    return this.registerForm.get('status') as FormControl;
  }
  get term(): FormControl{
    return this.registerForm.get('term') as FormControl;
  }

}
