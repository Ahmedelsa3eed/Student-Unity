package csed.swe.studentunity.Logic.User;

import csed.swe.studentunity.model.User;
import csed.swe.studentunity.DAO.UserRepository;
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


}