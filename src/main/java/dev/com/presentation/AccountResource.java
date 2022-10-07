package dev.com.presentation;

import dev.com.application.usecases.AddAccountUseCase;
import dev.com.presentation.dto.AddAccountRequestDto;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/account")
public class AccountResource {

    @Inject
    AddAccountUseCase addAccountUseCase;

    @POST
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String addUser(@Valid AddAccountRequestDto addAccountRequest) {
         addAccountUseCase.execute(addAccountRequest.toAccount());
         return "";
    }
}