import { Announcement } from './../../models/Announcement';
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AnnouncementService} from "../../services/announcement.service";
import {SignInOutService} from "../../services/sign-in-out.service";

@Component({
    selector: 'app-announcement',
    templateUrl: './announcement.component.html',
    styleUrls: ['./announcement.component.css'],
})
export class AnnouncementComponent implements OnInit {
    @Input() announcemet: Announcement = new Announcement();
    @Output() removingAnnouncement = new EventEmitter<number>();
    id: string = '';
    removingSpinner: boolean = false;
    loggedInUserRole = this.signInOutService.getSignedInUserRole();


    constructor(private announcementService:AnnouncementService,
                private signInOutService:SignInOutService) {}

    ngOnInit(): void {
        this.id = this.generateIdTag();
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

    private generateIdTag(): string {
        let randomLetter = String.fromCharCode(65 + Math.floor(Math.random() * 26));
        let uniqueId = randomLetter + Date.now();
        return uniqueId;
    }

    public removeAnnouncement(){
      this.removingSpinner = true;
        this.announcementService.deleteAnnouncement(this.announcemet.id).subscribe(
            (res) => {
              console.log(res);
              if (res == true) {
                this.removingAnnouncement.emit(this.announcemet.id);
                this.removingSpinner = false;
              }else {
                this.removingSpinner = false;
                console.log(res);
              }
            }
        );
    }

    }

