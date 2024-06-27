package com.edian.edian_backend.impl;

import com.edian.edian_backend.common.AccountType;
import com.edian.edian_backend.dto.AccountDto;
import com.edian.edian_backend.entity.Account;
import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.repository.AccountRepository;
import com.edian.edian_backend.service.AccountService;
import com.edian.edian_backend.utility.AccountServiceUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto dto) {
        if (accountRepository.existsByNumber(dto.getNumber())){
            throw new IllegalArgumentException("Account with name " + dto.getName() + " already exists.");
        }
        Account account = AccountServiceUtility.toAccount(dto);
        account.setActive(true); // Ensure the account is active by default
        Account savedAccount = accountRepository.save(account);
        return AccountServiceUtility.toAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = findAccountById(id);
        return AccountServiceUtility.toAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountServiceUtility::toAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto updatedAccount) {
        Account account = findAccountById(id);
        if (updatedAccount.getNumber() != null) {
            account.setNumber(updatedAccount.getNumber());
        }
        if (updatedAccount.getName() != null) {
            account.setName(updatedAccount.getName());
        }
        if (updatedAccount.getType() != null) {
            account.setType(AccountType.fromId(updatedAccount.getType()));
        }
        account.setActive(updatedAccount.isActive());
        Account updatedAccountObj = accountRepository.save(account);
        return AccountServiceUtility.toAccountDto(updatedAccountObj);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = findAccountById(id);
        accountRepository.deleteById(id);
    }
    @Override
    public List<AccountDto> getActiveAccounts() {
        List<Account> activeAccounts = accountRepository.findByIsActiveTrue();
        return activeAccounts.stream()
                .map(AccountServiceUtility::toAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> getAccountsByType(AccountType type) {
        List<Account> accountsByType = accountRepository.findByType(type);
        return accountsByType.stream()
                .map(AccountServiceUtility::toAccountDto)
                .collect(Collectors.toList());
    }
    public List<AccountDto> createAccounts(List<AccountDto> accountDtoList) {
        // Check for duplicates in the list based on account number
        List<String> existingAccountNumbers = accountDtoList.stream()
                .map(AccountDto::getNumber)
                .filter(accountRepository::existsByNumber)
                .toList();

        if (!existingAccountNumbers.isEmpty()) {
            throw new IllegalArgumentException("Accounts with numbers " + existingAccountNumbers + " already exist.");
        }

        // Map DTOs to entities, set them as active, and save them
        List<Account> accounts = accountDtoList.stream()
                .map(AccountServiceUtility::toAccount)
                .peek(account -> account.setActive(true)) // Ensure all accounts are active by default
                .collect(Collectors.toList());

        List<Account> savedAccounts = accountRepository.saveAll(accounts);
        return savedAccounts.stream()
                .map(AccountServiceUtility::toAccountDto)
                .collect(Collectors.toList());
    }
    // Private helper method to find account by id and handle exception
    private Account findAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account with id " + id + " not found"));
    }
}
