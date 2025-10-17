package com.app.comidarapida.persistence.repositories.Inventario;

import com.app.comidarapida.persistence.entities.Inventario.InventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<InventarioEntity, Long> {
}
