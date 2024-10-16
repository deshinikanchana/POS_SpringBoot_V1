package lk.ijse.gdse.aad68.pos_system.Service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.ItemResponse;
import lk.ijse.gdse.aad68.pos_system.CustomOBJ.IMPL.ItemErrorResponse;
import lk.ijse.gdse.aad68.pos_system.DAO.ItemDAO;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.ItemDTO;
import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.ItemEntity;
import lk.ijse.gdse.aad68.pos_system.Exception.DataPersistFailedException;
import lk.ijse.gdse.aad68.pos_system.Exception.ItemNotFoundException;
import lk.ijse.gdse.aad68.pos_system.Service.ItemService;
import lk.ijse.gdse.aad68.pos_system.Util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveItem(ItemDTO itemDTO) {
        var CustEntity = mapping.ItemDTOToEntity(itemDTO);
        var savedItem = itemDAO.save(CustEntity);
        if(savedItem == null){
            throw new DataPersistFailedException("Cannot Save Item");
        }
    }

    @Override
    public void updateItem(String itemCode,ItemDTO itemDTO) {
        Optional<ItemEntity> tmpItemEntity = itemDAO.findById(itemCode);
        if(!tmpItemEntity.isPresent()){
            throw new ItemNotFoundException("Item not found");
        }else {
            tmpItemEntity.get().setItemName(itemDTO.getItemName());
            tmpItemEntity.get().setItemQty(itemDTO.getItemQty());
            tmpItemEntity.get().setItemPrice(itemDTO.getItemPrice());
        }
    }

    @Override
    public void deleteItem(String itemCode) {
        Optional<ItemEntity> findId = itemDAO.findById(itemCode);
        if(!findId.isPresent()){
            throw new ItemNotFoundException("Item not found");
        }else {
            itemDAO.deleteById(itemCode);
        }
    }

    @Override
    public ItemResponse getSelectedItem(String itemCode) {
        if(itemDAO.existsById((itemCode))){
            return mapping.ItemEntityToDTO(itemDAO.getReferenceById(itemCode));
        }else{
            return new ItemErrorResponse(0,"Item Not Found");
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return mapping.ItemEntityListToDTOList(itemDAO.findAll());
    }
}
