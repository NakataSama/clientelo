package br.com.alura.clientelo.store.customer;

import br.com.alura.clientelo.store.core.entity.customer.Customer;
import br.com.alura.clientelo.store.customer.vo.LoyalCustomersVO;
import br.com.alura.clientelo.store.customer.vo.MostProfitableCustomersVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT new br.com.alura.clientelo.store.customer.vo.MostProfitableCustomersVO(c.name, count(o.id), sum(oi.price) AS total) FROM Order o JOIN Customer c on c.id = o.customer.id JOIN OrderItem oi on oi.order.id = o.id GROUP BY c.name ORDER BY total DESC")
    List<MostProfitableCustomersVO> getMostProfitableCustomers();

    @Query("SELECT new br.com.alura.clientelo.store.customer.vo.LoyalCustomersVO(c.name, count(o.id) as numberOfOrders) FROM Customer c JOIN Order o on o.customer.id = c.id GROUP BY c.id ORDER BY numberOfOrders DESC")
    List<LoyalCustomersVO> getLoyalCustomers();

}
