package csed.swe.studentunity.Logic.User;

import csed.swe.studentunity.model.User;
import csed.swe.studentunity.DAO.UserRepo;
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
        /*
            return of type Optional<User>
            it has method isEmpty
            if user exist it will be false
            if user doesn't exist return true
        */
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

    public String getUnverifiedUser() {
        // milestone 2
        return null;
    }

    public String addUnverifiedUser() {
        // milestone 2
        return null;
    }

}