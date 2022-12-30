import { Component, Input, OnInit } from '@angular/core';
import { UserName } from '../../models/settings/UserName';
import { UserPassword } from '../../models/settings/UserPassword';
import { UserId } from '../../models/settings/UserId';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SettingsService } from '../../services/settings.service';
import { ConfirmedValidator } from '../shared/match.validator';
import {CookieService} from "ngx-cookie-service";

@Component({
    selector: 'app-settings',
    templateUrl: './settings.component.html',
    styleUrls: ['./settings.component.css'],
})
export class SettingsComponent implements OnInit {

    nameForm!: FormGroup;
    passwordForm!: FormGroup;
    idForm!: FormGroup;



    constructor(private fb: FormBuilder, private settingsService: SettingsService, private cookieService: CookieService) {}

    ngOnInit(): void {
        this.nameForm = this.fb.group({
            firstName: this.fb.control(null, [Validators.required, Validators.pattern('[a-zA-Z]*')]),
            lastName: this.fb.control(null, [Validators.required, Validators.pattern('[a-zA-Z]*')]),
        });

        this.passwordForm = this.fb.group({
            password: this.fb.control(null, [Validators.required, Validators.maxLength(16), Validators.minLength(8)]),
            confirmPassword: this.fb.control(null, [
                Validators.required,
                Validators.minLength(8),
                ConfirmedValidator('password', 'confirmPassword'),
            ]),
        });

        this.idForm = this.fb.group({
            studentId: this.fb.control(null, [Validators.required, Validators.maxLength(8), Validators.minLength(8)]),
        });
    }

    changeUserName() {
        let userName = new UserName();
        // @ts-ignore
        userName.firstName = document.getElementById('firstName')?.value;
        // @ts-ignore
        userName.lastName = document.getElementById('lastName')?.value;
        console.log(userName);

        console.log(document.getElementById('firstName')?.getAttribute('value'));

        this.settingsService.changeName(userName).subscribe((response) => {
            if (response == "SUCCESSFUL_CHANGE_NAME") {
                // print success message
              let expirationDate = "0";
              expirationDate = this.cookieService.get("expires");

              this.cookieService.set(
                'firstName',
                userName.firstName,
                new Date(expirationDate),
                '/',
                '',
                true,
                'Strict'
              );
              this.cookieService.set('lastName', userName.lastName, new Date(expirationDate), '/', '', true, 'Strict');
              alert('Name changed successfully');
              window.location.reload();
            } else {
                // print response message
              alert('Name change failed');
            }
        });
    }

    changeUserId() {
        let userId = new UserId();
        // @ts-ignore
        userId.studentId = document.getElementById('studentId')?.value;
        console.log(userId);

        this.settingsService.changeId(userId).subscribe((response) => {
            if (response == "SUCCESSFUL_CHANGE_ID") {
                // print success message
                alert('ID changed successfully');

            } else {
                // print response message
                alert('ID change failed');
            }
        });
    }

    changeUserPassword() {
        let userPassword = new UserPassword();
      // @ts-ignore
      userPassword.currentPassword = document.getElementById('currentPassword')?.value;
        // @ts-ignore
      userPassword.newPassword = document.getElementById('newPassword')?.value;
        // @ts-ignore
      userPassword.confirmPassword = document.getElementById('confirmNewPassword')?.value;
      console.log(userPassword);
      if(userPassword.currentPassword != null && userPassword.newPassword != null && userPassword.confirmPassword != null
      && userPassword.confirmPassword == userPassword.newPassword){
        this.settingsService.changePassword(userPassword).subscribe((response) => {
          console.log(response);
          if (response == "SUCCESSFUL_CHANGE_PASSWORD") {
            // print success message
            alert('Password changed successfully');
          } else {
            // print response message
            alert('Password change failed');
          }
        });
      } else {
        alert('new password and confirm password do not match');
      }
    }
}
