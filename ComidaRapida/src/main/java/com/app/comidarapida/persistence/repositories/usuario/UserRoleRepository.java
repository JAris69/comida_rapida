package com.app.comidarapida.persistence.repositories.usuario;

import com.app.comidarapida.persistence.entities.users.UsuarioRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UsuarioRolEntity, Long> {

    List<UsuarioRolEntity> findByUsuarioId(Long usuarioId);

    @Query("SELECT ur FROM UsuarioRolEntity ur " +
            "JOIN FETCH ur.rol r " +
            "JOIN FETCH r.permisos rp " +
            "JOIN FETCH rp.permiso p " +
            "WHERE ur.usuario.id = :usuarioId")
    List<UsuarioRolEntity> findRolesConPermisosPorUsuario(@Param("usuarioId") Long usuarioId);

}
