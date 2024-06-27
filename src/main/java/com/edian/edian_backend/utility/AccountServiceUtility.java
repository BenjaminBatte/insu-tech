package com.edian.edian_backend.utility;

import com.edian.edian_backend.common.AccountType;
import com.edian.edian_backend.dto.AccountDto;
import com.edian.edian_backend.entity.Account;

public class AccountServiceUtility {
    public static AccountDto toAccountDto(Account account){
        if (account == null) {
            return null;
        }
        AccountDto dto=new AccountDto();
        dto.setId(account.getId());
        dto.setNumber(account.getNumber());
        dto.setName(account.getName());
        dto.setType(account.getType() != null ? account.getType().getId() : null);
        dto.setActive(account.isActive());
        return dto;
    }
    public static Account toAccount(AccountDto dto) {
        if (dto == null) {
            return null;
        }
        Account account = new Account();
        account.setId(dto.getId());
        account.setNumber(dto.getNumber());
        account.setName(dto.getName());
        account.setType(dto.getType() != null ? AccountType.fromId(dto.getType()) : null);
        account.setActive(dto.isActive());
        return account;
    }
}
