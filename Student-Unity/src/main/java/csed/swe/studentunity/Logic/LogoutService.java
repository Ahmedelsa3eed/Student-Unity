package csed.swe.studentunity.Logic;

import csed.swe.studentunity.Logic.ActiveUserService;
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
