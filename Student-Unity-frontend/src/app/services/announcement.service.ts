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
}