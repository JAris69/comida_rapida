package com.app.comidarapida.persistence.repositories.rol;

import com.app.comidarapida.persistence.entities.rol.RolEntity;
import com.app.comidarapida.persistence.entities.rol.RolPermisoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermisoEntity, Integer> {
    List<RolPermisoEntity> findByRol(RolEntity rol);
    //void deleteByRolEntityAndPermisoEntityIn(RolEntity rolEntity, List<PermisoEntity> permisoEntities);
}
