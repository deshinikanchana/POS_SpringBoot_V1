package lk.ijse.gdse.aad68.pos_system.Service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.OrderDetailResponse;
import lk.ijse.gdse.aad68.pos_system.CustomOBJ.IMPL.OrderDetailErrorResponse;
import lk.ijse.gdse.aad68.pos_system.DAO.CustomerDAO;
import lk.ijse.gdse.aad68.pos_system.DAO.ItemDAO;
import lk.ijse.gdse.aad68.pos_system.DAO.OrderDetailsDAO;
import lk.ijse.gdse.aad68.pos_system.DAO.OrdersDAO;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.OrderDetailDTO;
import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.OrderDetailEntity;
import lk.ijse.gdse.aad68.pos_system.Exception.DataPersistFailedException;
import lk.ijse.gdse.aad68.pos_system.Exception.OrderDetailNotFoundException;
import lk.ijse.gdse.aad68.pos_system.Service.CustomerService;
import lk.ijse.gdse.aad68.pos_system.Service.ItemService;
import lk.ijse.gdse.aad68.pos_system.Service.OrderDetailService;
import lk.ijse.gdse.aad68.pos_system.Util.AppUtil;
import lk.ijse.gdse.aad68.pos_system.Util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDetailServiceIMPL implements OrderDetailService {
    @Autowired
    private OrderDetailsDAO orderDetailsDAO;
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveDetails(OrderDetailDTO detailsDTO) {
        String maxIndex = AppUtil.createOrderDetailIndex(orderDetailsDAO.findMaxOrderDetailIndex());
        if (maxIndex == null) {
            detailsDTO.setOrderDetailIndex("OD-0001");
        }else{
            detailsDTO.setOrderDetailIndex(maxIndex);
        }

        var dataEntity = new OrderDetailEntity();
        dataEntity.setOrderDetailIndex(detailsDTO.getOrderDetailIndex());
        dataEntity.setItem(itemDAO.getReferenceById(detailsDTO.getItemCode()));
        dataEntity.setOrder(ordersDAO.getReferenceById(detailsDTO.getOrderId()));
        dataEntity.setItemTotal(detailsDTO.getItemTotal());
        dataEntity.setBuyQty(detailsDTO.getBuyQty());

        var savedData = orderDetailsDAO.save(dataEntity);
        if(savedData == null){
            throw new DataPersistFailedException("Cannot Save Order Details");
        }
    }

    @Override
    public void updateDetails(String orderDetailIndex, OrderDetailDTO detailsDTO) {
        Optional<OrderDetailEntity> tmpDataEntity = orderDetailsDAO.findById(orderDetailIndex);
        if(!tmpDataEntity.isPresent()){
            throw new OrderDetailNotFoundException("Order Detail not found");
        }else {
            tmpDataEntity.get().setBuyQty(detailsDTO.getBuyQty());
            tmpDataEntity.get().setItemTotal(detailsDTO.getItemTotal());
        }
    }

    @Override
    public void deleteDetails(String orderDetailIndex) {
        Optional<OrderDetailEntity> findId = orderDetailsDAO.findById(orderDetailIndex);
        if(!findId.isPresent()){
            throw new OrderDetailNotFoundException("Order Detail not found");
        }else {
            orderDetailsDAO.deleteById(orderDetailIndex);
        }
    }

    @Override
    public OrderDetailResponse getSelectedDetail(String orderDetailIndex) {
        if(orderDetailsDAO.existsById(orderDetailIndex)){
            OrderDetailEntity entityById = orderDetailsDAO.getReferenceById(orderDetailIndex);
            return mapping.OrderDetailEntityToDTO(entityById);
        }else {
            return new OrderDetailErrorResponse(0, "OrderDetails not found");
        }
    }

    @Override
    public List<OrderDetailDTO> getAllDetails() {
        return mapping.OrderDetailEntityListToDTOList(orderDetailsDAO.findAll());
    }
}
