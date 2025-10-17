package com.app.comidarapida.service.interfaces.Producto;

import com.app.comidarapida.web.dtos.ProductoDTO.ProductoRequestDTO;
import com.app.comidarapida.web.dtos.ProductoDTO.ProductoResponseDTO;

public interface ProductoInterface {
    ProductoResponseDTO saveProducto(ProductoRequestDTO dto);
}
