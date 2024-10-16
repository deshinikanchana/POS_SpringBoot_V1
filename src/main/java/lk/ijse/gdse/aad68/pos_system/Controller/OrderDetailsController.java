package lk.ijse.gdse.aad68.pos_system.Controller;

import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.OrderDetailResponse;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.OrderDetailDTO;
import lk.ijse.gdse.aad68.pos_system.Exception.DataPersistFailedException;
import lk.ijse.gdse.aad68.pos_system.Exception.OrderDetailNotFoundException;
import lk.ijse.gdse.aad68.pos_system.Service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @GetMapping( value = "allDetails",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailDTO> getAllDetails(){
        return orderDetailService.getAllDetails();
    }

}
