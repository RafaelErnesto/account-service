package dev.com.infraestructure.streaming;


import dev.com.domain.entities.Account;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class AccountEmitter {

    @Inject
    @Channel("accounts-auth")
    Emitter<Account> accountEmitter;

    public void send(Account account){
        CompletionStage<Void> ack = accountEmitter.send(account);
    }
}
