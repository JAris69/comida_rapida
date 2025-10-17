package com.app.comidarapida.web.controller.Pedido;

import com.app.comidarapida.enums.EstadoPedido;
import com.app.comidarapida.service.implementation.Pedidos.PedidoImpl;
import com.app.comidarapida.web.dtos.PedidoDTO.PedidoRequestDTO;
import com.app.comidarapida.web.dtos.PedidoDTO.PedidoResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private final PedidoImpl pedido;

    public PedidoController(PedidoImpl pedido) {
        this.pedido = pedido;
    }

    @PostMapping
    public PedidoResponseDTO crearNuevoPedido(@RequestBody PedidoRequestDTO dto){
        return pedido.savePedido(dto);
    }

    @PatchMapping("/{id}/estado")
    public PedidoResponseDTO cambiaEstadoPedido(@PathVariable Long id, @RequestParam EstadoPedido estado){
        return pedido.cambiarEstado(id, estado);
    }

    @PutMapping("/update/{id}")
    public PedidoResponseDTO updatePedido(@PathVariable Long id, @RequestBody PedidoRequestDTO dto) {
        return pedido.updatePedido(id, dto)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    @GetMapping
    public List<PedidoResponseDTO> listarPersonalTodos() {
        return pedido.listaPedidos();
    }


}
