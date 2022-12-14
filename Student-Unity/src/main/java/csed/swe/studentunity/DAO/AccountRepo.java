package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepo extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM user", nativeQuery = true)
    List<User> getAllAccounts();

    @Query(value = "SELECT * FROM user " +
                    "WHERE first_name LIKE %?1% OR student_id LIKE %?1% OR email LIKE %?1% OR last_name LIKE %?1%", nativeQuery = true)
    List<User> searchAccounts(String searchString);

    @Modifying
    @Query(value = "UPDATE user SET role = ?2 WHERE email= ?1", nativeQuery = true)
    void changeRole(String targetUserEmail, String role);

    @Modifying
    @Query(value = "DELETE FROM user WHERE student_id = ?1", nativeQuery = true)
    void deleteAccountByStudentId(Integer studentId);

}
