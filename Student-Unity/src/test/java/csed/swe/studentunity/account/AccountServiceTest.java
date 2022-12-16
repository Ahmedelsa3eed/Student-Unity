package csed.swe.studentunity.account;

import csed.swe.studentunity.Logic.AccountService;
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
            "password", "admin");
    private User targetUser = new User("targetUser@mail.com", 7777,
            "firstname", "lastname",
            "password", "user");


    @Test
    void getAllAccountsByAnAdmin() {
        userService.addUser(user);
        assertNotNull(accountService.getAllAccounts());
        accountService.deleteAccount(1111);
    }

    @Test
    void searchAccountsByFirstname() {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        userService.addUser(user);
        assertNotNull(accountService.searchAccounts("firstname"));
        accountService.deleteAccount(1111);
    }

    @Test
    void searchAccountsByLastname() {
        userService.addUser(user);
        assertNotNull(accountService.searchAccounts( "lastname"));
        accountService.deleteAccount(1111);
    }

    @Test
    void searchAccountsByStudentId() {
        userService.addUser(user);
        assertNotNull(accountService.searchAccounts( "1111"));
        accountService.deleteAccount(1111);
    }

    @Test
    void searchAccountsByEmail() {
        userService.addUser(user);
        assertNotNull(accountService.searchAccounts("user1"));
        accountService.deleteAccount(1111);
    }

    @Test
    void changeRole() {
        userService.addUser(user);
        userService.addUser(targetUser);

        accountService.changeRole( 7777, "admin");
        assertTrue(accountService.changeRole(7777, "admin"));
        accountService.deleteAccount(1111);
        accountService.deleteAccount(7777);

    }


    @Test
    void deleteAccount() {
        userService.addUser(user);
        assertTrue(accountService.deleteAccount(1111));

    }


}