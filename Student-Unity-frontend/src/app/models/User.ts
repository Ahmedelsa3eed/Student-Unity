export class User {
  studentId: number;
  firstName: string;
  lastName: string;
  email: string;
  role: string;
  sessionID: string;

  constructor() {
    this.studentId = 0;
    this.firstName = '';
    this.lastName = '';
    this.email = '';
    this.role = '';
    this.sessionID = '';
  }

}
