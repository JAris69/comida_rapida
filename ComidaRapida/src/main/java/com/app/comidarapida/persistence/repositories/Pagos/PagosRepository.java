package com.app.comidarapida.persistence.repositories.Pagos;

import com.app.comidarapida.persistence.entities.Pagos.PagosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagosRepository extends JpaRepository<PagosEntity, Long> {
}
