package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class ChaveCompostaTest extends EntityManagerTest {

    @Test
    public void salvarItem() {
        entityManager.getTransaction().begin();

        final Cliente cliente = entityManager.find(Cliente.class, 1);
        final Produto produto = entityManager.find(Produto.class, 1);

        final Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(produto.getPreco());

        entityManager.persist(pedido);

        entityManager.flush();

        final ItemPedido item = new ItemPedido();
//        item.setPedidoId(pedido.getId()); Com @IdClass
//        item.setProdutoId(produto.getId());
        item.setId(new ItemPedidoId(pedido.getId(), produto.getId()));
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setPrecoProduto(produto.getPreco());
        item.setQuantidade(1);

        entityManager.persist(item);

        entityManager.getTransaction().commit();

        entityManager.clear();

        final Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertFalse(pedidoVerificacao.getItens().isEmpty());
    }

    @Test
    public void buscarItem() {
        final ItemPedido item = entityManager.find(ItemPedido.class, new ItemPedidoId(1, 1));
        Assert.assertNotNull(item);
    }
}
