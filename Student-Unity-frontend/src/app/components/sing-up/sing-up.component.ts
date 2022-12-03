import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';
import { SignUpService } from 'src/app/services/sign-up.service';

@Component({
  templateUrl: './sing-up.component.html',
  styleUrls: ['./sing-up.component.css']
})
export class SingUpComponent implements OnInit {
  postError: boolean = false;
  postErrorMessage: string = "";

  registerForm! : FormGroup;

  constructor(private fb: FormBuilder, private signUpService: SignUpService, private router: Router) { }


  ngOnInit(): void {
    this.registerForm = this.fb.group ({
      firstName: this.fb.control(null, [Validators.required, Validators.maxLength(16), Validators.pattern("[a-zA-Z]*")]),
      lastName: this.fb.control(null, [Validators.required, Validators.maxLength(16), Validators.pattern("[a-zA-Z]*")]),
      email: this.fb.control(null, [Validators.required, Validators.pattern("^[A-Za-z0-9._%+-]+@alexu\.edu\.eg")]),
      password: this.fb.control(null, [Validators.required, Validators.maxLength(16), Validators.minLength(8)]),
      rPassword: this.fb.control(null, [Validators.required, this.ConfirmedValidator('password', 'rPassword')]),
      studentId: this.fb.control(null, [Validators.required])
    }, {
      validators: this.ConfirmedValidator('password', 'rPassword')
    });
  }

  // Method to register a new user
  registerSubmitted() {
    this.signUpService.postSignUpData(this.registerForm.value).subscribe(
      Response => console.log(Response),
      error => this.httpError(error)
    );
  }

  // method to print the error message from the backend
  httpError(httpError: any) {
    console.log('error: ', httpError);
    this.postError = true;
    this.postErrorMessage = httpError.error.errorMessage;
  }

  // creacte a custom validtor to check if the password and the re-entered password are the same
  private ConfirmedValidator(controlName: string, matchingControlName: string) : ValidatorFn {
    return (control: AbstractControl) : ValidationErrors | null => {
      const FormGroup = control as FormGroup;
      const controlValue  = FormGroup.get(controlName)?.value;
      const matchingControlValue  = FormGroup.get(matchingControlName)?.value;

      if (control.get('password')?.value === control.get('rPassword')?.value) {
        return null;
      } else {
        return { ValudesNotMatch: true };
      }
    }
  }


  // getters for the form controls
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
