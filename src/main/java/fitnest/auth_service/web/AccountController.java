package fitnest.auth_service.web;

import fitnest.auth_service.entities.Account;
import fitnest.auth_service.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/id")
    public ResponseEntity<Account> getUserById(@RequestParam Long id) {
        return accountService.findAccountById(id);
    }

    @GetMapping("/username")
    public ResponseEntity<Account> getaccountByaccountname(@RequestParam String email) {
        return accountService.findAccountByUsername(email);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return accountService.retrieveAllAccounts();
    }


}
