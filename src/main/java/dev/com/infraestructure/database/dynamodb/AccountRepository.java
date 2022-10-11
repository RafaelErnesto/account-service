package dev.com.infraestructure.database.dynamodb;

import dev.com.application.usecases.Repository;
import dev.com.config.AccountConfigProperties;
import dev.com.domain.entities.Account;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class AccountRepository implements Repository {

    @Inject
    DynamoDbClient dynamoDB;
    @Inject
    AccountConfigProperties accountConfigProperties;

    public String getTableName() {
        return accountConfigProperties.tableName();
    }
    @Override
    public <Account> void insert(dev.com.domain.entities.Account input) {
        dynamoDB.putItem(putRequest(input));
    }

    @Override
    public <String, Account> Account get(String key) {
        return null;
    }

    private PutItemRequest putRequest(Account account) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("email", AttributeValue.builder().s(account.getEmail()).build());
        item.put("username", AttributeValue.builder().s(account.getUsername()).build());
        item.put("name", AttributeValue.builder().s(account.getName()).build());
        item.put("created_at", AttributeValue.builder().s(LocalDateTime.now().toString()).build());

        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }
}
