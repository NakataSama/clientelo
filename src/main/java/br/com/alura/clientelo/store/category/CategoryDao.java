package br.com.alura.clientelo.store.category;

import br.com.alura.clientelo.store.JPADao;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class CategoryDao implements JPADao<Customer> {

    private EntityManager em;

    public CategoryDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Customer category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Customer> getById(long id) {
        em.getTransaction().begin();
        Optional<Customer> response = Optional.of(em.find(Customer.class, id));
        em.getTransaction().commit();
        em.close();

        return response;
    }

    @Override
    public void update(Customer category) {
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Customer category) {
        em.getTransaction().begin();
        em.remove(category);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<List<Customer>> getAll() {
        em.getTransaction().begin();
        Optional<List<Customer>> response = Optional.of(em.createQuery("SELECT c FROM category c", Customer.class).getResultList());
        em.getTransaction().commit();
        em.close();

        return response;
    }
}
