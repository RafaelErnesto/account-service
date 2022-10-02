package dev.com.application.usecases;

import dev.com.domain.entities.Account;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddAccountUseCase {
    Repository accountRepository;

    AddAccountUseCase(Repository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(Account input) {
        if(this.userAlreadyExists(input.getEmail())){
            throw new RuntimeException("User already exists");
        }
        accountRepository.insert(input);
    }

    private boolean userAlreadyExists(String email){
        Account userFound = accountRepository.get(email);
        return userFound != null;
    }
}
