package csed.swe.studentunity.account;

import csed.swe.studentunity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepo extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM user", nativeQuery = true)
    List<User> getAllAccounts();

    @Query(value = "SELECT * FROM user " +
                    "WHERE name LIKE %?1% OR id LIKE %?1% OR email LIKE %?1%", nativeQuery = true)
    List<User> searchAccounts(String searchString);

    @Modifying
    @Query(value = "UPDATE user SET role = ?2 WHERE id = ?1", nativeQuery = true)
    void changeRole(Integer targetUserId, String role);

    void deleteAccountByStudentId(Integer studentId);

}
