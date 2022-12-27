import { Course } from './Course';

export class MaterialCategory {
    id: number;
    courseId: number;
    name: string;

    constructor() {
        this.id = -1;
        this.courseId = -1;
        this.name = '';
    }
}
