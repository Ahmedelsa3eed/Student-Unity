package csed.swe.studentunity.Logic.AccountsPage;

import csed.swe.studentunity.DAO.AccountRepo;
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

    public List<User> getAllAccounts() {
        return accountRepo.getAllAccounts();
    }

    public List<User> searchAccounts(String searchString) {
        return accountRepo.searchAccounts(searchString);
    }

    public Boolean changeRole(Integer targetUserId, String role) {
        accountRepo.changeRole(targetUserId, role);
        return true;
    }

    public Boolean deleteAccount(Integer targetUserId) {
        accountRepo.deleteAccountByStudentId(targetUserId);
        return true;
    }

}
