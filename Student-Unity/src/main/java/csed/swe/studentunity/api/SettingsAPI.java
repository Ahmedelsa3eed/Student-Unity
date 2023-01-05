package csed.swe.studentunity.api;

import csed.swe.studentunity.logic.SettingsService;
import csed.swe.studentunity.model.settings.SettingsResponses;
import csed.swe.studentunity.model.settings.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/settings")
public class SettingsAPI {
    private final SettingsService settingsService;
    @PutMapping("/changeName")
    public ResponseEntity<SettingsResponses> changeName(@RequestParam String sessionId, @RequestBody ChangeUserNameRequest request){
        SettingsResponses settingsResponse = settingsService.changeName(request, sessionId);
        return new ResponseEntity<>(settingsResponse, httpStatus(settingsResponse));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<SettingsResponses> changePassword(@RequestParam String sessionId, @RequestBody ChangePasswordRequest request){
        SettingsResponses settingsResponse = settingsService.changePassword(request, sessionId);
        return new ResponseEntity<>(settingsResponse, httpStatus(settingsResponse));
    }



    @PutMapping("/changeId")
    public ResponseEntity<SettingsResponses> changeId(@RequestParam String sessionId, @RequestParam String userId){
        SettingsResponses settingsResponse = settingsService.changeId(Integer.valueOf(userId), sessionId);
        return new ResponseEntity<>(settingsResponse, httpStatus(settingsResponse));
    }

    private HttpStatus httpStatus(SettingsResponses settingsResponses) {
        if (settingsResponses == SettingsResponses.SUCCESSFUL_CHANGE_NAME ||
                settingsResponses == SettingsResponses.SUCCESSFUL_CHANGE_PASSWORD ||
                settingsResponses == SettingsResponses.SUCCESSFUL_CHANGE_ID) {
            return HttpStatus.OK;
        } else if (settingsResponses == SettingsResponses.WRONG_PASSWORD ||
                settingsResponses == SettingsResponses.WRONG_ID) {
            return HttpStatus.FORBIDDEN;
        }
        return HttpStatus.BAD_REQUEST;
    }

}
