package br.com.alura.clientelo.store.product;

import br.com.alura.clientelo.store.JPADao;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class ProductDao implements JPADao<Product> {

    private EntityManager em;

    public ProductDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Product> getById(long id) {
        em.getTransaction().begin();
        Optional<Product> response = Optional.of(em.find(Product.class, id));
        em.getTransaction().commit();
        em.close();

        return response;
    }

    @Override
    public void update(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Product product) {
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Product> getAll() {
        em.getTransaction().begin();
        List<Product> response = em.createQuery("SELECT p FROM product p", Product.class).getResultList();
        em.getTransaction().commit();
        em.close();

        return response;
    }
}
