package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void atualizarObjeto() {
        final Produto produto = new Produto();

        produto.setId(1);
        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheça o novo Kindle.");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear(); // Limpa entityManager

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
        Assert.assertEquals("Kindle Paperwhite", produtoVerificacao.getNome());
    }

    @Test
    public void removerObjeto() {
        final Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificado = entityManager.find(Produto.class, 3);
        Assert.assertNull(produtoVerificado);
    }

    @Test
    public void inserirOPrimeiroObjeto() {
        Produto produto = new Produto();
        produto.setId(2);
        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para suas fotos.");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
//        entityManager.flush(); Obriga a execucao do entityManager em memoria no banco
        entityManager.getTransaction().commit();

        entityManager.clear(); // Limpa entityManager

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
    }

//    @Test
//    public void abrirEFecharATransacao() {
//        Produto produto = new Produto();
//
//        entityManager.getTransaction().begin();
//
//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);
//
//        entityManager.getTransaction().commit();
//    }
}
