package dev.com.infraestructure.streaming;

import dev.com.domain.entities.Account;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountConsumer {
    @Incoming("accounts-in")
    public void consume(Account account) {
        // process your price.
    }
}
