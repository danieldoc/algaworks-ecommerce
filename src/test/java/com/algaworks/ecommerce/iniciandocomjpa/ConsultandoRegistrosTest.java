package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.model.Produto;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConsultandoRegistrosTest {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeClass
    public static void beforeClass() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        entityManagerFactory.close();
    }

    @Before
    public void setUp() throws Exception {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
    }

    @Test
    public void buscarPorIdentificador() {
        Produto produto = entityManager.find(Produto.class, 1);
//        Produto produto = entityManager.getReference(Produto.class, 1);

        Assert.assertNotNull(produto);
        Assert.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void atualizarReferencia() {
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setNome("Microfone Samson");

        entityManager.refresh(produto);

        Assert.assertEquals("Kindle", produto.getNome());
    }
}
