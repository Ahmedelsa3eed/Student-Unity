import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { ChildActivationStart, Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css'],
})
export class SignInComponent implements OnInit {

  signInResponse: string = "";
  forgotPasswordResponse: string = "";

  constructor(private signInOutService: SignInOutService, private router: Router) { }

  ngOnInit(): void {
    if(this.signInOutService.isSignedIn()){
      this.router.navigate(["home"]);
    }
  }

  public onSignIn(signInForm: NgForm): void {
    this.signInOutService.signIn(signInForm.value.email, signInForm.value.password).subscribe(
      (response: string) => {
        if(response == "forgetPassword" || response == "Email not found"){
          this.signInResponse = response;
          document.getElementById('openSignInErrorBtn')?.click();
        }else {
          this.signInOutService.fillSignedInUserInfo(signInForm.value.rememberMe, response);
          this.router.navigate(['home']);
        }
      }
    );
  }

  public onForgotPassword(email: string){
    this.signInOutService.forgotPassword(email).subscribe(
      (response) => {
        this.forgotPasswordResponse = response;
      }
    );
  }

}
