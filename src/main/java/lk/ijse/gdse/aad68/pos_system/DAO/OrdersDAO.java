package lk.ijse.gdse.aad68.pos_system.DAO;

import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersDAO extends JpaRepository<OrderEntity,String> {

    @Query("SELECT o FROM OrderEntity o LEFT JOIN FETCH o.orderDetailsList WHERE o.orderId = :orderId")
    Optional<OrderEntity> findByIdWithDetails(@Param("orderId") String orderId);
}
