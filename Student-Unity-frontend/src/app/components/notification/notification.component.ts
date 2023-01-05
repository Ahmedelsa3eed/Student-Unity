import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { getMessaging, getToken, onMessage } from 'firebase/messaging';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
    selector: 'app-notification',
    templateUrl: './notification.component.html',
    styleUrls: ['./notification.component.css'],
})
export class NotificationComponent implements OnInit {
    message: any = null;
    constructor(private notificationService: NotificationService) {}

    ngOnInit(): void {
        this.requestPermission();
        this.listen();
    }

    public requestPermission() {
        const messaging = getMessaging();
        getToken(messaging, { vapidKey: environment.firebase.vapidKey })
            .then((currentToken) => {
                if (currentToken) {
                    this.notificationService.putToken(currentToken).subscribe();
                } else {
                    console.log('No registration token available. Request permission to generate one.');
                }
            })
            .catch((err) => {
                console.log('An error occurred while retrieving token. ', err);
            });
    }

    public listen() {
        const messaging = getMessaging();
        onMessage(messaging, (payload) => {
            console.log('Message received. ', payload);
            this.message = payload;
        });
    }
}
