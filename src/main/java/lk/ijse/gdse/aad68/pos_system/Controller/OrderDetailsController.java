package lk.ijse.gdse.aad68.pos_system.Controller;

import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.OrderDetailResponse;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.OrderDetailDTO;
import lk.ijse.gdse.aad68.pos_system.Service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orderDetails")
@RequiredArgsConstructor
@CrossOrigin
public class OrderDetailsController {
    @Autowired
    private final OrderDetailService orderDetailService;

    @GetMapping(value = "/{orderDetailIndex}",produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDetailResponse getSelectedOrderDetail(@PathVariable("orderDetailIndex") String index){
        return orderDetailService.getSelectedDetail(index);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailDTO> getOrderDetailsByOrderId(@RequestParam(name = "orderId") String orderId) {
        if (orderId == null || orderId.isEmpty()) {
            throw new IllegalArgumentException("Order ID must not be null or empty.");
        }
        return orderDetailService.getOrderDetailsByOrderId(orderId);
    }
    @GetMapping( value = "allDetails",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailDTO> getAllDetails(){
        return orderDetailService.getAllDetails();
    }

}
