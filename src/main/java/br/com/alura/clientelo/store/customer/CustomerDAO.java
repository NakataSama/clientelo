package br.com.alura.clientelo.store.customer;

import br.com.alura.clientelo.store.DAO;
import br.com.alura.clientelo.store.customer.vo.LoyalCustomersVO;
import br.com.alura.clientelo.store.customer.vo.MostProfitableCustomersVO;
import br.com.alura.clientelo.store.order.Order;
import br.com.alura.clientelo.store.orderitem.OrderItem;
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
        em.persist(customer);
    }

    @Override
    public Optional<Customer> getById(long id) {
        return Optional.ofNullable(em.find(Customer.class, id));
    }

    @Override
    public void update(Customer customer) {
        em.merge(customer);
    }

    @Override
    public void remove(Customer customer) {
        em.remove(customer);
    }

    @Override
    public List<Customer> getAll() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    public List<MostProfitableCustomersVO> getMostProfitableCustomers() {
        String query = "SELECT new br.com.alura.clientelo.store.customer.vo.MostProfitableCustomersVO(c.name, count(o.id), sum(oi.price) AS total) FROM "+ Order.class.getSimpleName() + " o " +
                "JOIN " + Customer.class.getSimpleName() + " c on c.id = o.customer.id " +
                "JOIN " + OrderItem.class.getSimpleName() + " oi on oi.order.id = o.id " +
                "GROUP BY c.name " +
                "ORDER BY total DESC";

        return em.createQuery(query, MostProfitableCustomersVO.class).getResultList();
    }

    public List<LoyalCustomersVO> getLoyalCustomers() {
        String query = "SELECT new br.com.alura.clientelo.store.customer.vo.LoyalCustomersVO(c.name, count(o.id) as numberOfOrders) FROM "+ Customer.class.getSimpleName() + " c " +
                "JOIN " + Order.class.getSimpleName() + " o on o.customer.id = c.id " +
                "GROUP BY c.id " +
                "ORDER BY numberOfOrders DESC";

        return em.createQuery(query, LoyalCustomersVO.class).getResultList();
    }
}
