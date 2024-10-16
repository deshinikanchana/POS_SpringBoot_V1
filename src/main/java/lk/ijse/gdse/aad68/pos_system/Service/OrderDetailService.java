package lk.ijse.gdse.aad68.pos_system.Service;

import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.OrderDetailResponse;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {

    void saveDetails(OrderDetailDTO detailsDTO);
    void updateDetails(String orderDetailIndex,OrderDetailDTO detailsDTO);
    void deleteDetails(String orderDetailIndex);
    OrderDetailResponse getSelectedDetail(String orderDetailIndex);
    List<OrderDetailDTO> getAllDetails ();


}
