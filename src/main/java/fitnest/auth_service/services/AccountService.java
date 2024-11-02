package fitnest.auth_service.services;

import fitnest.auth_service.Repos.AccountRepo;
import fitnest.auth_service.entities.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepo accountRepo;

    public ResponseEntity<Account> findAccountById(Long id) {
        return accountRepo.findById(id)
                .map(ResponseEntity::ok)  // If user is found, return 200 with user
                .orElseGet(() -> ResponseEntity.notFound().build());  // Else return 404
    }

    public ResponseEntity<Account> findAccountByUsername(String email) {
        return accountRepo.findByUsername(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Account>> retrieveAllAccounts() {
        List<Account> users = accountRepo.findAll();
        return ResponseEntity.ok(users);
    }
}
