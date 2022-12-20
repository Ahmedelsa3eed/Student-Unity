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

    public String deleteUser(Long id){
        try {
            userRepo.deleteById(id);
            return "deleted successfully";
        }
        catch (Exception e){
            return "ID doesn't exist";
        }
    }


}