import { AnnouncementService } from './../../services/announcement.service';
import { Component, OnInit } from '@angular/core';
import { Announcement } from 'src/app/models/Announcement';
import { map } from 'rxjs';

@Component({
    selector: 'app-announcement-page',
    templateUrl: './announcement-page.component.html',
    styleUrls: ['./announcement-page.component.css'],
})
export class AnnouncementPageComponent implements OnInit {
    announcementList: Announcement[] = [];

    constructor(private announcementService: AnnouncementService) {}

    ngOnInit(): void {
        this.getAnnouncements();
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
                        this.announcementList.push(announcement);
                    });
                })
            )
            .subscribe({
                error: (err) => console.error(err),
            });
    }
}
