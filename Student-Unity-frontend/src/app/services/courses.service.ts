import { HttpClient, HttpResponse, HttpStatusCode } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from 'src/app/models/Course';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  constructor(private httpClient: HttpClient) { }

  getUserRegisteredCourse(sessionId: string): Observable<any>{
    return this.httpClient.get<any>(environment.baseUrl + "/AllCourses/getRegisteredCourses/" + sessionId);
  }

  unRegisterCourse(sessionId: string, courseId: number): Observable<HttpStatusCode>{
    return this.httpClient.delete<HttpStatusCode>(environment.baseUrl + "/AllCourses/unRegisterCourse/" + sessionId + "/" + courseId);
  }

  toggleRVSubscription(sessionId: string, courseId: number, oldRevisionSubscription: boolean): Observable<HttpStatusCode>{
    return this.httpClient.put<HttpStatusCode>(environment.baseUrl + "/AllCourses/toggleRVSubscription", {}, {
      params: {
        sessionId: sessionId,
        courseId: courseId,
        oldRevisionSubscription: oldRevisionSubscription
      }
    });
  }

}
