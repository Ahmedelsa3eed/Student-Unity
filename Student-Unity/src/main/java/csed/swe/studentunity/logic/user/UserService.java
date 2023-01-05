package csed.swe.studentunity.logic.user;

import csed.swe.studentunity.model.User;
import csed.swe.studentunity.dao.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;

    public void addUser(User user) {
         userRepo.save(user);
    }

    public Optional<User> getUser(String email) {
        return userRepo.findUserByEmail(email);
    }

    public Optional<User> getUser(Long id) {
        return userRepo.findUserById(id);
    }

    public Optional<User> getUser(Integer studentId) {
        return userRepo.findUserByStudentId(studentId);
    }
    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }

    public void updateUserPassword(String password, Long id){
        userRepo.updateUserPassword(password, id);
    }

    public void updateUserFirstName(String firstName, Long id){
        userRepo.updateUserFirstName(firstName, id);
    }

    public void updateUserLastName(String lastName, Long id){
        userRepo.updateUserLastName(lastName, id);
    }

    public void updateUserStudentId(Integer studentId, Long id){
        userRepo.updateUserStudentId(studentId, id);
    }



}