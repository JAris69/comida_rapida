package com.app.comidarapida.util.mappers.user;

import com.app.comidarapida.persistence.entities.users.UsuarioEntity;
import com.app.comidarapida.web.dtos.user.UserRegisterRequestDTO;
import com.app.comidarapida.web.dtos.user.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UsuarioEntity toEntity(UserRegisterRequestDTO dto);

    @Mapping(target = "roles", ignore = true)
    @Mapping(source = "personal.nombre_completo", target = "nombreCompleto")
    @Mapping(source = "personal.nro_identidad", target = "ci")
    @Mapping(source = "personal.telefono_movil", target = "telefono")
    UserResponseDTO toResponseDTO(UsuarioEntity entity);

    default UserResponseDTO toResponseDTO(UsuarioEntity entity, List<String> roles) {
        UserResponseDTO dto = toResponseDTO(entity);
        dto.setRoles(roles);
        return dto;
    }
}
