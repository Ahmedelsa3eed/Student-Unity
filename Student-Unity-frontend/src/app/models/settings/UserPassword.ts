export class UserPassword {
  currentPassword: string;
  newPassword: string;
  confirmPassword: string;

  constructor() {
    this.currentPassword = '';
    this.newPassword = '';
    this.confirmPassword = '';
  }
}
