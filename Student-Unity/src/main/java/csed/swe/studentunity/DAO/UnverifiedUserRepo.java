package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.UnverifiedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UnverifiedUserRepo extends JpaRepository<UnverifiedUser, Long> {
    Optional<UnverifiedUser> findUserByEmail(String email);
}

