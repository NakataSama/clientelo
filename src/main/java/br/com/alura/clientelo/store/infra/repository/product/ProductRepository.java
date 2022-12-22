package br.com.alura.clientelo.store.infra.repository.product;

import br.com.alura.clientelo.store.core.entity.product.Product;
import br.com.alura.clientelo.store.infra.repository.product.vo.TopSellingProductsVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    @Query("SELECT new br.com.alura.clientelo.store.infra.repository.product.vo.TopSellingProductsVO(p.category.name, p.name, sum(oi.quantity) as quantity) FROM Product p JOIN OrderItem oi on oi.product.id = p.id GROUP BY p.id ORDER BY quantity DESC")
    List<TopSellingProductsVO> getTopSellingProducts();
}