import { STATUS } from "./Status";

export class Task {
    taskId: string;
    title: string;
    courseCode: string; // should be course name instead
    dueDate: string;
    telegramLink: string;
    status: STATUS;

	constructor() {
        this.taskId = "";
        this.title = "";
        this.courseCode = "";
        this.dueDate = "";
        this.telegramLink = "";
        this.status = STATUS.NotCompleted;
	}

}