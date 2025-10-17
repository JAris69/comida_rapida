package com.app.comidarapida.service.implementation.user;

import com.app.comidarapida.enums.Estado;
import com.app.comidarapida.enums.EstadoLog;
import com.app.comidarapida.persistence.entities.personal.PersonalEntity;
import com.app.comidarapida.persistence.entities.rol.RolEntity;
import com.app.comidarapida.persistence.entities.users.UsuarioEntity;
import com.app.comidarapida.persistence.entities.users.UsuarioRolEntity;
import com.app.comidarapida.persistence.repositories.personal.PersonalRepository;
import com.app.comidarapida.persistence.repositories.rol.RoleRepository;
import com.app.comidarapida.persistence.repositories.usuario.UserRepository;
import com.app.comidarapida.persistence.repositories.usuario.UserRoleRepository;
import com.app.comidarapida.service.interfaces.user.UserServiceInterface;
import com.app.comidarapida.util.mappers.user.UserMapper;
import com.app.comidarapida.web.dtos.user.UserRegisterRequestDTO;
import com.app.comidarapida.web.dtos.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository usuarioRepository;
    private final RoleRepository rolRepository;
    private final UserRoleRepository usuarioRolRepository;
    private final PersonalRepository personalRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO registrar(UserRegisterRequestDTO request) {
        PersonalEntity personal = personalRepository.findById(request.getPersonalId())
                .orElseThrow(() -> new RuntimeException("Personal no encontrado"));

        UsuarioEntity usuario = userMapper.toEntity(request);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setEstado(Estado.ACTIVO);
        usuario.setEstadoLog(EstadoLog.ALTA);
        usuario.setCreatedAt(LocalDateTime.now());
        usuario.setUpdatedAt(LocalDateTime.now());

        usuario.setPersonal(personal);

        usuarioRepository.save(usuario);

        RolEntity rolEntity = rolRepository.findById(request.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        UsuarioRolEntity usuarioRolEntity = UsuarioRolEntity.builder()
                .usuario(usuario)
                .rol(rolEntity)
                .estado(Estado.ACTIVO)
                .fechaCreacion(LocalDateTime.now())
                .build();

        usuarioRolRepository.save(usuarioRolEntity);

        personal.setUsuario(usuario);
        personalRepository.save(personal);

        return userMapper.toResponseDTO(usuario, List.of(rolEntity.getNombre()));
    }

    @Override
    public UserResponseDTO editarUsuario(Integer id, UserRegisterRequestDTO request) {
        UsuarioEntity usuario = usuarioRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setCorreo(request.getCorreo());
        usuario.setNombreUsuario(request.getNombreUsuario());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        usuario.setUpdatedAt(LocalDateTime.now());

        // Asociar a otro personal si cambia el personalId
        if (request.getPersonalId() != null) {
            PersonalEntity personal = personalRepository.findById(request.getPersonalId())
                    .orElseThrow(() -> new RuntimeException("Personal no encontrado"));
            usuario.setPersonal(personal);
        }

        usuarioRepository.save(usuario);

        if (request.getRolId() != null) {
            RolEntity nuevoRolEntity = rolRepository.findById(request.getRolId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

            List<UsuarioRolEntity> rolesPrevios = usuarioRolRepository.findByUsuarioId(usuario.getId());
            usuarioRolRepository.deleteAll(rolesPrevios);

            UsuarioRolEntity usuarioRolEntity = UsuarioRolEntity.builder()
                    .usuario(usuario)
                    .rol(nuevoRolEntity)
                    .estado(Estado.ACTIVO)
                    .fechaCreacion(LocalDateTime.now())
                    .build();

            usuarioRolRepository.save(usuarioRolEntity);
        }

        List<String> roles = usuarioRolRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(ur -> ur.getRol().getNombre())
                .toList();

        return userMapper.toResponseDTO(usuario, roles);
    }

    @Override
    public UserResponseDTO cambiarEstado(Integer id, Estado nuevoEstado) {
        UsuarioEntity usuario = usuarioRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setEstado(nuevoEstado);
        usuario.setUpdatedAt(LocalDateTime.now());
        usuarioRepository.save(usuario);

        List<String> roles = usuarioRolRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(ur -> ur.getRol().getNombre())
                .toList();

        return userMapper.toResponseDTO(usuario, roles);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        UsuarioEntity usuario = usuarioRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setEstado(Estado.INACTIVO);
        usuario.setEstadoLog(EstadoLog.BAJA);
        usuario.setUpdatedAt(LocalDateTime.now());

        usuarioRepository.save(usuario);
    }

    @Override
    public List<UserResponseDTO> listarUsuarios() {
        return usuarioRepository.findAllActivos()
                .stream()
                .map(u -> {
                    List<String> roles = usuarioRolRepository.findByUsuarioId(u.getId())
                            .stream()
                            .map(ur -> ur.getRol().getNombre())
                            .toList();
                    return userMapper.toResponseDTO(u, roles);
                })
                .toList();
    }

    //desctivacion de cuentas segun finalizacion de contrato
    /*@Scheduled(cron ="0 0 0 * * ?")
    public void desactivarUsuarios(){
        LocalDate fecha= LocalDate.now();

        List<UsuarioEntity> user=usuarioRepository.findByFechaRetiroBeforeAndEstado(fecha, Estado.ACTIVO);
        if(!user.isEmpty()){
            user.forEach(u -> {
                u.setEstado(Estado.INACTIVO);
            });

            usuarioRepository.saveAll(user);
        }

    }*/
}
