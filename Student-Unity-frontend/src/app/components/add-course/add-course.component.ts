import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/Course';
import { ActiveCourse } from 'src/app/models/active-course';
import { AllCoursesService } from 'src/app/services/all-courses.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
    selector: 'app-add-course',
    templateUrl: './add-course.component.html',
    styleUrls: ['./add-course.component.css'],
})
export class AddCourseComponent implements OnInit {
    course: Course = {} as Course;
    activeCourse: ActiveCourse = {} as ActiveCourse;
    addCourseForm!: FormGroup;
    postError: boolean = false;
    postErrorMessage: string = '';
    submitLoading: boolean = false;

    constructor(
        private fb: FormBuilder,
        private allCoursesService: AllCoursesService,
        private router: Router,
        private signInOutService: SignInOutService
    ) {}

    ngOnInit(): void {
        this.addCourseForm = this.fb.group({
            courseName: this.fb.control(null, [Validators.required, Validators.pattern('[a-zA-Z0-9]*')]),
            courseCode: this.fb.control(null, [Validators.required, Validators.pattern('[a-zA-Z0-9]*')]),
            status: this.fb.control(false, [Validators.required]),
            term: this.fb.control(null, [Validators.required, Validators.min(0), Validators.max(9)]),
            telegramLink: this.fb.control(null, [Validators.pattern('https://t.me/([A-Za-z0-9-/_%^&$#@!]*)')]),
            timeTable: this.fb.control(null),
        });
    }

    // on destroy of component
    ngOnDestroy() {
        this.addCourseForm.reset();
    }

    addFullCourse() {
        this.allCoursesService.addCourse(this.signInOutService.getSignedInUserSessionID(), this.course).subscribe({
            next: (res) => {
                console.log(res);
                if (this.addCourseForm.get('status')?.value) {
                    this.allCoursesService
                        .makeCourseActive(this.signInOutService.getSignedInUserSessionID(), this.course.code)
                        .subscribe({
                            next: (res) => {
                                this.allCoursesService
                                    .editActiveCourse(
                                        this.signInOutService.getSignedInUserSessionID(),
                                        this.course.code,
                                        this.activeCourse
                                    )
                                    .subscribe({
                                        next: (res) => {
                                            console.log(res);
                                            this.submitLoading = false;
                                            this.router.navigate(['/home/allCourses']);
                                        },
                                        error: (err) => this.httpError(err),
                                        complete: () => console.info('Course Submitted'),
                                    });
                            },
                            error: (err) => this.httpError(err),
                            complete: () => console.info('Course Submitted'),
                        });
                } else {
                    this.submitLoading = false;
                    this.router.navigate(['/home/allCourses']);
                }
            },
            error: (err) => this.httpError(err),
            complete: () => console.info('Course Submitted'),
        });
    }

    // copy the values from the form, and add the course.
    registerSubmitted() {
        this.submitLoading = true;
        this.course.name = this.addCourseForm.get('courseName')?.value;
        this.course.code = this.addCourseForm.get('courseCode')?.value;
        this.course.term = this.addCourseForm.get('term')?.value;
        this.course.telegramLink = this.addCourseForm.get('telegramLink')?.value;
        this.course.timeTable = this.addCourseForm.get('timeTable')?.value;
        this.activeCourse.telegramLink = this.addCourseForm.get('telegramLink')?.value;
        this.activeCourse.timeTable = this.addCourseForm.get('timeTable')?.value;
        this.addFullCourse();
    }

    // method to print the error message from the backend
    httpError(httpError: any) {
        console.error(httpError);
        this.postError = true;
        this.postErrorMessage = httpError.error;
        console.log(this.postErrorMessage);
    }

    // getters for the form controls for every field to get the error messages
    get courseName(): FormControl {
        return this.addCourseForm.get('courseName') as FormControl;
    }
    get courseCode(): FormControl {
        return this.addCourseForm.get('courseCode') as FormControl;
    }
    get status(): FormControl {
        return this.addCourseForm.get('status') as FormControl;
    }
    get term(): FormControl {
        return this.addCourseForm.get('term') as FormControl;
    }
    get telegramLink(): FormControl {
        return this.addCourseForm.get('telegramLink') as FormControl;
    }
    get timeTable(): FormControl {
        return this.addCourseForm.get('timeTable') as FormControl;
    }
}
