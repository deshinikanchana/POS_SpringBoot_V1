package lk.ijse.gdse.aad68.pos_system.DTO.IMPL;

import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.OrderDetailResponse;
import lk.ijse.gdse.aad68.pos_system.DTO.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO implements SuperDTO, OrderDetailResponse {
    private String orderDetailIndex;
    private String orderId;
    private String itemCode;
    private int buyQty;
    private int itemTotal;
}
