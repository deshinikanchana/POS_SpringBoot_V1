package lk.ijse.gdse.aad68.pos_system.Service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.OrderResponse;
import lk.ijse.gdse.aad68.pos_system.CustomOBJ.IMPL.OrderErrorResponse;
import lk.ijse.gdse.aad68.pos_system.DAO.CustomerDAO;
import lk.ijse.gdse.aad68.pos_system.DAO.ItemDAO;
import lk.ijse.gdse.aad68.pos_system.DAO.OrderDetailsDAO;
import lk.ijse.gdse.aad68.pos_system.DAO.OrdersDAO;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.ItemDTO;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.OrderDTO;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.OrderDetailDTO;
import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.CustomerEntity;
import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.ItemEntity;
import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.OrderDetailEntity;
import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.OrderEntity;
import lk.ijse.gdse.aad68.pos_system.Exception.DataPersistFailedException;
import lk.ijse.gdse.aad68.pos_system.Exception.OrderNotFoundException;
import lk.ijse.gdse.aad68.pos_system.Service.ItemService;
import lk.ijse.gdse.aad68.pos_system.Service.OrderDetailService;
import lk.ijse.gdse.aad68.pos_system.Service.OrderService;
import lk.ijse.gdse.aad68.pos_system.Util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderDetailsDAO orderDetailsDAO;
    @Autowired
    private final CustomerDAO custDao;
    @Autowired
    private final ItemDAO itemDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveOrder(OrderDTO orderDTO) {
        var orderEntity = mapping.OrderDTOToEntity(orderDTO);
        CustomerEntity mapCust = custDao.getReferenceById(orderDTO.getCusId());
        orderEntity.setCustomer(mapCust);

        var savedOrder = ordersDAO.save(orderEntity);
        for(OrderDetailDTO od : orderDTO.getOrderDetailList()) {
            OrderDetailDTO ordDTO = new OrderDetailDTO();
            ordDTO.setOrderId(orderDTO.getOrderId());
            ordDTO.setItemCode(od.getItemCode());
            ordDTO.setBuyQty(od.getBuyQty());
            ordDTO.setItemTotal(od.getItemTotal());

            orderDetailService.saveDetails(ordDTO);

            ItemEntity itemEnt = itemDAO.getReferenceById(ordDTO.getItemCode());
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemCode(itemEnt.getItemCode());
            itemDTO.setItemName(itemEnt.getItemName());
            itemDTO.setItemQty((itemEnt.getItemQty() - ordDTO.getBuyQty()));
            itemDTO.setItemPrice(itemEnt.getItemPrice());
            itemService.updateItem(itemEnt.getItemCode(), itemDTO);
        }
        if(savedOrder == null){
            throw new DataPersistFailedException("Cannot Save Order");
        }
    }

    @Override
    public OrderResponse getSelectedOrder(String orderId) {
        if(ordersDAO.existsById((orderId))){
            OrderEntity entity = ordersDAO.findByIdWithDetails(orderId).orElse(null);
            OrderDTO orderDTO = mapping.OrderEntityToDTO(entity);
            orderDTO.setCusId(entity.getCustomer().getCusId());
            ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

            for(int i =0;i<entity.getOrderDetailsList().size();i++){
                orderDetailDTOS.add(mapping.OrderDetailEntityToDTO(entity.getOrderDetailsList().get(i)));
            }
            orderDTO.setOrderDetailList(orderDetailDTOS);
            return orderDTO;

        }else{
            return new OrderErrorResponse(0,"Order Not Found");
        }
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<OrderResponse> orderList = new ArrayList<>();
        List<OrderEntity> list1 = ordersDAO.findAll();
        for(int i =0;i<list1.size();i++){
           OrderResponse dto = getSelectedOrder(list1.get(i).getOrderId());
           orderList.add(dto);
        }

        return orderList;
    }
    @Override
    public void updateOrder(String orderId, OrderDTO orderDTO) {
        Optional<OrderEntity> tmpOrderEntity = ordersDAO.findById(orderId);
        if(!tmpOrderEntity.isPresent()){
            throw new OrderNotFoundException("Order not found");
        }else {
            tmpOrderEntity.get().setOrderDate(orderDTO.getOrderDate());
            tmpOrderEntity.get().setOrderTotal(orderDTO.getOrderTotal());
            tmpOrderEntity.get().setDiscount(orderDTO.getDiscount());
            tmpOrderEntity.get().setSubTotal(orderDTO.getSubTotal());
            tmpOrderEntity.get().setCash(orderDTO.getCash());
            tmpOrderEntity.get().setBalance(orderDTO.getBalance());

            for(OrderDetailDTO od : orderDTO.getOrderDetailList()) {
                String index = orderDetailsDAO.findIndex(orderDTO.getOrderId(), od.getItemCode());
                OrderDetailEntity currentOD = orderDetailsDAO.getReferenceById(index);
                int balance = (currentOD.getBuyQty() - od.getBuyQty());
                OrderDetailDTO ordDTO = new OrderDetailDTO(index, orderDTO.getOrderId(), od.getItemCode(), od.getBuyQty(), od.getItemTotal());
                orderDetailService.updateDetails(index,ordDTO);
                ItemEntity itemEnt = itemDAO.getReferenceById(ordDTO.getItemCode());
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setItemCode(itemEnt.getItemCode());
                itemDTO.setItemName(itemEnt.getItemName());
                itemDTO.setItemPrice(itemEnt.getItemPrice());
                itemDTO.setItemQty((itemEnt.getItemQty() + balance));

                itemService.updateItem(itemEnt.getItemCode(), itemDTO);
            }

        }
    }

}
