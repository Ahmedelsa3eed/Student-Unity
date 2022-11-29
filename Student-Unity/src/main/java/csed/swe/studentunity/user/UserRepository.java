package csed.swe.studentunity.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
//    Optional<User> findUserByStudentId(Integer studentId);
    Optional<User> findUserByEmail(String email);
}
