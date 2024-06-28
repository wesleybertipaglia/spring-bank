package com.wesleybertipaglia.bank.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wesleybertipaglia.bank.dtos.AccountDTO;
import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public List<AccountDTO> listAccounts() {
        return accountService.listAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable UUID id) {
        Optional<AccountDTO> account = accountService.getAccountById(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{agencyId}")
    public AccountDTO createAccount(@PathVariable UUID agencyId, @RequestBody Account account) {
        return accountService.createAccount(agencyId, account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable UUID id, @RequestBody Account account) {
        Optional<AccountDTO> updatedAccount = accountService.updateAccount(id, account);
        return updatedAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
