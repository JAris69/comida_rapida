package com.app.comidarapida.service.implementation.AuthServiceImpl;

import com.app.comidarapida.persistence.entities.users.UsuarioEntity;
import com.app.comidarapida.persistence.repositories.usuario.UserRepository;
import com.app.comidarapida.persistence.repositories.usuario.UserRoleRepository;
import com.app.comidarapida.service.interfaces.auth.AuthServiceInterface;
import com.app.comidarapida.util.mappers.user.UserMapper;
import com.app.comidarapida.util.security.JwtUtil;
import com.app.comidarapida.web.dtos.auth.LoginResponseDTO;
import com.app.comidarapida.web.dtos.user.UserLoginRequestDTO;
import com.app.comidarapida.web.dtos.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthServiceInterface {

    private final UserRepository usuarioRepository;
    private final UserRoleRepository usuarioRolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    @Override
    public LoginResponseDTO login(UserLoginRequestDTO request) {
        UsuarioEntity usuario = usuarioRepository.findByCorreoOrNombreUsuario(request.getNombre_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        List<String> roles = usuarioRolRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(ur -> ur.getRol().getNombre())
                .toList();

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(usuario.getNombreUsuario())
                .password(usuario.getPassword())
                .authorities(roles.toArray(new String[0]))
                .build();

        String token = jwtUtil.generarToken(userDetails, roles);

        UserResponseDTO userResp = userMapper.toResponseDTO(usuario, roles);

        return LoginResponseDTO.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(3600000L)
                .refreshToken(null)
                .user(userResp)
                .build();
    }
}
