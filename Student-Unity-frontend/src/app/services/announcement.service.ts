import { Observable } from 'rxjs';
import { Announcement } from 'src/app/models/Announcement';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { SignInOutService } from './sign-in-out.service';

@Injectable({
    providedIn: 'root',
})
export class AnnouncementService {
    private url = environment.baseUrl + '/announcement';

    constructor(private http: HttpClient, private userService: SignInOutService) {}

    public addAnnouncement(announcement: Announcement): Observable<any> {
        return this.http.post(this.url + '/add', announcement, {
            params: {
                sessionId: this.userService.getSignedInUserSessionID(),
            },
            responseType: 'json',
        });
    }

    public getAnnouncements(): Observable<HttpResponse<Announcement[]>> {
        return this.http.get<Announcement[]>(this.url + '/all', {
            observe: 'response',
            params: {
                sessionId: this.userService.getSignedInUserSessionID(),
            },
            responseType: 'json',
        });
    }

    public filterAnnouncements(courseId: number): Observable<HttpResponse<Announcement[]>> {
        return this.http.get<Announcement[]>(this.url + '/filter', {
            observe: 'response',
            params: {
                sessionId: this.userService.getSignedInUserSessionID(),
                courseId: courseId,
            },
            responseType: 'json',
        });
    }

    public deleteAnnouncement(announcementId: number): Observable<any> {
        return this.http.delete(this.url + '/delete', {
            params: {
                sessionId: this.userService.getSignedInUserSessionID(),
                announcementId: announcementId,
            },
            responseType: 'json',
        });
    }

    editAnnouncement(id: number, value: any) {
        return this.http.put(this.url + '/update', value, {
            params: {
                sessionId: this.userService.getSignedInUserSessionID(),
                announcementId: id,
            },
            responseType: 'json',
        });
    }
}
