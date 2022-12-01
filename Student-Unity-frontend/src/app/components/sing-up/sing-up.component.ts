import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sing-up',
  templateUrl: './sing-up.component.html',
  styleUrls: ['./sing-up.component.css']
})
export class SingUpComponent implements OnInit {
  constructor() { }

  ngOnInit(): void {
  }
  // Method to register a new user
  registerSubmitted() {
    console.log(this.registerForm.value);
  }
  registerForm = new FormGroup({
    firstName: new FormControl("", [Validators.required]),
    lastName: new FormControl(""),
    email: new FormControl(""),
    password: new FormControl(""),
    rPassword: new FormControl(""),
    studentId: new FormControl("")

  });
  get firstName(): FormControl{
    return this.registerForm.get('firstName') as FormControl;
  }
  get lastName(): FormControl{
    return this.registerForm.get('lastName') as FormControl;
  }
  get email(): FormControl{
    return this.registerForm.get('email') as FormControl;
  }
  get password(): FormControl{
    return this.registerForm.get('password') as FormControl;
  }
  get rPassword(): FormControl{
    return this.registerForm.get('rPassword') as FormControl;
  }
  get studentId(): FormControl{
    return this.registerForm.get('studentId') as FormControl;
  }
  

}
