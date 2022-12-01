package csed.swe.studentunity.account;

import csed.swe.studentunity.sharedServices.ActiveUserService;
import csed.swe.studentunity.user.User;
import csed.swe.studentunity.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    private User user = new User("user1@mail.com", 1111,
            "firstname", "lastname",
            "password", "admin", "token");
    private User targetUser = new User("targetUser@mail.com", 7777,
            "firstname", "lastname",
            "password", "user", "token");


    @Test
    void getAllAccountsByAnAdmin() {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        UUID sessionId = activeUserService.login("ahmed", "admin");
        userService.addUser(user);
        assertNotNull(accountService.getAllAccounts(sessionId));
        accountService.deleteAccount(sessionId,1111);
    }

    @Test
    void getAllAccountsByAUser() {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        UUID sessionId = activeUserService.login("ahmed", "user");
        assertNull(accountService.getAllAccounts(sessionId));
    }

    @Test
    void searchAccountsByFirstname() {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        UUID sessionId = activeUserService.login("ahmed", "admin");
        userService.addUser(user);
        assertNotNull(accountService.searchAccounts(sessionId, "firstname"));
        accountService.deleteAccount(sessionId,1111);
    }

    @Test
    void searchAccountsByLastname() {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        UUID sessionId = activeUserService.login("ahmed", "admin");
        userService.addUser(user);
        assertNotNull(accountService.searchAccounts(activeUserService.login("ahmed", "admin"), "lastname"));
        accountService.deleteAccount(sessionId,1111);
    }
    @Test
    void searchAccountsByStudentId() {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        UUID sessionId = activeUserService.login("ahmed", "admin");
        userService.addUser(user);
        assertNotNull(accountService.searchAccounts(sessionId, "1111"));
        accountService.deleteAccount(sessionId, 1111);
    }

    @Test
    void searchAccountsByEmail() {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        UUID sessionId = activeUserService.login("ahmed", "admin");
        userService.addUser(user);
        assertNotNull(accountService.searchAccounts(sessionId, "user1"));
        accountService.deleteAccount(sessionId, 1111);
    }

    @Test
    void searchAccountsNonAuthorized() {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        UUID sessionId = activeUserService.login("ahmed", "user");
        userService.addUser(user);
        assertNull(accountService.searchAccounts(sessionId, "user1"));
        accountService.deleteAccount(activeUserService.login("ahmed", "admin"), 1111);
    }


    @Test
    void changeRole() {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        UUID sessionId = activeUserService.login("ahmed", "admin");
        userService.addUser(user);
        userService.addUser(targetUser);

        accountService.changeRole(sessionId, 7777, "admin");
        assertTrue(accountService.changeRole(sessionId, 7777, "admin"));
        accountService.deleteAccount(sessionId, 1111);
        accountService.deleteAccount(sessionId, 7777);

    }

    @Test
    void changeRoleNonAuthorized() {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        UUID sessionId = activeUserService.login("ahmed", "user");
        userService.addUser(user);
        userService.addUser(targetUser);

        assertFalse(accountService.changeRole(sessionId, 7777, "admin"));
        accountService.deleteAccount(activeUserService.login("ahmed", "admin"), 1111);
        accountService.deleteAccount(activeUserService.login("ahmed", "admin"), 7777);
    }


    @Test
    void deleteAccount() {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        UUID sessionId = activeUserService.login("ahmed", "admin");
        userService.addUser(user);
        assertTrue(accountService.deleteAccount(sessionId, 1111));

    }

    @Test
    void deleteAccountNonAuthorized(){
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        UUID sessionId = activeUserService.login("ahmed", "user");
        userService.addUser(user);
        assertFalse(accountService.deleteAccount(sessionId, 1111));
        accountService.deleteAccount(activeUserService.login("ahmed", "admin"), 1111);
    }

}