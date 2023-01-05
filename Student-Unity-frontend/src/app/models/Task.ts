export class Task {
    taskId: number;
    title?: string;
    courseCode?: string;
    course?: any;
    dueDate?: string;
    telegramLink?: string;
    status?: boolean;

    constructor(
        taskId?: number,
        courseCode?: string,
        title?: string,
        dueDate?: string,
        telegramLink?: string,
        status?: boolean
    ) {
        if (taskId) {
            this.taskId = taskId;
            this.title = title;
            this.courseCode = courseCode;
            this.dueDate = dueDate;
            this.telegramLink = telegramLink;
            this.status = status;
        } else {
            this.taskId = 0;
            this.title = '';
            this.courseCode = '';
            this.course = null;
            this.dueDate = '';
            this.telegramLink = '';
            this.status = false;
        }
    }
}
