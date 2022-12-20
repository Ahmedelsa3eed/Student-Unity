import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgOtpInputComponent } from 'ng-otp-input';
import { VerificationRequest } from 'src/app/models/verification-request';
import { VerficationCodeService } from 'src/app/services/verfication-code.service';

@Component({
  selector: 'app-verfication',
  templateUrl: './verfication.component.html',
  styleUrls: ['./verfication.component.css']
})



export class VerficationComponent implements OnInit {

  otpLenght: number = 6;

  otpValidity: boolean = false;
  tryAgain: boolean = false;

  otpContent: string = '';

  otpTime: number = 10*60*1000;         // Time in milliseconds
  redirectTime: number = 5*1000;      // Time in milliseconds
  private verficationData : VerificationRequest = {} as VerificationRequest;
  email: string = "";

  @ViewChild(NgOtpInputComponent, { static: false }) ngOtpInput:NgOtpInputComponent = {} as NgOtpInputComponent;


  constructor(private router : Router, private verficationService : VerficationCodeService) {
    this.email = this.router.getCurrentNavigation()?.extras.queryParams?.['email'];
    console.log(this.router.getCurrentNavigation());
    console.log(this.email);
   }

  onOtpChange(otp: string) {
    this.otpContent = otp;
  }


  verfiy() {
    if (this.otpContent.length == this.otpLenght) {
      this.verficationData.code = this.otpContent;
      this.verficationData.email = this.email;

      this.verficationService.postVerficationCode(this.verficationData).subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/success-sign-up']);
        },
        error: (e) => console.error(e),
        complete: () => console.info('Registeration Done!')
      })
    }
  }



  // Timer for otp or redirect
  startTimer(picked: string): void {
    if (picked == 'otp') {
      let timer = setInterval(() => {
        this.otpTime = this.otpTime - 1000;
        if (this.otpTime == 0) {
          clearInterval(timer);
          this.ngOtpInput.otpForm.disable();
          // redirect to home page
        }
      }, 1000);
    } else if (picked == 'redirect') {
      let timer = setInterval(() => {
        this.redirectTime = this.redirectTime - 1000;
        if (this.redirectTime == 0) {
          clearInterval(timer);
          // redirect to home page
        }
      }, 1000);
    }

  }

  ngOnInit(): void {
    this.startTimer("otp");
  }
}
