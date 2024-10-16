package lk.ijse.gdse.aad68.pos_system.Service;

import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.ItemResponse;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(String itemCode,ItemDTO itemDTO);
    void deleteItem(String itemCode);
    ItemResponse getSelectedItem(String itemCode);
    List<ItemDTO> getAllItems();
}
