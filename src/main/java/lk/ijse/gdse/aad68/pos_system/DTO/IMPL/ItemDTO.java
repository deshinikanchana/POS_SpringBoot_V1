package lk.ijse.gdse.aad68.pos_system.DTO.IMPL;

import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.ItemResponse;
import lk.ijse.gdse.aad68.pos_system.DTO.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements SuperDTO, ItemResponse {
    private String itemCode;
    private String itemName;
    private int itemQty;
    private int itemPrice;
    private List<OrderDetailDTO> orderDetailList = new ArrayList<>();
}
