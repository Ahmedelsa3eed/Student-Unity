import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from '../models/Course';

@Injectable({
    providedIn: 'root',
})
export class AllCoursesService {
    constructor(private httpClient: HttpClient) {}

    getAllCourses(): Observable<Course[]> {
        return this.httpClient.get<Course[]>('/assets/all-courses-data.json');
    }
    postCourseData(course: Course): Observable<HttpResponse<string>> {
        return this.httpClient.post(`${environment.baseUrl}/add-course`, course, {
            observe: 'response',
            responseType: 'text',
        });
    }
    deleteCourse(course: Course): Observable<HttpResponse<string>> {
        return this.httpClient.post(`${environment.baseUrl}/delete-course`, course, {
            observe: 'response',
            responseType: 'text',
        });
    }
}
