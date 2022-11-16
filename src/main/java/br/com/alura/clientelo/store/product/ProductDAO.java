package br.com.alura.clientelo.store.product;

import br.com.alura.clientelo.store.DAO;
import br.com.alura.clientelo.store.orderitem.OrderItem;
import br.com.alura.clientelo.store.product.vo.TopSellingProductsVO;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class ProductDAO implements DAO<Product> {

    private EntityManager em;

    public ProductDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Product product) {
        em.persist(product);
    }

    @Override
    public Optional<Product> getById(long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    @Override
    public void update(Product product) {
        em.merge(product);
    }

    @Override
    public void remove(Product product) {
        em.remove(product);
    }

    @Override
    public List<Product> getAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public List<TopSellingProductsVO> getTopSellingProducts() {
        String query = "SELECT new br.com.alura.clientelo.store.product.vo.TopSellingProductsVO(p.category.name, p.name, sum(oi.quantity) as quantity) FROM "+ Product.class.getSimpleName() + " p " +
                "JOIN " + OrderItem.class.getSimpleName() + " oi on oi.product.id = p.id " +
                "GROUP BY p.id " +
                "ORDER BY quantity DESC";

        return em.createQuery(query, TopSellingProductsVO.class).getResultList();
    }
}
