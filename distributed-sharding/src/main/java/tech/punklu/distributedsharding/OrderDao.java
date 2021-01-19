package tech.punklu.distributedsharding;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderEntity,Long> {

    OrderEntity findByOrderId(Long orderId);
}
