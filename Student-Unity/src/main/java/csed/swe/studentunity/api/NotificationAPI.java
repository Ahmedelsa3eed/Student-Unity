package csed.swe.studentunity.api;

import com.google.firebase.messaging.FirebaseMessagingException;
import csed.swe.studentunity.logic.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Notification")
public class NotificationAPI {
    private final NotificationService notificationService;

    public NotificationAPI(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/addToken")
    public ResponseEntity<String> addToken(@RequestParam String sessionId, @RequestParam String token){
        System.out.println(sessionId + " " +token);
        String re = notificationService.saveToken(token, sessionId);
        System.out.println(re);
        if (re.equals("ok")){
            return new ResponseEntity<>(re, HttpStatus.OK);
        }
        else if (re.equals("user already have a token")){
            return new ResponseEntity<>(re, HttpStatus.FORBIDDEN);
        }
        else{
            return new ResponseEntity<>(re, HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @PutMapping("/subscribeToTopic")
    public ResponseEntity<String> subscribeToTopic(@RequestParam String sessionId, @RequestParam String topic) {
        try{
            String res = notificationService.subscribeToTopic(sessionId, topic);
            if (res.equals("ok")){
                return new ResponseEntity<>("ok", HttpStatus.OK);
            }
            if (res.equals("User didn't accept notification")){
                return new ResponseEntity<>("User didn't accept notification", HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>("User is not active", HttpStatus.FAILED_DEPENDENCY);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error subscribing", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/sendMessageToTopic")
    public ResponseEntity<String> sendMessageToTopic(@RequestParam String topic, @RequestParam String body, @RequestParam String title) throws FirebaseMessagingException {
        try {
            return new ResponseEntity<>(notificationService.sendMessageToTopic(topic, body, title), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error", HttpStatus.FORBIDDEN);
        }
    }

}
