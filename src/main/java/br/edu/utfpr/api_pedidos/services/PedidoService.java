package br.edu.utfpr.api_pedidos.services;

import br.edu.utfpr.api_pedidos.dtos.ItemPedidoDTO;
import br.edu.utfpr.api_pedidos.dtos.PedidoDTO;
import br.edu.utfpr.api_pedidos.dtos.ProdutoDTO;
import br.edu.utfpr.api_pedidos.models.ItemPedido;
import br.edu.utfpr.api_pedidos.models.Pedido;
import br.edu.utfpr.api_pedidos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    // injetando o repository para persistir pedidos no H2
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoFeignClient produtoFeignClient;

    // Método para adicionar um Pedido no H2
    public Pedido createPedido(PedidoDTO pedidoDTO){
        // Cria pedido
        Pedido pedido = new Pedido();
        pedido.setCustomerName(pedidoDTO.customerName());
        pedido.setStatus("PENDENTE");

        // Cria lista de itens do pedido
        List<ItemPedido> itens = new ArrayList<>();

        // Processa a lista de itens DTO
        for (ItemPedidoDTO itemDTO : pedidoDTO.items()){

            // Chama a api-produto
            ProdutoDTO produtoEstoque = produtoFeignClient.getProdutoById(itemDTO.productId());

            // O produto não existe, interrompe a criação do pedido
            if (produtoEstoque == null) return null;

            // Não há estoque suficiente, interrompe a criação do pedido.
            if (produtoEstoque.quantity() - itemDTO.quantity() < 0){
                return null;
            }

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProductId(itemDTO.productId());
            itemPedido.setQuantity(itemDTO.quantity());
            itemPedido.setPrice(produtoEstoque.price());
            itemPedido.setPedido(pedido); // Relaciona o ItemPedido com o Pedido
            itens.add(itemPedido);
        }

        // Relaciona o Pedido com os ItemPedido
        pedido.setItems(itens);

        return this.pedidoRepository.save(pedido);
    }

    // Recuperar todos os pedidos
    public List<Pedido> getAll(){
        return this.pedidoRepository.findAll();
    }

}
