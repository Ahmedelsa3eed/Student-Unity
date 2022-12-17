package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationCodeRepo extends JpaRepository<VerificationCode, Long> {
    Optional<VerificationCode> findVerificationCodeByEmail(String email);
}