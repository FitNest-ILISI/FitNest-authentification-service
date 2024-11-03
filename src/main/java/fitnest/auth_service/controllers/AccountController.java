package fitnest.auth_service.controllers;

import fitnest.auth_service.entities.Account;
import fitnest.auth_service.services.IAccountService; // Import the interface
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService; // Use the interface here

    @GetMapping("/id")
    public ResponseEntity<Account> getUserAccountById(@RequestParam Long id) {
        return accountService.findAccountById(id);
    }

    @GetMapping("/username")
    public ResponseEntity<Account> getAccountByUsername(@RequestParam String email) {
        return accountService.findAccountByUsername(email);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return accountService.retrieveAllAccounts();
    }
}
