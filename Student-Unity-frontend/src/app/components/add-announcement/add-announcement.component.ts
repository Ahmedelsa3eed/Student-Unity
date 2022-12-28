import { AnnouncementService } from './../../services/announcement.service';
import { Announcement } from 'src/app/models/Announcement';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Course } from 'src/app/models/Course';
import { HttpErrorResponse } from '@angular/common/http';
import { map } from 'rxjs';
import { CoursesService } from 'src/app/services/courses.service';

@Component({
    selector: 'app-add-announcement',
    templateUrl: './add-announcement.component.html',
    styleUrls: ['./add-announcement.component.css'],
})
export class AddAnnouncementComponent implements OnInit {
    addAnnouncementForm!: FormGroup;
    announcement: Announcement = {} as Announcement;
    registeredCourses: Course[] = [];
    errorFlag: boolean = false;
    errorMessage: string = '';

    constructor(
        private fb: FormBuilder,
        private coursesService: CoursesService,
        private announcementService: AnnouncementService
    ) {}

    ngOnInit(): void {
        this.addAnnouncementForm = this.fb.group({
            course: this.fb.control(null, Validators.required),
            body: this.fb.control(null, Validators.required),
        });
        this.getCourses();
    }

    ngOnDestroy() {
        this.addAnnouncementForm.reset();
    }

    private getCourses() {
        this.coursesService
            .getUserRegisteredCourse()
            .pipe(
                map((list) => {
                    list.forEach((data: any) => {
                        let course: Course = new Course();
                        course.id = data[0];
                        course.name = data[1];
                        course.code = data[2];
                        course.revisionSubscription = data[3];
                        this.registeredCourses.push(course);
                    });
                })
            )
            .subscribe({
                error: (err: HttpErrorResponse) => {
                    if (err.status == 404) alert('User Not Found');
                },
            });
    }

    addAnnouncement(): void {
        this.prepareAnnouncementData();
        this.announcementService.addAnnouncement(this.announcement).subscribe({
            next: (res) => {
                if (res.ok && res.body) {
                    this.addAnnouncementForm.reset();
                }
            },
            error: (err) => this.httpError(err),
        });
    }

    private prepareAnnouncementData() {
        let courseName = this.addAnnouncementForm.get('course')?.value.name;
        this.announcement.courseName = courseName;
        this.announcement.body = this.addAnnouncementForm.value.body;
        let date = new Date().toISOString().split('T')[0];
        this.announcement.postedDate = date;
        this.announcement.courseId = this.registeredCourses.find(
            (course) => course.name == this.announcement.courseName
        )!.id;
    }

    // show the resulting error message
    httpError(httpError: HttpErrorResponse) {
        console.error(httpError);
        this.errorFlag = true;
        this.errorMessage = httpError.name;
        console.log(this.errorMessage);
    }

    // getters for the form controls for every field to get the error messages
    get courseName(): FormControl {
        return this.addAnnouncementForm.get('course') as FormControl;
    }

    get body(): FormControl {
        return this.addAnnouncementForm.get('body') as FormControl;
    }
}
