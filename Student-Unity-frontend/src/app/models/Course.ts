export class Course {
    id: number;
    name: string;
    code: string;
    timeTable: string;
    telegramLink: string;
    activeCourse: [];
    notificationsToken: string;
    term: number;
    revisionSubscription: boolean;

    constructor() {
        this.id = 0;
        this.name = '';
        this.code = '';
        this.timeTable = '';
        this.telegramLink = '';
        this.notificationsToken = '';
        this.revisionSubscription = true;
        this.term = 0;
        this.activeCourse = [];
    }
}
