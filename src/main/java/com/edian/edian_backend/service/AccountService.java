package com.edian.edian_backend.service;

import com.edian.edian_backend.common.AccountType;
import com.edian.edian_backend.dto.AccountDto;
import com.edian.edian_backend.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto dto);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccounts();
    AccountDto updateAccount(Long id,AccountDto updatedAccount);
    void  deleteAccount(Long id);
    List<AccountDto> getActiveAccounts();
    List<AccountDto> getAccountsByType(AccountType type);
    List<AccountDto> createAccounts(List<AccountDto> accountDtoList);
}
