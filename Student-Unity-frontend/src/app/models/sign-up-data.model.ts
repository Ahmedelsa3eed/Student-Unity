export class SignUpData {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  studentId: number;

  constructor() {
    this.firstName = '';
    this.lastName = '';
    this.email = '';
    this.password = '';
    this.studentId = 0;
  }
}
