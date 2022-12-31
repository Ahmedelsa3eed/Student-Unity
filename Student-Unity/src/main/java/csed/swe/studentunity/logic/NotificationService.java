package csed.swe.studentunity.logic;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.TopicManagementResponse;
import csed.swe.studentunity.dao.NotificationRepo;
import csed.swe.studentunity.logic.user.ActiveUserService;
import csed.swe.studentunity.logic.user.UserService;
import csed.swe.studentunity.model.Notification;
import csed.swe.studentunity.model.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class NotificationService {

    private final FirebaseMessaging fcm;
    private final NotificationRepo notificationRepo;

    private final UserService userService;

    public NotificationService(FirebaseMessaging fcm, NotificationRepo notificationRepo, UserService userService) {
        this.fcm = fcm;
        this.notificationRepo = notificationRepo;
        this.userService = userService;
    }



    public String saveToken(String token, String sessionId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String email = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(email).orElse(null);
        if (user == null){
            return "User isn't active";
        }
        Notification notification = notificationRepo.findById(user.getId()).orElse(null);
        if (notification != null){
            notification.setToken(token);
            return "user already have a token";
        }
        Notification notification1 = new Notification();
        notification1.setUser(user);
        notification1.setToken(token);
        notificationRepo.save(notification1);
        return "ok";
    }

    public String subscribeToTopic(String sessionId, String topic) throws FirebaseMessagingException {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String email = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(email).orElse(null);
        if (user == null){
            return "User isn't active";
        }
        Notification notification = notificationRepo.findById(user.getId()).orElse(null);
        if (notification == null){
            return "User didn't accept notification";
        }
        List<String> myTokens = new ArrayList<>();
        myTokens.add(notification.getToken());
        TopicManagementResponse topicManagementResponse = FirebaseMessaging.getInstance().subscribeToTopic(myTokens, topic);
        System.out.println(topicManagementResponse.getSuccessCount());
        return "ok";
    }

    public String unSubscribe(String sessionId, String topic) throws FirebaseMessagingException {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        String email = activeUserService.checkLogin(UUID.fromString(sessionId))[0];
        User user = userService.getUser(email).orElse(null);
        if (user == null){
            return "User isn't active";
        }
        Notification notification = notificationRepo.findById(user.getId()).orElse(null);
        if (notification == null){
            return "User didn't accept notification";
        }
        List<String> myTokens = new ArrayList<>();
        myTokens.add(notification.getToken());
        TopicManagementResponse topicManagementResponse = FirebaseMessaging.getInstance().unsubscribeFromTopic(myTokens, topic);
        System.out.println(topicManagementResponse.getSuccessCount());
        return "ok";
    }

    public String sendMessageToTopic(String topic, String body, String title) throws FirebaseMessagingException {
        com.google.firebase.messaging.Notification notification = com.google.firebase.messaging.Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
        System.out.println("here");
        Message message = Message.builder()
                .setNotification(notification)
                .setTopic(topic)
                .build();
        fcm.send(message);
        return "ok";
    }

}
