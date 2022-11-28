export class User {
  studentId: number;
  name: string;
  email: string;
  password: string;
  role: string;
  sessionID: string;

  constructor() {
    this.studentId = 0;
    this.name = '';
    this.email = '';
    this.password = '';
    this.role = '';
    this.sessionID = '';
  }

}
