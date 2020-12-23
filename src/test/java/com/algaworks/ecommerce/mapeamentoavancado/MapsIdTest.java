package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

public class MapsIdTest extends EntityManagerTest {

    @Test
    public void inserirPagamento() {
        final Pedido pedido = entityManager.find(Pedido.class, 1);

        final NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setXml("<xml/>");

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        final NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, 1);
        Assert.assertNotNull(notaFiscalVerificacao);
        Assert.assertEquals(pedido.getId(), notaFiscal.getId());
    }

    @Test
    public void inserirItemPedido() {
        final Cliente cliente = entityManager.find(Cliente.class, 1);
        final Produto produto = entityManager.find(Produto.class, 1);

        final Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(produto.getPreco());

        final ItemPedido item = new ItemPedido();
        item.setId(new ItemPedidoId());
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setPrecoProduto(produto.getPreco());
        item.setQuantidade(1);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.persist(item);
        entityManager.getTransaction().commit();

        entityManager.clear();

        final ItemPedido itemVerificacao = entityManager.find(ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));
        Assert.assertNotNull(itemVerificacao);
    }
}
