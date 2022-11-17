package br.com.alura.clientelo.store.category;

import br.com.alura.clientelo.store.category.vo.SalesPerCategoryVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT new br.com.alura.clientelo.store.category.vo.SalesPerCategoryVO(c.name, sum(oi.quantity), sum(oi.price)) FROM Order o JOIN OrderItem oi on oi.id = o.id JOIN Product p on p.id = oi.product.id JOIN Category c on c.id = p.category.id GROUP BY c.name ORDER BY c.name")
    List<SalesPerCategoryVO> getSalesPerCategory();

}
