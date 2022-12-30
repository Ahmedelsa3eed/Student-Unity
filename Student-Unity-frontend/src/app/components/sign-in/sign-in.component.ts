import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { ChildActivationStart, Router } from '@angular/router';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { NotificationComponent } from '../notification/notification.component';
import { NotificationService } from 'src/app/services/notification.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
    selector: 'app-sign-in',
    templateUrl: './sign-in.component.html',
    styleUrls: ['./sign-in.component.css'],
})
export class SignInComponent implements OnInit {
    signInResponse: string = '';
    forgotPasswordResponse: string = '';
    loadings: boolean = false;
    forgetPasswordLoading: boolean = false;

    constructor(
        private signInOutService: SignInOutService,
        private router: Router,
        private notificationService: NotificationService,
        private cookieService: CookieService
    ) {}

    ngOnInit(): void {
        if (this.signInOutService.isSignedIn()) {
            this.router.navigate(['home']);
        }
    }

    public onSignIn(signInForm: NgForm): void {
        this.loadings = true;
        this.signInOutService.signIn(signInForm.value.email, signInForm.value.password).subscribe({
            next: (res) => this.handleSignInResponse(res, signInForm),
            error: (e) => this.handleResponseError(e),
        });
    }

    public onForgotPassword(email: string) {
        this.forgetPasswordLoading = true;
        this.signInOutService.forgotPassword(email).subscribe(
            (response) => {
                this.forgetPasswordLoading = false;
                if (response.status == 200) {
                    this.forgotPasswordResponse = 'Email sent successfully';
                }
            },
            (err) => {
                this.forgetPasswordLoading = false;
                if (err.status == 404) {
                    this.forgotPasswordResponse = 'Email not found';
                } else {
                    this.forgotPasswordResponse = 'Something went wrong the server may be down';
                }
            }
        );
    }

    public handleSignInResponse(response: HttpResponse<any>, signInForm: NgForm) {
        if (response.status == 200) {
            let expirationDate = 0;
            if (signInForm.value.rememberMe) expirationDate = 400;
            this.cookieService.set('sessionId', response.body, expirationDate, '/', '', true, 'Strict');
            this.signInOutService.fillSignedInUserInfo(response.body).subscribe(
                (res) => {
                    if (res.body) {
                        this.cookieService.set(
                            'firstName',
                            res.body.firstName,
                            expirationDate,
                            '/',
                            '',
                            true,
                            'Strict'
                        );
                        this.cookieService.set('lastName', res.body.lastName, expirationDate, '/', '', true, 'Strict');
                        this.cookieService.set('email', res.body.email, expirationDate, '/', '', true, 'Strict');
                        this.cookieService.set('role', res.body.role, expirationDate, '/', '', true, 'Strict');
                        this.loadings = false;
                        this.router.navigate(['home']);
                    }
                },
                (err) => {
                    this.loadings = false;
                    console.log(err);
                }
            );
        }
    }

    private handleResponseError(err: any) {
        this.loadings = false;
        if (err.status == 403) {
            this.signInResponse = 'Wrong password';
        } else if (err.status == 404) {
            this.signInResponse = 'Email Not Found';
        } else {
            this.signInResponse = 'Something went wrong the server may be down';
        }
        document.getElementById('openSignInErrorBtn')?.click();

        console.error(err);
    }
}
