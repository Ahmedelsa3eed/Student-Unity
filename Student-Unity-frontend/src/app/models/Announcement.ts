import { Course } from './Course';

export class Announcement {
    courseName: string;
    body: string;
    postedDate: string; // yyyy-mm-dd
    courseId: number;
    id: number = 0;

    constructor() {
        this.courseName = '';
        this.body = '';
        this.postedDate = '';
        this.courseId = 0;
    }
}
