package csed.swe.studentunity.account;

import csed.swe.studentunity.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AccountService {
    private final AccountRepo accountRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    List<Object> getAllAccounts(String sessionId) {
        // call activeUser service
        return accountRepo.findByEmail("es-amgaballah24@alexu.edu.eg");
    }
}
