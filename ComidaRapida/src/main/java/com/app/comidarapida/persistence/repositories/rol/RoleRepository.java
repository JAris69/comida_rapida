package com.app.comidarapida.persistence.repositories.rol;

import com.app.comidarapida.persistence.entities.rol.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RolEntity, Integer> {
}
