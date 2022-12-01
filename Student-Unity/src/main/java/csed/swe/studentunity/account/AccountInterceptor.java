package csed.swe.studentunity.account;


import csed.swe.studentunity.sharedServices.ActiveUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class AccountInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        System.out.println("Here we are in pre checking of accounts the received path is "+ path);
        String sessionId = request.getParameter("sessionId");
        System.out.println("Here we are in pre checking of accounts the received sessionId is "+ sessionId);
        String[] user = ActiveUserService.getInstance().checkLogin(UUID.fromString(sessionId));
        if(user[1] == null) {
            System.out.println("User is not logged in");
            response.setStatus(401);
            return false;
        }
        if(!user[1].equals("admin")) {
            System.out.println("User is not admin");
            response.setStatus(401);
            return false;
        }
        user = new String[]{"ahmed@mail.com", "admin"};
        request.setAttribute("email", user[0]);
        request.setAttribute("role", user[1]);
        return true;
    }
    
}
