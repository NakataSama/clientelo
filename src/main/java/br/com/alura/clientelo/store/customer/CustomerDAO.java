package br.com.alura.clientelo.store.customer;

import br.com.alura.clientelo.store.DAO;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class CustomerDAO implements DAO<Customer> {

    private EntityManager em;

    public CustomerDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Customer customer) {
        em.getTransaction().begin();
        em.persist(customer);
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
    public void update(Customer customer) {
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Customer customer) {
        em.getTransaction().begin();
        em.remove(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Customer> getAll() {
        em.getTransaction().begin();
        List<Customer> response = em.createQuery("SELECT c FROM customer c", Customer.class).getResultList();
        em.getTransaction().commit();
        em.close();

        return response;
    }
}