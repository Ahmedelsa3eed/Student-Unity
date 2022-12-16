package csed.swe.studentunity.Logic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class AdminCheckInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(AdminCheckInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String sessionId = request.getParameter("sessionId");
        String[] user = ActiveUserService.getInstance().checkLogin(UUID.fromString(sessionId));
        if(user[1] == null) {
            logger.info("User is not logged in");
            response.setStatus(401);
            return false;
        }
        if(!user[1].equals("admin")) {
            logger.info("User is not admin");
            response.setStatus(401);
            return false;
        }

        return true;
    }
    
}
