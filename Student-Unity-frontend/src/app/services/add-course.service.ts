import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Course } from '../models/Course';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddCourseService {

  constructor(private http: HttpClient) { }

  postCourseData(course: Course): Observable<HttpResponse<string>> {
    return this.http.post(`${environment.baseUrl}/add-course`, course, {
      observe: "response",
      responseType: "text",
    });
  }
}
