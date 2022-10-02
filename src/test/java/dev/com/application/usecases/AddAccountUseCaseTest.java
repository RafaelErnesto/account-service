package dev.com.application.usecases;

import dev.com.domain.entities.Account;
import dev.com.infraestructure.database.dynamodb.AccountRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.inject.Inject;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddAccountUseCaseTest {
    AddAccountUseCase sut;

    @InjectMock
    AccountRepository accountRepository;

    @BeforeAll
    void startup(){
        sut = new AddAccountUseCase(accountRepository);
    }


    @Test
    void addAccountThrowsWhenAccountExists(){
        Account account = new Account("Joseph", "j@mail.com","123456","jh");
        Mockito.when(accountRepository.get(account.getEmail())).thenReturn(account);
        Assertions.assertThrows(Exception.class, () -> sut.execute(account));
    }

    @Test
    void addAccountInsertsWhenAccountIsOK(){
        Account account = new Account("Joseph", "j@mail.com","123456","jh");
        Mockito.when(accountRepository.get(account.getEmail())).thenReturn(null);
        sut.execute(account);
        Mockito.verify(accountRepository).insert(account);
    }
}
