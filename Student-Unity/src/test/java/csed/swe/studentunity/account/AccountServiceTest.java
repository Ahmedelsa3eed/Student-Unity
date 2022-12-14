package csed.swe.studentunity.account;

import csed.swe.studentunity.Logic.AccountsPage.AccountService;
import csed.swe.studentunity.Logic.ActiveUserService;
import csed.swe.studentunity.model.User;
import csed.swe.studentunity.Logic.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        userService.addUser(user);
        assertNotNull(accountService.getAllAccounts());
        accountService.deleteAccount("user1@mail.com");
    }

    @Test
    void searchAccountsByFirstname() {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        userService.addUser(user);
        assertNotNull(accountService.searchAccounts("firstname"));
        accountService.deleteAccount("user1@mail.com");
    }

    @Test
    void searchAccountsByLastname() {
        userService.addUser(user);
        assertNotNull(accountService.searchAccounts( "lastname"));
        accountService.deleteAccount("user1@mail.com");
        userService.addUser(user);
        assertNotNull(accountService.searchAccounts( "1111"));
        assertNotNull(accountService.searchAccounts("user1"));
        accountService.deleteAccount("user1@mail.com");
    }

    @Test
    void changeRole() {
        userService.addUser(user);
        userService.addUser(targetUser);

        accountService.changeRole( "user1@mail.com", "admin");
        assertTrue(accountService.changeRole("targetUser@mail.com", "admin"));
        accountService.deleteAccount("targetUser@mail.com");
        accountService.deleteAccount("user1@mail.com");

    }


    @Test
    void deleteAccount() {
        userService.addUser(user);
        assertTrue(accountService.deleteAccount("user1@mail.com"));

    }


}