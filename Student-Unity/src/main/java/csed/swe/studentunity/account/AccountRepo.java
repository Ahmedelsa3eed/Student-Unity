package csed.swe.studentunity.account;

import csed.swe.studentunity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepo extends JpaRepository<User, String> {

    @Query(value = "SELECT name, id, email, role FROM USER", nativeQuery = true)
    List<Object> getAllAccounts();

    @Query(value = "SELECT name, id, email, role FROM USER " +
                    "WHERE name LIKE %?1% OR id LIKE %?1% OR email LIKE %?1%", nativeQuery = true)
    List<Object> searchAccounts(String search);

    @Modifying
    @Query(value = "UPDATE USER SET role = ?2 WHERE id = ?1", nativeQuery = true)
    void changeRole(String id, String role);


}
