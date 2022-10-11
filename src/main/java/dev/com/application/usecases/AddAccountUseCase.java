package dev.com.application.usecases;

import dev.com.domain.entities.Account;
import dev.com.infraestructure.database.dynamodb.AccountRepository;
import dev.com.infraestructure.streaming.AccountEmitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddAccountUseCase {

    Repository accountRepository;

    @Inject
    AccountEmitter accountEmitter;

    AddAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(Account input) {
        if (this.userAlreadyExists(input.getEmail())) {
            throw new RuntimeException("User already exists");
        }
       // accountRepository.insert(input);
        accountEmitter.send(input);
    }

    private boolean userAlreadyExists(String email) {
        Account userFound = accountRepository.get(email);
        return userFound != null;
    }
}
