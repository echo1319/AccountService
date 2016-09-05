package com.eshop.endpoints;

import com.eshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import shopeeng.account.LoginRequest;
import shopeeng.account.LoginResponse;

import static com.eshop.utils.Constants.NAMESPACE_URI;

@Endpoint
public class LoginEndpoint {
    private UserRepository userRepository;

    @Autowired
    public LoginEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest request) {
        LoginResponse response = new LoginResponse();
        response.setUser(userRepository.loginUser(request.getLogin().getUsername(), request.getLogin().getPassword()));
        return response;
    }


}