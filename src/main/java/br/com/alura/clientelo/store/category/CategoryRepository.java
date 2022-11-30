package br.com.alura.clientelo.store.category;

import br.com.alura.clientelo.store.category.vo.SalesPerCategoryVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    Optional<Category> findById(Long id);

    @Query("SELECT new br.com.alura.clientelo.store.category.vo.SalesPerCategoryVO(c.name, sum(oi.quantity), sum(oi.price)) FROM Order o JOIN OrderItem oi on oi.id = o.id JOIN Product p on p.id = oi.product.id JOIN Category c on c.id = p.category.id GROUP BY c.name ORDER BY c.name")
    List<SalesPerCategoryVO> getSalesPerCategory();

}
