package lk.ijse.gdse.aad68.pos_system.CustomOBJ.IMPL;

import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemErrorResponse implements ItemResponse, Serializable {
    private int errorCode;
    private String errorMessage;

}