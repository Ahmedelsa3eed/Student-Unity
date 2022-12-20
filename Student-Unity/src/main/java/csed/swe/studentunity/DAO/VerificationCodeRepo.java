package csed.swe.studentunity.DAO;

import csed.swe.studentunity.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VerificationCodeRepo extends JpaRepository<VerificationCode, Long> {
    @Query(value = "SELECT v FROM VerificationCode v WHERE v.email = ?1")
    Optional<VerificationCode> findVerificationCodeByEmail(String email);
}