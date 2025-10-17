package com.app.comidarapida.persistence.repositories.Producto;

import com.app.comidarapida.persistence.entities.Producto.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
