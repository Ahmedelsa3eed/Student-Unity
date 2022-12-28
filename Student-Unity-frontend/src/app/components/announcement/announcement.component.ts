import { Announcement } from './../../models/Announcement';
import { Component, Input, OnInit } from '@angular/core';

@Component({
    selector: 'app-announcement',
    templateUrl: './announcement.component.html',
    styleUrls: ['./announcement.component.css'],
})
export class AnnouncementComponent implements OnInit {
    @Input() announcemet: Announcement = new Announcement();
    id: string = '';

    constructor() {}

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

}
