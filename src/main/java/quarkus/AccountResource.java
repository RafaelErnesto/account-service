package quarkus;


import org.jboss.resteasy.annotations.Body;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Path("/accounts")
public class AccountResource {

    Set<Account> accounts = new HashSet<>();

    @PostConstruct
    public void setup() {
        accounts.add(new Account(123456789L, 987654321L, "George Baird", new BigDecimal("354.23")));
        accounts.add(new Account(121212121L, 888777666L, "Mary Taylor", new BigDecimal("560.03")));
        accounts.add(new Account(545454545L, 222444999L, "Diana Rigg", new BigDecimal("422.00")));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Account> getAllAccounts() {
        return accounts;
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("accountNumber") Long accountNumber) {
        Optional<Account> response = accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst();
        return response.orElseThrow(()
                -> new NotFoundException("Account with id: " + accountNumber + " was not found"));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account createAccount(CreateAccountRequestBody requestBody) {
        Long accountNumber = new Random().nextLong();
        Account account = new Account(accountNumber, requestBody.customerNumber, requestBody.customerName, requestBody.balance);
        accounts.add(account);
        return account;
    }

    @DELETE
    @Path("/{accountNumber}")
    public void deleteAccount(@PathParam("accountNumber") Long accountNumber) {
        Optional<Account> accountFound = accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst();

        accountFound.ifPresentOrElse(
                account -> account.close(),
                () -> {
                    throw new NotFoundException("Account with number: " + accountNumber + " was not found");
                }
        );
    }

    @PATCH
    @Path("/{accountNumber}/add-funds")
    @Consumes(MediaType.APPLICATION_JSON)
    public Account addFounds(@PathParam("accountNumber") Long accountNumber, AddFundsRequestBody request) {
        Optional<Account> accountFound = accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber) && !account.accountStatus.equals(AccountStatus.CLOSED))
                .findFirst();

        accountFound.ifPresentOrElse(
                account -> {
                    account.setBalance(request.funds);
                    if (account.balance.signum() > 0 && account.accountStatus.equals(AccountStatus.OVERDRAWN)) {
                        account.removeOverdrawnState();
                    }
                },
                () -> {
                    throw new NotFoundException("Account with number: " + accountNumber + " was not found");
                }
        );

        return accountFound.get();
    }
}
