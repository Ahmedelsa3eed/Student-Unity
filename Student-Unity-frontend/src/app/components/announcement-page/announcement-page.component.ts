import { AnnouncementService } from './../../services/announcement.service';
import { Component, OnInit } from '@angular/core';
import { Announcement } from 'src/app/models/Announcement';
import { map } from 'rxjs';
import { Course } from '../../models/Course';
import { HttpErrorResponse } from '@angular/common/http';
import { CoursesService } from '../../services/courses.service';
import { SignInOutService } from '../../services/sign-in-out.service';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-announcement-page',
    templateUrl: './announcement-page.component.html',
    styleUrls: ['./announcement-page.component.css'],
})
export class AnnouncementPageComponent implements OnInit {
    announcementList: Announcement[] | null = [];
    registeredCourses: Course[] = [];
    selectedCourseName: string = 'All';
    showFilterSelector: boolean = true;

    constructor(
        private announcementService: AnnouncementService,
        private coursesService: CoursesService,
        private signInOutService: SignInOutService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit(): void {
        this.getAnnouncements();
        this.getSubscribedCourses();
        this.activatedRoute.queryParams.subscribe((params) => {
            let courseId = params['courseId'];
            if (courseId == undefined) return;
            this.showFilterSelector = false;
            let course: Course = new Course();
            this.coursesService.getCourseById(courseId).subscribe(
                (response) => {
                    course.id = response.id;
                    course.code = response.code;
                    course.name = response.name;
                    course.telegramLink = response.activeCourse?.telegramLink;
                    course.timeTable = response.activeCourse?.timeTable;
                    this.filterAnnouncement(course);
                },
                (error: HttpErrorResponse) => {
                    if (error.status == 404) alert('Course Not Found');
                }
            );
        });
    }

    getAnnouncements() {
        this.announcementService
            .getAnnouncements()
            .pipe(
                map((list) => {
                    list.body!.forEach((data: any) => {
                        let announcement: Announcement = new Announcement();
                        announcement.courseName = data[0];
                        announcement.body = data[1];
                        announcement.postedDate = data[2];
                        // @ts-ignore
                        this.announcementList.push(announcement);
                    });
                })
            )
            .subscribe({
                error: (err) => console.error(err),
            });
    }

    public getSubscribedCourses() {
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
            .subscribe(
                () => {},
                (error: HttpErrorResponse) => {
                    if (error.status == 404) alert('User Not Found');
                }
            );
    }

    public filterAnnouncement($event: any) {
        console.log($event);
        this.announcementList = [];
        if ($event == 'All') {
            this.getAnnouncements();
        } else {
            this.announcementService
                .filterAnnouncements($event.id)
                .pipe(
                    map((list) => {
                        list.body!.forEach((data: any) => {
                            let announcement: Announcement = new Announcement();
                            announcement.courseName = data[0];
                            announcement.body = data[1];
                            announcement.postedDate = data[2];
                            // @ts-ignore
                            this.announcementList.push(announcement);
                        });
                    })
                )
                .subscribe({
                    error: (err) => console.error(err),
                });
        }
    }
}
