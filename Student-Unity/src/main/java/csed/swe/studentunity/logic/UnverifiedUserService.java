package csed.swe.studentunity.logic;

import csed.swe.studentunity.dao.UnverifiedUserRepo;
import csed.swe.studentunity.model.UnverifiedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UnverifiedUserService {

    private UnverifiedUserRepo unverifiedUserRepo;
    public Optional<UnverifiedUser> getUnverifiedUser(String email) {
        return unverifiedUserRepo.findUnverifiedUserByEmail(email);
    }

    public void addUnverifiedUser(UnverifiedUser unverifiedUser) {
        unverifiedUserRepo.save(unverifiedUser);
    }


    public void deleteUnverifiedUser(Long id){
        unverifiedUserRepo.deleteById(id);
    }




}
