import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignInOutService } from '../../services/sign-in-out.service';
import { User } from '../../models/User';
import { NotificationComponent } from '../notification/notification.component';
import { NotificationService } from 'src/app/services/notification.service';
import { CookieService } from 'ngx-cookie-service';
@Component({
    selector: 'app-navigation-bar',
    templateUrl: './navigation-bar.component.html',
    styleUrls: ['./navigation-bar.component.css'],
})
export class NavigationBarComponent implements OnInit {
    loggedInUserRole = this.signInOutService.getSignedInUserRole();
    loggedInUserFirstName = this.signInOutService.getSignedInUserFirstName();
    loggedInUserLastName = this.signInOutService.getSignedInUserLastName();
    constructor(
        private router: Router,
        private signInOutService: SignInOutService,
        private notificationService: NotificationService
    ) {}
    public isLoading: boolean = false;
    ngOnInit(): void {
        let askPermission = new NotificationComponent(this.notificationService);
        askPermission.requestPermission();

        setInterval(() => {
            askPermission.listen();
        }, 25);
    }

    navigateTo(child: string) {
        this.router.navigateByUrl('home/' + child);
    }

    logout() {
        this.signInOutService.signOut().subscribe(
            (res) => {
                this.router.navigateByUrl('sign-in');
                this.signInOutService.deleteCookies();
            },
            (err) => {
                console.error(err);
            },
            () => {}
        );
    }
}
