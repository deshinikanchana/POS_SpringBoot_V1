package lk.ijse.gdse.aad68.pos_system.DTO.IMPL;

import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.OrderResponse;
import lk.ijse.gdse.aad68.pos_system.DTO.SuperDTO;
import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.OrderDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements SuperDTO, OrderResponse {
    private String orderId;
    private String cusId;
    private String orderDate;
    private int orderTotal;
    private int discount;
    private int subTotal;
    private int cash;
    private int balance;
    private List<OrderDetailDTO> orderDetailList = new ArrayList<>();
}
