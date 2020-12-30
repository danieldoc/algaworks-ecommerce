package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Atributo;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class ElementCollectionTest extends EntityManagerTest {

    @Test
    public void aplicarTags() {
        entityManager.getTransaction().begin();

        final Produto produto = entityManager.find(Produto.class, 1);
        produto.setTags(Arrays.asList("ebook", "livro-digital"));

        entityManager.getTransaction().commit();

        entityManager.clear();

        final Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(produtoVerificacao.getTags().isEmpty());
    }

    @Test
    public void aplicarAtributos() {
        entityManager.getTransaction().begin();

        final Produto produto = entityManager.find(Produto.class, 1);
        produto.setAtributos(Collections.singletonList(new Atributo("tela", "320x600")));

        entityManager.getTransaction().commit();

        entityManager.clear();

        final Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(produtoVerificacao.getAtributos().isEmpty());
    }
}
