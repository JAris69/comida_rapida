package com.app.comidarapida.persistence.repositories.usuario;

import com.app.comidarapida.persistence.entities.users.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsuarioEntity, Long> {
    //Optional<Usuario> findByCorreo(String correo);
    //Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.correo = :param OR u.nombreUsuario = :param")
    Optional<UsuarioEntity> findByCorreoOrNombreUsuario(@Param("param") String param);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.estadoLog <> 'BAJA'")
    List<UsuarioEntity> findAllActivos(); // solo devuelve los usuarios ALTA

    //List<UsuarioEntity> findByFechaRetiroBeforeAndEstado(LocalDate fecha, Estado estado);

}
