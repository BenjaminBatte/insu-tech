package com.edian.edian_backend.impl;

import com.edian.edian_backend.common.AccountType;
import com.edian.edian_backend.dto.AccountDto;
import com.edian.edian_backend.entity.Account;
import com.edian.edian_backend.entity.NamedInsured;
import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.kafka.KafkaProducer;
import com.edian.edian_backend.repository.AccountRepository;
import com.edian.edian_backend.repository.AgentRepository;
import com.edian.edian_backend.repository.NamedInsuredRepository;
import com.edian.edian_backend.service.AccountService;
import com.edian.edian_backend.utility.AccountServiceUtility;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AgentRepository agentRepository;
    private final NamedInsuredRepository namedInsuredRepository;
    private final KafkaProducer kafkaProducer;

    private synchronized Long generateAccountNumber() {
        // Retrieve the highest existing account number or start from 10000000L if none exist
        Long lastAccountNumber = accountRepository.findHighestAccountNumber().orElse(10000000L);

        // Generate a new account number ensuring uniqueness
        Random random = new Random();
        long number;
        do {
            number = lastAccountNumber + 1 + random.nextInt(90000000);
        } while (accountRepository.existsByAccountNumber(number));

        return number;
    }

    @Override
    public AccountDto createAccount(AccountDto dto) {
        if (dto.getAccountNumber() == null) {
            dto.setAccountNumber(generateAccountNumber());
        } else if (this.accountRepository.existsByAccountNumber(dto.getAccountNumber())) {
            throw new IllegalArgumentException("Account with number " + dto.getAccountNumber() + " already exists.");
        }

        Account account = AccountServiceUtility.toAccount(dto, this.agentRepository, this.namedInsuredRepository);
        account.setActive(true);
        Account savedAccount = this.accountRepository.save(account);
        this.kafkaProducer.sendMessage("Account created: " + savedAccount.getAccountNumber());
        return AccountServiceUtility.toAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = this.findAccountById(id);
        return AccountServiceUtility.toAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = this.accountRepository.findAll();
        return accounts.stream().map(AccountServiceUtility::toAccountDto).collect(Collectors.toList());
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto updatedAccount) {
        Account account = this.findAccountById(id);
        if (updatedAccount.getAccountNumber() != null) {
            account.setAccountNumber(updatedAccount.getAccountNumber());
        }
        if (updatedAccount.getName() != null) {
            account.setName(updatedAccount.getName());
        }
        if (updatedAccount.getType() != null) {
            account.setType(AccountType.fromId(updatedAccount.getType()));
        }
        if (updatedAccount.getNamedInsuredId() != null) {
            NamedInsured namedInsured = namedInsuredRepository.findById(updatedAccount.getNamedInsuredId())
                    .orElseThrow(() -> new ResourceNotFoundException("Named Insured not found"));
            account.setNamedInsured(namedInsured);
        }
        account.setActive(updatedAccount.isActive());
        Account updatedAccountObj = this.accountRepository.save(account);
        return AccountServiceUtility.toAccountDto(updatedAccountObj);
    }

    @Override
    public void deleteAccount(Long id) {
        this.findAccountById(id);
        this.accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDto> getActiveAccounts() {
        List<Account> activeAccounts = this.accountRepository.findByIsActiveTrue();
        return activeAccounts.stream().map(AccountServiceUtility::toAccountDto).collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> getAccountsByType(AccountType type) {
        List<Account> accountsByType = this.accountRepository.findByType(type);
        return accountsByType.stream().map(AccountServiceUtility::toAccountDto).collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> createAccounts(List<AccountDto> accountDtoList) {
        List<Long> existingAccountNumbers = accountDtoList.stream()
                .map(AccountDto::getAccountNumber)
                .filter(accountRepository::existsByAccountNumber)
                .toList();
        if (!existingAccountNumbers.isEmpty()) {
            throw new IllegalArgumentException("Accounts with numbers " + existingAccountNumbers + " already exist.");
        }

        List<Account> accounts = accountDtoList.stream()
                .map(dto -> {
                    if (dto.getAccountNumber() == null) {
                        dto.setAccountNumber(generateAccountNumber());
                    }
                    return AccountServiceUtility.toAccount(dto, this.agentRepository, this.namedInsuredRepository);
                })
                .peek(account -> account.setActive(true))
                .collect(Collectors.toList());

        List<Account> savedAccounts = this.accountRepository.saveAll(accounts);
        return savedAccounts.stream().map(AccountServiceUtility::toAccountDto).collect(Collectors.toList());
    }

    private Account findAccountById(Long id) {
        return this.accountRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Account with id " + id + " not found")
        );
    }

    public AccountServiceImpl(final AccountRepository accountRepository, final AgentRepository agentRepository, final NamedInsuredRepository namedInsuredRepository, final KafkaProducer kafkaProducer) {
        this.accountRepository = accountRepository;
        this.agentRepository = agentRepository;
        this.namedInsuredRepository = namedInsuredRepository;
        this.kafkaProducer = kafkaProducer;
    }
}
