package br.edu.utfpr.api_pedidos.repositories;

import br.edu.utfpr.api_pedidos.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}
