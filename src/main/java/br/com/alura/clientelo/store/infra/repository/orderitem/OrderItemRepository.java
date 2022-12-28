package br.com.alura.clientelo.store.infra.repository.orderitem;

import br.com.alura.clientelo.store.domain.orderitem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
