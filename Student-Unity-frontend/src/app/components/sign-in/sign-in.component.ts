import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  constructor(private signInOutService: SignInOutService, private router: Router) { }

  ngOnInit(): void {
  }

  signInResponse: string = "";

  public onSignIn(signInForm: NgForm): void {
    this.signInOutService.signIn(signInForm.value.email, signInForm.value.password).subscribe(
      (response: string) => {
        if(response == "OK"){
          this.router.navigate(['home']);
        }else {
          this.signInResponse = response;
          document.getElementById('openSignInErrorBtn')?.click();
        }
      }
    );
  }

}
