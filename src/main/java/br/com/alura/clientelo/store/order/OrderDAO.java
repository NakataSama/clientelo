package br.com.alura.clientelo.store.order;

import br.com.alura.clientelo.store.DAO;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class OrderDAO implements DAO<Order> {

    private EntityManager em;

    public OrderDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Order order) {
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Order> getById(long id) {
        em.getTransaction().begin();
        Optional<Order> response = Optional.of(em.find(Order.class, id));
        em.getTransaction().commit();
        em.close();

        return response;
    }

    @Override
    public void update(Order order) {
        em.getTransaction().begin();
        em.merge(order);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Order order) {
        em.getTransaction().begin();
        em.remove(order);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Order> getAll() {
        em.getTransaction().begin();
        List<Order> response = em.createQuery("SELECT o FROM order o", Order.class).getResultList();
        em.getTransaction().commit();
        em.close();

        return response;
    }
}
