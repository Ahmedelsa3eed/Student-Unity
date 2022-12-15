import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from 'src/app/models/Course';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  constructor(private httpClient: HttpClient) { }

  getUserRegisteredCourse(sessionId: string): Observable<Course[]>{
    return this.httpClient.get<Course[]>(environment.baseUrl + "/AllCourses/getSubscribedCourses/" + sessionId);
  }

}
