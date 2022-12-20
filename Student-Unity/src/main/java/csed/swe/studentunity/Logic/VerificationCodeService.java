package csed.swe.studentunity.Logic;

import csed.swe.studentunity.DAO.VerificationCodeRepo;
import csed.swe.studentunity.model.VerificationCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class VerificationCodeService {
    private final VerificationCodeRepo verificationCodeRepo;
    private final EmailService emailService;
    public Optional<VerificationCode> getVerificationCode(String email) {
        return verificationCodeRepo.findVerificationCodeByEmail(email);
    }

    public String addVerificationCode(String email) {
        String code = generateCode();
        VerificationCode verificationCode = new VerificationCode(email, code);
        verificationCodeRepo.save(verificationCode);
        sendVerificationCode(verificationCode);
        return code;
    }

    public void deleteVerificationCode(String email) {
        getVerificationCode(email).ifPresent(verificationCodeRepo::delete);
    }


    public void sendVerificationCode(String email) {
        Optional<VerificationCode> verificationCode = getVerificationCode(email);
        if(verificationCode.isEmpty()) return;
        emailService.send("You have registered in Student Unity \n" +
                "verification code is: " + verificationCode.get().getCode(), "verification code", verificationCode.get().getEmail());

    }

    private void sendVerificationCode(VerificationCode verificationCode) {
        emailService.send("You have registered in Student Unity \n" +
                "verification code is: " + verificationCode.getCode(), "verification code", verificationCode.getEmail());
    }
    private String generateCode() {
        UUID code = UUID.randomUUID();
        return code.toString();
    }

}
