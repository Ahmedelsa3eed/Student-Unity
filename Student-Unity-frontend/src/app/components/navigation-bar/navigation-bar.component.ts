import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignInOutService } from '../../services/sign-in-out.service';
import { User } from '../../models/User';
import { NotificationComponent } from '../notification/notification.component';
import { NotificationService } from 'src/app/services/notification.service';
@Component({
    selector: 'app-navigation-bar',
    templateUrl: './navigation-bar.component.html',
    styleUrls: ['./navigation-bar.component.css'],
})
export class NavigationBarComponent implements OnInit {
    loggedInUser = new User();

    constructor(private router: Router, private signInOutService: SignInOutService, private notificationService:NotificationService) {}
    public isLoading: boolean = false;
    ngOnInit(): void {
        this.getSignedInUser();
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
        this.signInOutService.signOut();
        this.router.navigateByUrl('sign-in');
    }

    getSignedInUser() {
        this.isLoading = true;
        this.signInOutService.getSignedInUser().subscribe(
            (res) => {
                this.isLoading = false;
                if (res.body) {
                    this.loggedInUser = res.body;
                }
            },
            (err) => {
                this.isLoading = false;
                console.log(err);
            }
        );
    }
}
