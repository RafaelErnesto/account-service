package dev.com.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.com.domain.entities.Account;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApplicationScoped
public class AddAccountRequestDto {
    @NotNull
    @Email(message = "Email format is not valid")
    @JsonProperty("email")
    String email;
    @NotNull
    @Size(min = 3, max = 150, message = "Name must have between 3 and 150 characters")
    @JsonProperty("name")
    String name;
    @NotNull
    @Size(min = 8, max = 36, message = "Password must have between 8 and 36 digits")
    @JsonProperty("password")
    String password;
    @NotNull
    @Size(min = 3, max = 30, message = "Username must have between 3 and 30 characters")
    @JsonProperty("username")
    String username;

    public Account toAccount() {
        return new Account(this.name, this.email, this.password, this.username);
    }
}
