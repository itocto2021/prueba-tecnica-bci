package com.prueba_tecnica.bci.cl.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AuthServicesImplTest {

    private final AuthServicesImpl authServices = new AuthServicesImpl();

    @Test
    public void authenticateValidCredentialsTest() {
        boolean result = authServices.authenticate("T50735", "Ild234Toc486#");
        assertTrue(result, "Expected authentication to succeed with valid credentials.");
    }

    @Test
    public void authenticateInvalidUsernameTest() {
        boolean result = authServices.authenticate("InvalidUser", "Ild234Toc486#");
        assertFalse(result, "Expected authentication to fail with invalid username.");
    }

    @Test
    public void authenticateInvalidPasswordTest() {
        boolean result = authServices.authenticate("T50735", "InvalidPassword");
        assertFalse(result, "Expected authentication to fail with invalid password.");
    }

    @Test
    public void authenticateInvalidCredentialsTest() {
        boolean result = authServices.authenticate("InvalidUser", "InvalidPassword");
        assertFalse(result, "Expected authentication to fail with invalid credentials.");
    }
}