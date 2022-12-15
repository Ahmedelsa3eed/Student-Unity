package csed.swe.studentunity.Logic;

import csed.swe.studentunity.model.LoginResponses;
import csed.swe.studentunity.model.User;
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
    public LoginResponses checkCredentials(String email, String password) {
        try {
            User user = queries.getUser(email).orElseThrow(RuntimeException::new);
            if (user.getPassword().equals(password)) {
                ActiveUserService activeUserService = ActiveUserService.getInstance();
                activeUserService.login(email, user.getRole());
                return LoginResponses.SUCCESSFUL_LOGIN;
            } else {
                return LoginResponses.WRONG_PASSWORD;
            }
        }
        catch (RuntimeException e){
            return LoginResponses.EMAIL_NOT_FOUND;
        }
    }

    public boolean forgetPassword(String email){
        try {
            User user = queries.getUser(email).orElseThrow(RuntimeException::new);
            senderService.send(user.getPassword(), "Your Password", email);
            return true;
        }
        catch (RuntimeException e){
            return false;
        }
    }

    public  User getUser(String sessionID){
        try {
            String email = ActiveUserService.getInstance().getEmailFromSessionId(UUID.fromString(sessionID));
            return queries.getUser(email).orElseThrow(RuntimeException::new);

        }
        catch (RuntimeException e){
            return null;
        }
    }
    public void addCookie(){
        // milestone 2
    }

}
