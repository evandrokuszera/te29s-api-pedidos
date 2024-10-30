package br.edu.utfpr.api_pedidos.services;

import br.edu.utfpr.api_pedidos.dtos.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="api-produto", url="localhost:8080")
public interface ProdutoFeignClient {

    @GetMapping("/produtos/{id}")
    ProdutoDTO getProdutoById(@PathVariable Long id);

    @PostMapping("/produtos/estoque/baixa")
    Boolean atualizarEstoque(@RequestBody List<ProdutoDTO> itens);

}
