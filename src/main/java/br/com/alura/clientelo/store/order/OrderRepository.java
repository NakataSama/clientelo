package br.com.alura.clientelo.store.order;

import br.com.alura.clientelo.store.core.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long id);
    Optional<Order> findById(Long id);
    List<Order> findByOrderByDateDescCustomerNameAsc();
}
