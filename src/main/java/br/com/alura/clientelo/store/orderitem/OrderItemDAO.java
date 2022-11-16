package br.com.alura.clientelo.store.orderitem;

import br.com.alura.clientelo.store.DAO;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class OrderItemDAO implements DAO<OrderItem> {

    private EntityManager em;

    public OrderItemDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(OrderItem orderItem) {
        em.persist(orderItem);
    }

    @Override
    public Optional<OrderItem> getById(long id) {
        return Optional.ofNullable(em.find(OrderItem.class, id));
    }

    @Override
    public void update(OrderItem orderItem) {
        em.merge(orderItem);
    }

    @Override
    public void remove(OrderItem orderItem) {
        em.remove(orderItem);
    }

    @Override
    public List<OrderItem> getAll() {
        List<OrderItem> response = em.createQuery("SELECT oi FROM OrderItem oi", OrderItem.class).getResultList();
        return response;
    }
}
