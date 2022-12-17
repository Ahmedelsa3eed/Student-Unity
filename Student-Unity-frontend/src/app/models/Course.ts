export class Course {
  id: number;
  name: string;
  code: string;
  timeTable: string;
  telegramLink: string;
  status: boolean;
  notificationsToken: string;
  revisionSubscription: boolean;

  constructor() {
    this.id = 0;
    this.name = '';
    this.code = '';
    this.timeTable = '';
    this.telegramLink = '';
    this.status = false;
    this.notificationsToken = "";
    this.revisionSubscription = true;
  }

}
