package lk.ijse.gdse.aad68.pos_system.DAO;

import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsDAO extends JpaRepository<OrderDetailEntity,String> {
        @Query("SELECT MAX(orderDetailIndex) FROM OrderDetailEntity")
        String findMaxOrderDetailIndex();

        @Query("SELECT o.orderDetailIndex FROM OrderDetailEntity o WHERE o.order.orderId = :orderId AND o.item.itemCode = :itemCode")
        String findIndex(@Param("orderId") String orderId, @Param("itemCode") String itemCode);

        @Query("SELECT od FROM OrderDetailEntity od WHERE od.order.orderId = :orderId")
        List<OrderDetailEntity> findByOrderId(@Param("orderId") String orderId);

}
