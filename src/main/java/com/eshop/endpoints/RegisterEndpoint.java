package com.eshop.endpoints;

import com.eshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import shopeeng.account.RegisterRequest;
import shopeeng.account.RegisterResponse;

import static com.eshop.utils.Constants.NAMESPACE_URI;


@Endpoint
public class RegisterEndpoint {
    private UserRepository userRepository;

    @Autowired
    public RegisterEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerRequest")
    @ResponsePayload
    public RegisterResponse register(@RequestPayload RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        response.setUser(userRepository.registerUser(request.getUser()));
        return response;
    }


}