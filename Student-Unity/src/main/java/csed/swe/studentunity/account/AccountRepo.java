package csed.swe.studentunity.account;

import csed.swe.studentunity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepo extends JpaRepository<User, String> {

    @Query(value = "SELECT name, id, email, role FROM USER", nativeQuery = true)
    List<Object> findByEmail(String email);
}
