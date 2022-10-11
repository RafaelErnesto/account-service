package dev.com.application.usecases;

import dev.com.domain.entities.Account;

public interface Repository {

    <T> void insert(Account input);

    <I,O> O get(I key);
}
