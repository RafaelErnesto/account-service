package dev.com.infraestructure.streaming;

import dev.com.domain.entities.Account;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestConsumer {
    @Incoming("accounts-out")
    public void consume(Account account) {
        System.out.println(account.getEmail());
    }
}
