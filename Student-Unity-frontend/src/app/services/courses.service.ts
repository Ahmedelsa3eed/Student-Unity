import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { HttpClient, HttpStatusCode } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root',
})
export class CoursesService {
    constructor(private httpClient: HttpClient, private signInOutService: SignInOutService) {}

    getCourseById(courseId: number): Observable<any> {
        return this.httpClient.get<any>(environment.baseUrl + '/AllCourses/getCourseById', {
            params: {
                id: courseId,
            },
        });
    }

    getUserRegisteredCourse(): Observable<any> {
        let sessionId = this.signInOutService.getSignedInUserSessionID();
        return this.httpClient.get<any>(environment.baseUrl + '/AllCourses/getRegisteredCourses/' + sessionId);
    }

    unRegisterCourse(courseId: number): Observable<HttpStatusCode> {
        let sessionId = this.signInOutService.getSignedInUserSessionID();
        return this.httpClient.delete<HttpStatusCode>(
            environment.baseUrl + '/AllCourses/unRegisterCourse/' + sessionId + '/' + courseId
        );
    }

    toggleRVSubscription(courseId: number, oldRevisionSubscription: boolean): Observable<HttpStatusCode> {
        let sessionId = this.signInOutService.getSignedInUserSessionID();
        return this.httpClient.put<HttpStatusCode>(
            environment.baseUrl + '/AllCourses/toggleRVSubscription',
            {},
            {
                params: {
                    sessionId: sessionId,
                    courseId: courseId,
                    oldRevisionSubscription: oldRevisionSubscription,
                },
            }
        );
    }
}
