package br.edu.utfpr.api_pedidos.controllers;

import br.edu.utfpr.api_pedidos.dtos.PedidoDTO;
import br.edu.utfpr.api_pedidos.models.Pedido;
import br.edu.utfpr.api_pedidos.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoService.createPedido(pedidoDTO);
        if (pedido != null)
            return ResponseEntity.ok(pedido);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll(){
        return ResponseEntity.ok(this.pedidoService.getAll());
    }

}
