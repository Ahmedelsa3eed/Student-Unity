import { Component, OnInit, ViewChild } from '@angular/core';
import { NgOtpInputComponent } from 'ng-otp-input';

@Component({
  selector: 'app-verfication',
  templateUrl: './verfication.component.html',
  styleUrls: ['./verfication.component.css']
})



export class VerficationComponent implements OnInit {

  otpLenght: number = 3;

  otpValidity: boolean = false;
  tryAgain: boolean = false;

  otpTime: number = 120*1000;         // Time in milliseconds
  redirectTime: number = 5*1000;      // Time in milliseconds

  email: string = "el.sherif.mohamad@alexu.edu.eg";

  @ViewChild(NgOtpInputComponent, { static: false}) ngOtpInput:NgOtpInputComponent = {} as NgOtpInputComponent;

  constructor() { }



  onOtpChange(otp: string) {
    if (otp.length == this.otpLenght) {
      this.ngOtpInput.otpForm.disable();
      // Compare otp with server
      //
      // If otp is correct
      //     this.otpValidity = true;
      //     this.startTimer("redirect");
      //     redirect to home page
      // Else
          this.tryAgain = true;
          this.ngOtpInput.otpForm.enable();
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
