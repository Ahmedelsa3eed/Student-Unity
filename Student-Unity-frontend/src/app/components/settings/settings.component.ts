import {Component, Input, OnInit} from '@angular/core';
import {UserName} from "../../models/settings/UserName";
import {UserPassword} from "../../models/settings/UserPassword";
import {UserId} from "../../models/settings/UserId";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SettingsService} from "../../services/settings.service";
import {ConfirmedValidator} from "../shared/match.validator";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  @Input() name = new UserName();
  @Input() password = new UserPassword();
  @Input() id = new UserId();

  nameForm! : FormGroup;
  passwordForm! : FormGroup;
  idForm! : FormGroup;


  constructor(private fb: FormBuilder, private settingsService: SettingsService) { }

  ngOnInit(): void {
    this.nameForm = this.fb.group({
      firstName: this.fb.control(null, [
        Validators.required,
        Validators.pattern('[a-zA-Z]*')])
      , lastName: this.fb.control(null, [
        Validators.required,
        Validators.pattern('[a-zA-Z]*')])

    });

    this.passwordForm = this.fb.group({
      password: this.fb.control(null, [
        Validators.required,
        Validators.maxLength(16),
        Validators.minLength(8)
      ]),
      confirmPassword: this.fb.control(null, [
        Validators.required,
        Validators.maxLength(16),
        Validators.minLength(8),
        ConfirmedValidator('password', 'confirmPassword')
      ])

    });

    this.idForm = this.fb.group({
      studentId: this.fb.control(null, [
        Validators.required,
        Validators.maxLength(8),
        Validators.minLength(8)
    ])
    });


  }

  changeUserName() {
    let userName = new UserName();
    console.log(this.nameForm.get('firstName')?.value);
    userName.firstName = this.nameForm.get('firstName')?.value;
    userName.lastName = this.nameForm.get('lastName')?.value;
    console.log(userName);

    this.settingsService.changeName(userName).subscribe(
      (response) => {
        console.log(response);
        if(response.status == 200) {
          // print success message
        } else {
          // print response message

        }
      }
    );

  }

  changeUserId() {
    let userId = new UserId();
    userId.studentId = this.idForm.get('studentId')?.value;

    this.settingsService.changeId(userId).subscribe(
      (response) => {
        console.log(response);
        if(response.status == 200) {
          // print success message
        } else {
          // print response message

        }
      }
    );

  }

  changeUserPassword() {
    let userPassword = new UserPassword();
    userPassword.currentPassword = this.passwordForm.get('current-password')?.value;
    userPassword.newPassword = this.passwordForm.get('new-password')?.value;
    userPassword.confirmPassword = this.passwordForm.get('confirm-password')?.value;


    this.settingsService.changePassword(userPassword).subscribe(
      (response) => {
        console.log(response);
        if(response.status == 200) {
          // print success message
        } else {
          // print response message

        }
      }
    );

  }
}
