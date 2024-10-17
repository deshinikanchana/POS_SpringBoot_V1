package lk.ijse.gdse.aad68.pos_system.Controller;

import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.OrderResponse;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.ItemDTO;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.OrderDTO;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.OrderDetailDTO;
import lk.ijse.gdse.aad68.pos_system.Exception.DataPersistFailedException;
import lk.ijse.gdse.aad68.pos_system.Exception.OrderNotFoundException;
import lk.ijse.gdse.aad68.pos_system.Service.CustomerService;
import lk.ijse.gdse.aad68.pos_system.Service.ItemService;
import lk.ijse.gdse.aad68.pos_system.Service.OrderDetailService;
import lk.ijse.gdse.aad68.pos_system.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin
public class OrdersController {
    @Autowired
    private final OrderService orderService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveOrder(@RequestBody OrderDTO order){
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try {
                orderService.saveOrder(order);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{orderId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder(@PathVariable("orderId") String orderId, @RequestBody OrderDTO orderDTO) {
        try {
            if (orderDTO == null && (orderId == null || orderDTO.equals(""))){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            orderService.updateOrder(orderId, orderDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{orderId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse getSelectedOrder(@PathVariable("orderId") String orderId){
        return orderService.getSelectedOrder(orderId);
    }

    @GetMapping(value = "allOrders",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();
    }

}
