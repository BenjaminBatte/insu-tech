package com.edian.edian_backend.controller;

import com.edian.edian_backend.common.AccountType;
import com.edian.edian_backend.dto.AccountDto;
import com.edian.edian_backend.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto dto) {
        AccountDto savedAccount = accountService.createAccount(dto);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id) {
        AccountDto dto = accountService.getAccountById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Long id, @RequestBody AccountDto updatedDto) {
        AccountDto dto = accountService.updateAccount(id, updatedDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
    @GetMapping("/active")
    public ResponseEntity<List<AccountDto>> getActiveAccounts() {
        List<AccountDto> activeAccounts = accountService.getActiveAccounts();
        return ResponseEntity.ok(activeAccounts);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<AccountDto>> getAccountsByType(@PathVariable("type") String type) {
        List<AccountDto> accountsByType = accountService.getAccountsByType(AccountType.fromId(type));
        return ResponseEntity.ok(accountsByType);
    }
    @PostMapping("/batch")
    public ResponseEntity<List<AccountDto>> createAccounts(@RequestBody List<AccountDto> accountDtoList) {
        List<AccountDto> savedAccounts = accountService.createAccounts(accountDtoList);
        return new ResponseEntity<>(savedAccounts, HttpStatus.CREATED);
    }

}