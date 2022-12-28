package csed.swe.studentunity.logic;

import csed.swe.studentunity.logic.user.ActiveUserService;
import csed.swe.studentunity.logic.user.UserService;
import csed.swe.studentunity.model.settings.SettingsResponses;
import csed.swe.studentunity.model.User;
import csed.swe.studentunity.model.settings.ChangePasswordRequest;
import csed.swe.studentunity.model.settings.ChangeUserNameRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SettingsService {

    private final UserService userService;
    private final ActiveUserService activeUserService = ActiveUserService.getInstance();

    public SettingsResponses changeName(ChangeUserNameRequest request, String sessionId){
        Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        if (userId != null) {
            userService.updateUserFirstName(request.getFirstName(), userId);
            userService.updateUserLastName(request.getLastName(), userId);
            return SettingsResponses.SUCCESSFUL_CHANGE_NAME;
        } else {
            return SettingsResponses.NOT_FOUND;
        }
    }

    public SettingsResponses changePassword(ChangePasswordRequest request, String sessionId){
        Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        if (userId != null) {
            Optional<User> user = userService.getUser(userId);
            if (user.isPresent()) {
                if (user.get().getPassword().equals(request.getCurrentPassword())) {
                    userService.updateUserPassword(request.getNewPassword(), userId);
                    return SettingsResponses.SUCCESSFUL_CHANGE_PASSWORD;
                } else {
                    return SettingsResponses.WRONG_PASSWORD;
                }
            }
        }
        return SettingsResponses.NOT_FOUND;

    }

    public SettingsResponses changeId(Integer studentId, String sessionId){
        Long userId = activeUserService.getUserIdFromSessionId(UUID.fromString(sessionId));
        if (userId != null) {
            Optional<User> user = userService.getUser(userId);
            if(userService.getUser(studentId).isPresent()){
                return SettingsResponses.WRONG_ID;
            }

            if (user.isPresent()) {
                userService.updateUserStudentId(studentId, userId);
                return SettingsResponses.SUCCESSFUL_CHANGE_ID;
            }
        }
        return SettingsResponses.NOT_FOUND;
    }

}
