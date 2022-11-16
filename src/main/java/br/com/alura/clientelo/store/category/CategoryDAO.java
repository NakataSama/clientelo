package br.com.alura.clientelo.store.category;

import br.com.alura.clientelo.store.DAO;
import br.com.alura.clientelo.store.category.vo.SalesPerCategoryVO;
import br.com.alura.clientelo.store.order.Order;
import br.com.alura.clientelo.store.orderitem.OrderItem;
import br.com.alura.clientelo.store.product.Product;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class CategoryDAO implements DAO<Category> {

    private EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Category category) {
        em.persist(category);
    }

    @Override
    public Optional<Category> getById(long id) {
        return Optional.ofNullable(em.find(Category.class, id));
    }

    @Override
    public void update(Category category) {
        em.merge(category);
    }

    @Override
    public void remove(Category category) {
        em.remove(category);
    }

    @Override
    public List<Category> getAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    public List<SalesPerCategoryVO> getSalesPerCategory() {
        String query = "SELECT new br.com.alura.clientelo.store.category.vo.SalesPerCategoryVO(c.name, sum(oi.quantity), sum(oi.price)) FROM "+ Order.class.getSimpleName() + " o " +
                "JOIN " + OrderItem.class.getSimpleName() + " oi on oi.id = o.id " +
                "JOIN " + Product.class.getSimpleName() + " p on p.id = oi.product.id " +
                "JOIN " + Category.class.getSimpleName() + " c on c.id = p.category.id " +
                "GROUP BY c.name " +
                "ORDER BY c.name";


        return em.createQuery(query, SalesPerCategoryVO.class).getResultList();
    }
}
