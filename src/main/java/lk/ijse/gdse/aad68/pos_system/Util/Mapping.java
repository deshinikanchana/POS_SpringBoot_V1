package lk.ijse.gdse.aad68.pos_system.Util;

import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.CustomerDTO;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.ItemDTO;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.OrderDTO;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.OrderDetailDTO;
import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.CustomerEntity;
import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.ItemEntity;
import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.OrderDetailEntity;
import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.OrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO CustEntityToDTO(CustomerEntity entity) {
        return modelMapper.map(entity, CustomerDTO.class);
    }
    public CustomerEntity CustDTOToEntity(CustomerDTO dto) {
        return modelMapper.map(dto, CustomerEntity.class);
    }
    public List<CustomerDTO> CustEntityListToDTOList(List<CustomerEntity> customers) {
        return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {}.getType());
    }

    public ItemDTO ItemEntityToDTO(ItemEntity entity) {
        return modelMapper.map(entity, ItemDTO.class);
    }
    public ItemEntity ItemDTOToEntity(ItemDTO dto) {
        return modelMapper.map(dto, ItemEntity.class);
    }
    public List<ItemDTO> ItemEntityListToDTOList(List<ItemEntity> items) {
        return modelMapper.map(items, new TypeToken<List<ItemDTO>>() {}.getType());
    }

    public OrderDTO OrderEntityToDTO(OrderEntity entity) {
        return modelMapper.map(entity, OrderDTO.class);
    }
    public OrderEntity OrderDTOToEntity(OrderDTO dto) {
        return modelMapper.map(dto, OrderEntity.class);
    }
    public List<OrderDTO> OrderEntityListToDTOList(List<OrderEntity> orders) {
        return modelMapper.map(orders, new TypeToken<List<OrderDTO>>() {}.getType());
    }

    public OrderDetailDTO OrderDetailEntityToDTO(OrderDetailEntity entity) {
        return modelMapper.map(entity, OrderDetailDTO.class);
    }
    public OrderDetailEntity OrderDetailDTOToEntity(OrderDetailDTO dto) {
        return modelMapper.map(dto, OrderDetailEntity.class);
    }
    public List<OrderDetailDTO> OrderDetailEntityListToDTOList(List<OrderDetailEntity> orderDetails) {
        return modelMapper.map(orderDetails, new TypeToken<List<OrderDetailDTO>>() {}.getType());
    }
}
