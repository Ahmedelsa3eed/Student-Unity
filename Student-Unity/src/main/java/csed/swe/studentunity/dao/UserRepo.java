package csed.swe.studentunity.dao;

import csed.swe.studentunity.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Transactional
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(Long id);

    Optional<User> findUserByStudentId(Integer studentId);

    // change password
    @Modifying
    @Query(value = "UPDATE User u SET u.password = :password WHERE u.id = :id")
    void updateUserPassword(@Param("password") String password, @Param("id") Long id);
    // change first name
    @Modifying
    @Query(value = "UPDATE User u SET u.firstName = :firstName WHERE u.id = :id")
    void updateUserFirstName(@Param("firstName") String firstName, @Param("id") Long id);
    // change last name
    @Modifying
    @Query(value = "UPDATE User u SET u.lastName = :lastName WHERE u.id = :id")
    void updateUserLastName(@Param("lastName") String lastName, @Param("id") Long id);
    // change student id
    @Modifying
    @Query(value = "UPDATE User u SET u.studentId = :studentId WHERE u.id = :id")
    void updateUserStudentId(@Param("studentId") Integer studentId, @Param("id") Long id);

}
