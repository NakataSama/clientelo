package br.com.alura.clientelo.store.product;

import br.com.alura.clientelo.store.product.vo.TopSellingProductsVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new br.com.alura.clientelo.store.product.vo.TopSellingProductsVO(p.category.name, p.name, sum(oi.quantity) as quantity) FROM Product p JOIN OrderItem oi on oi.product.id = p.id GROUP BY p.id ORDER BY quantity DESC")
    List<TopSellingProductsVO> getTopSellingProducts();
}
