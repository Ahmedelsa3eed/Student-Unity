package csed.swe.studentunity.Logic;

import csed.swe.studentunity.DAO.UnverifiedUserRepo;
import csed.swe.studentunity.DAO.VerificationCodeRepo;
import csed.swe.studentunity.model.RegistrationResponses;
import csed.swe.studentunity.model.UnverifiedUser;
import csed.swe.studentunity.model.VerificationCode;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
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


    public String deleteUnverifiedUser(Long id){
        try {
            unverifiedUserRepo.deleteById(id);
            return "deleted successfully";
        }
        catch (Exception e){
            return "ID doesn't exist";
        }
    }




}
