package csed.swe.studentunity.logout;

import csed.swe.studentunity.sharedServices.ActiveUserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@Transactional
public class LogoutService {

    public void logout(UUID sessionId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.logout(sessionId);
    }
}
