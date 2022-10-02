package dev.com.infraestructure.database.dynamodb;

import dev.com.application.usecases.Repository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository implements Repository {
    @Override
    public <User> void insert(User input) {

    }

    @Override
    public <String, User> User get(String key) {
        return null;
    }
}
