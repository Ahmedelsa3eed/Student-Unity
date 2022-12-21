import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
    constructor(private signInOutService: SignInOutService, private router: Router) {}

    ngOnInit(): void {
        if (!this.signInOutService.isSignedIn()) {
            this.router.navigate(['sign-in']);
        }
    }
}
