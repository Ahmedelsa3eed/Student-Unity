import { Announcement } from './../../models/Announcement';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AnnouncementService } from '../../services/announcement.service';
import { SignInOutService } from '../../services/sign-in-out.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Course } from '../../models/Course';
import { map } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { CoursesService } from '../../services/courses.service';

@Component({
    selector: 'app-announcement',
    templateUrl: './announcement.component.html',
    styleUrls: ['./announcement.component.css'],
})
export class AnnouncementComponent implements OnInit {
    editAnnouncementForm!: FormGroup;
    @Input() announcemet: Announcement = new Announcement();
    @Output() removingAnnouncement = new EventEmitter<number>();
    registeredCourses: Course[] = [];
    removingSpinner: boolean = false;
    loggedInUserRole = this.signInOutService.getSignedInUserRole();

    constructor(
        private announcementService: AnnouncementService,
        private signInOutService: SignInOutService,
        private fb: FormBuilder,
        private coursesService: CoursesService
    ) {}

    ngOnInit(): void {
        this.getCourses();
        this.editAnnouncementForm = this.fb.group({
            body: this.fb.control(this.announcemet.body, [Validators.required]),
        });
    }

    ngOnDestroy(): void {
        this.removingAnnouncement.unsubscribe();
    }

    public getDate(): string {
        let d = new Date(this.announcemet.postedDate),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        let res = [year, month, day].join('-');
        return res;
    }

    public removeAnnouncement() {
        this.removingSpinner = true;
        this.announcementService.deleteAnnouncement(this.announcemet.id).subscribe((res) => {
            console.log(res);
            if (res == true) {
                this.removingAnnouncement.emit(this.announcemet.id);
                this.removingSpinner = false;
            } else {
                this.removingSpinner = false;
                console.log(res);
            }
        });
    }

    editAnnouncement() {
        this.announcementService
            .editAnnouncement(this.announcemet.id, this.editAnnouncementForm.value)
            .subscribe((res) => {
                if (res == true) {
                    this.removingSpinner = false;
                } else {
                    this.removingSpinner = false;
                    console.log(res);
                }
            });
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
}
