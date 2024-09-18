package com.prueba_tecnica.bci.cl.service.impl;

import com.prueba_tecnica.bci.cl.service.generic.IAuthServices;
import org.springframework.stereotype.Service;

@Service
public class AuthServicesImpl implements IAuthServices {
    @Override
    public boolean authenticate(String username, String password) {

        return "T50735".equals(username) && "Ild234Toc486#".equals(password);
    }
}
