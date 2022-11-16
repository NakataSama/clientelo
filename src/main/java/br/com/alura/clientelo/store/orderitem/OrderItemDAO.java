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
        em.getTransaction().begin();
        em.persist(orderItem);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<OrderItem> getById(long id) {
        em.getTransaction().begin();
        Optional<OrderItem> response = Optional.of(em.find(OrderItem.class, id));
        em.getTransaction().commit();
        em.close();

        return response;
    }

    @Override
    public void update(OrderItem orderItem) {
        em.getTransaction().begin();
        em.merge(orderItem);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(OrderItem orderItem) {
        em.getTransaction().begin();
        em.remove(orderItem);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<OrderItem> getAll() {
        em.getTransaction().begin();
        List<OrderItem> response = em.createQuery("SELECT oi FROM orderItem oi", OrderItem.class).getResultList();
        em.getTransaction().commit();
        em.close();

        return response;
    }
}
