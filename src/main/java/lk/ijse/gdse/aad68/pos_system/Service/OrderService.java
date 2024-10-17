package lk.ijse.gdse.aad68.pos_system.Service;

import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.OrderResponse;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.OrderDTO;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDTO orderDTO);
    OrderResponse getSelectedOrder(String orderId);
    void updateOrder(String orderId, OrderDTO orderDTO);

    List<OrderResponse> getAllOrders();
}
