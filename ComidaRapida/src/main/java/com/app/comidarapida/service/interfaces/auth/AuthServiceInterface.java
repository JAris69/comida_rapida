package com.app.comidarapida.service.interfaces.auth;

import com.app.comidarapida.web.dtos.auth.LoginResponseDTO;
import com.app.comidarapida.web.dtos.user.UserLoginRequestDTO;

public interface AuthServiceInterface {

    LoginResponseDTO login(UserLoginRequestDTO request);
}
