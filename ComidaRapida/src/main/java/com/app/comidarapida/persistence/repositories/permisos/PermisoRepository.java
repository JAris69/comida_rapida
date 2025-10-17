package com.app.comidarapida.persistence.repositories.permisos;

import com.app.comidarapida.persistence.entities.permisos.PermisoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisoRepository extends JpaRepository<PermisoEntity, Integer> {

    List<PermisoEntity> findBySubmoduloEntity_Id(Integer submoduloId);
}
