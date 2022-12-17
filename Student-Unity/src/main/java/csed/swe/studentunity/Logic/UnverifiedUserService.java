package csed.swe.studentunity.Logic;

import csed.swe.studentunity.DAO.UnverifiedUserRepo;
import csed.swe.studentunity.DAO.VerificationCodeRepo;
import csed.swe.studentunity.model.UnverifiedUser;
import csed.swe.studentunity.model.VerificationCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@AllArgsConstructor
@Service
public class UnverifiedUserService {

    private UnverifiedUserRepo unverifiedUserRepo;
    private VerificationCodeRepo verificationCodeRepo;
    public Optional<UnverifiedUser> getUnverifiedUser(String email) {
        return unverifiedUserRepo.findUnverifiedUserByEmail(email);
    }

    public String addUnverifiedUser(UnverifiedUser unverifiedUser) {
        // milestone 2
        unverifiedUserRepo.save(unverifiedUser);
        String code = generateCode();
        VerificationCode verificationCode = new VerificationCode(unverifiedUser.getEmail(), code);
        verificationCodeRepo.save(verificationCode);
        return code;
    }

    public boolean verifyUser(String email, String code) {
        // milestone 2
        Optional<UnverifiedUser> unverifiedUser = unverifiedUserRepo.findUnverifiedUserByEmail(email);
        if(unverifiedUser.isEmpty()) {
            return false;
        }
        Optional<VerificationCode> verificationCode = verificationCodeRepo.findVerificationCodeByEmail(email);
        if(verificationCode.isEmpty()) {
            return false;
        }
        if(verificationCode.get().getCode().equals(code)) {
            verificationCodeRepo.delete(verificationCode.get());
            return true;
        }
        return false;
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

    private String generateCode() {
        UUID code = UUID.randomUUID();
        return code.toString();
    }


}