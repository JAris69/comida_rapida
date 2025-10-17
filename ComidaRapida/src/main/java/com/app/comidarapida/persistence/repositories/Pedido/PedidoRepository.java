package com.app.comidarapida.persistence.repositories.Pedido;

import com.app.comidarapida.persistence.entities.Pedido.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
