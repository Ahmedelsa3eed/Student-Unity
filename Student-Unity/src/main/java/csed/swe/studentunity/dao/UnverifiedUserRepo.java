package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.UnverifiedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UnverifiedUserRepo extends JpaRepository<UnverifiedUser, Long> {
    Optional<UnverifiedUser> findUnverifiedUserByEmail(String email);
}

