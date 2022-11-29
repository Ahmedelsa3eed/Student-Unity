package csed.swe.studentunity.queries;

import csed.swe.studentunity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserQueries extends JpaRepository<User, String> {
    Optional<User> findUserById(String email);
}
