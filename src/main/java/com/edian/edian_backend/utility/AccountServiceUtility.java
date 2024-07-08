package com.edian.edian_backend.utility;

import com.edian.edian_backend.common.AccountType;
import com.edian.edian_backend.dto.AccountDto;
import com.edian.edian_backend.entity.Account;
import com.edian.edian_backend.entity.Agent;
import com.edian.edian_backend.entity.NamedInsured;
import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.repository.AgentRepository;
import com.edian.edian_backend.repository.NamedInsuredRepository;

public class AccountServiceUtility {

    public static AccountDto toAccountDto(Account account) {
        if (account == null) {
            return null;
        }
        AccountDto dto = new AccountDto();
        dto.setAccountNumber(account.getAccountNumber());
        dto.setName(account.getName());
        dto.setType(account.getType() != null ? account.getType().getId() : null);
        dto.setActive(account.isActive());
        if (account.getAgent() != null) {
            dto.setAgentId(account.getAgent().getId());
        }
        if (account.getNamedInsured() != null) {
            dto.setNamedInsuredId(account.getNamedInsured().getId());
        }
        return dto;
    }

    public static Account toAccount(AccountDto dto, AgentRepository agentRepository, NamedInsuredRepository namedInsuredRepository) {
        if (dto == null) {
            return null;
        }
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setName(dto.getName());
        account.setType(dto.getType() != null ? AccountType.fromId(dto.getType()) : null);
        account.setActive(dto.isActive());
        setAgent(dto, account, agentRepository);
        setNamedInsured(dto, account, namedInsuredRepository);
        return account;
    }

    private static void setAgent(AccountDto dto, Account account, AgentRepository agentRepository) {
        if (dto.getAgentId() != null) {
            Agent agent = agentRepository.findById(dto.getAgentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Agent with id " + dto.getAgentId() + " not found"));
            account.setAgent(agent);
        }
    }

    private static void setNamedInsured(AccountDto dto, Account account, NamedInsuredRepository namedInsuredRepository) {
        if (dto.getNamedInsuredId() != null) {
            NamedInsured namedInsured = namedInsuredRepository.findById(dto.getNamedInsuredId())
                    .orElseThrow(() -> new ResourceNotFoundException("Named insured with id " + dto.getNamedInsuredId() + " not found"));
            account.setNamedInsured(namedInsured);
        }
    }
}
