package csed.swe.studentunity.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void addUser(User user) {
         userRepository.save(user);
    }

    public Optional<User> getUser(String email) {
        /*
            return of type Optional<User>
            it has method isEmpty
            if user exist it will be false
            if user doesn't exist return true
        */
        return userRepository.findUserByEmail(email);
    }

    public String deleteUser(Long id){
        try {
            userRepository.deleteById(id);
            return "deleted successfully";
        }
        catch (Exception e){
            return "ID doesn't exist";
        }
    }

    public String getUnverifiedUser() {
        // milestone 2
        return "works";
    }

    public String addUnverifiedUser() {
        // milestone 2
        return "works";
    }

}