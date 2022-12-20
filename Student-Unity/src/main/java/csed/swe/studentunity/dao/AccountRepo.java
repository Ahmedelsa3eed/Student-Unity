package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepo extends JpaRepository<User, String> {

    @Query(value = "SELECT new User(u.email, u.studentId, u.firstName, u.lastName,  u.role) FROM User as u ")
    List<User> getAllAccounts();

    @Query(value = "SELECT new User(u.email, u.studentId, u.firstName, u.lastName,  u.role) FROM User as u " +
                    "WHERE u.firstName LIKE %?1% OR u.lastName LIKE %?1% OR u.email LIKE %?1% OR u.lastName LIKE %?1%")
    List<User> searchAccounts(String searchString);

    @Modifying
    @Query(value = "UPDATE user SET role = ?2 WHERE email= ?1", nativeQuery = true)
    void changeRole(String targetUserEmail, String role);

    @Modifying
    @Query(value = "DELETE FROM user WHERE email = ?1", nativeQuery = true)
    void deleteAccountByEmail(String targetUserEmail);

}
