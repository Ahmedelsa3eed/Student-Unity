export class SignUpData {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  id: number;

  constructor() {
    this.firstName = '';
    this.lastName = '';
    this.email = '';
    this.password = '';
    this.id = 0;
  }
}
