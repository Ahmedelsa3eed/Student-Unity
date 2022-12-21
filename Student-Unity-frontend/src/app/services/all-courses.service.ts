import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from '../models/Course';
import { FnParam } from '@angular/compiler/src/output/output_ast';

@Injectable({
    providedIn: 'root',
})
export class AllCoursesService {
    constructor(private httpClient: HttpClient) {}

    getAllCourses(): Observable<Course[]> {
        return this.httpClient.get<Course[]>(`${environment.baseUrl}/AllCourses/getAllCourses`);
    }
    postCourseData(sessionId: string, course: Course): Observable<HttpResponse<string>> {
        return this.httpClient.post(`${environment.baseUrl}/AllCourses/addCourse`, {sessionId, course}, {
            observe: 'response',
            responseType: 'text',
        });
    }
    deleteCourse(sessionId: string, code: string): Observable<HttpResponse<string>> {
        return this.httpClient.post(`${environment.baseUrl}/AllCourses/removeCourse`, {sessionId, code}, {
            observe: 'response',
            responseType: 'text',
        });
    }
    registerCourse(sessionId: string, courseId: number): Observable<HttpResponse<string>> {
        return this.httpClient.post(`${environment.baseUrl}/AllCourses/registerCourse`, {sessionId, courseId}, {
            observe: 'response',
            responseType: 'text',
        });
    }
}
