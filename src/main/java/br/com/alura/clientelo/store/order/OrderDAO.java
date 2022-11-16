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
        em.persist(order);
    }

    @Override
    public Optional<Order> getById(long id) {
        return Optional.ofNullable(em.find(Order.class, id));
    }

    @Override
    public void update(Order order) {
        em.merge(order);
    }

    @Override
    public void remove(Order order) {
        em.remove(order);
    }

    @Override
    public List<Order> getAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }
}
