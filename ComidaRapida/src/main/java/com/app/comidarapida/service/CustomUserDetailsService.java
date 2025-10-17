package com.app.comidarapida.service;

import com.app.comidarapida.enums.Estado;
import com.app.comidarapida.persistence.entities.rol.RolEntity;
import com.app.comidarapida.persistence.entities.rol.RolPermisoEntity;
import com.app.comidarapida.persistence.entities.users.UsuarioEntity;
import com.app.comidarapida.persistence.entities.users.UsuarioRolEntity;
import com.app.comidarapida.persistence.repositories.usuario.UserRepository;
import com.app.comidarapida.persistence.repositories.usuario.UserRoleRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public CustomUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = userRepository.findByCorreoOrNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<UsuarioRolEntity> roles = userRoleRepository.findRolesConPermisosPorUsuario(usuario.getId());

        Set<String> authorities = new HashSet<>();

        for (UsuarioRolEntity ur : roles) {
            RolEntity rol = ur.getRol();
            authorities.add("ROLE_" + rol.getNombre().toUpperCase());

            for (RolPermisoEntity rp : rol.getPermisos()) {
                if (rp.getEstado() == Estado.ACTIVO && rp.getPermiso().getEstado() == Estado.ACTIVO) {
                    authorities.add(rp.getPermiso().getCodigo());
                }
            }
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getNombreUsuario())
                .password(usuario.getPassword())
                .authorities(authorities.toArray(new String[0]))
                .build();
    }
}
