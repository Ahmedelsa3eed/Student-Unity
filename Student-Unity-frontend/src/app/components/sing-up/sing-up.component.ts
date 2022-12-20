import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';
import { SignUpService } from 'src/app/services/sign-up.service';
import { ConfirmedValidator } from 'src/app/components/shared/match.validator';
import { SignUpData } from 'src/app/models/sign-up-data.model';
@Component({
  templateUrl: './sing-up.component.html',
  styleUrls: ['./sing-up.component.css']
})
export class SingUpComponent implements OnInit, OnDestroy {

  registerForm!: FormGroup;
  postError: boolean = false;
  postErrorMessage: string = "";
  signUpdata: SignUpData  = {} as SignUpData;
  constructor(private fb: FormBuilder, private signUpService: SignUpService, private router: Router) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group ({
      firstName: this.fb.control(null, [Validators.required, Validators.maxLength(16), Validators.pattern("[a-zA-Z]*")]),
      lastName: this.fb.control(null, [Validators.required, Validators.maxLength(16), Validators.pattern("[a-zA-Z]*")]),
      email: this.fb.control(null, [Validators.required, Validators.pattern("^[A-Za-z0-9._%+-]+@alexu\.edu\.eg")]),
      password: this.fb.control(null, [Validators.required, Validators.maxLength(16), Validators.minLength(8)]),
      rPassword: this.fb.control(null, [Validators.required, ConfirmedValidator('password', 'rPassword')]),
      studentId: this.fb.control(null, [Validators.required])
    }, {
      validators: ConfirmedValidator('password', 'rPassword')
    });

  }

  // on destroy of component
  ngOnDestroy() {
    this.registerForm.reset();
  }

  // Method to register a new user
  registerSubmitted() {
    this.signUpdata.firstName = this.registerForm.get('firstName')?.value;
    this.signUpdata.lastName = this.registerForm.get('lastName')?.value;
    this.signUpdata.email = this.registerForm.get('email')?.value;
    this.signUpdata.password = this.registerForm.get('password')?.value;
    this.signUpdata.studentId = this.registerForm.get('studentId')?.value;
    this.router.navigate(['verfication'], {
      queryParams: { email: this.signUpdata.email }
    });
    this.signUpService.postSignUpData(this.signUpdata)
    .subscribe({
      next: (res) => {
        // If user already exists or registred successfully, he will be directed to signIn
        console.log(res);

        this.router.navigate(['verfication'], {
          queryParams: { email: this.signUpdata.email }
        });
      },
      error: (e) => this.httpError(e),
      complete: () => console.info('Registeration Done!')
    })
  }

  // method to print the error message from the backend
  httpError(httpError: any) {
    console.error(httpError);
    this.postError = true;
    this.postErrorMessage = httpError.error;
    console.log(this.postErrorMessage);
  }

  // getters for the form controls for every field to get the error messages
  get FirstName(): FormControl{
    return this.registerForm.get('firstName') as FormControl;
  }
  get LastName(): FormControl{
    return this.registerForm.get('lastName') as FormControl;
  }
  get Email(): FormControl{
    return this.registerForm.get('email') as FormControl;
  }
  get Password(): FormControl{
    return this.registerForm.get('password') as FormControl;
  }
  get RPassword(): FormControl{
    return this.registerForm.get('rPassword') as FormControl;
  }
  get StudentId(): FormControl{
    return this.registerForm.get('studentId') as FormControl;
  }
}
