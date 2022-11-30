package csed.swe.studentunity.login;

import csed.swe.studentunity.sharedServices.ActiveUserService;
import csed.swe.studentunity.user.User;
import csed.swe.studentunity.user.UserService;
import csed.swe.studentunity.sharedServices.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class LoginService {
    @Autowired
    private final UserService queries;

    @Autowired
    private final EmailService senderService;

    @Autowired
    public LoginService(UserService queries, EmailService senderService) {
        this.queries = queries;
        this.senderService = senderService;
    }

    public String checkCredentials(String email, String password) {
        try {
            User user = queries.getUser(email).orElseThrow(() -> new RuntimeException());
            if (user.getPassword().equals(password)) {
                ActiveUserService activeUserService = ActiveUserService.getInstance();
                UUID sessionId = activeUserService.login(email, user.getRole());
                return sessionId.toString();
            } else {
                return "Wrong password";
            }
        }
        catch (RuntimeException e){
            return "Email not found";
        }
    }

    public String forgetPassword(String email){
        try {
            User user = queries.getUser(email).orElseThrow(() -> new RuntimeException());
            senderService.send(user.getPassword(), "Your Password", email);
            return "Please check your mailbox";
        }
        catch (RuntimeException e){
            return "Email not found";
        }
    }

    public void addCookie(String email){

    }

}
