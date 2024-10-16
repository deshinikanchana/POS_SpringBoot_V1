package lk.ijse.gdse.aad68.pos_system.DTO.IMPL;

import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.CustomerResponse;
import lk.ijse.gdse.aad68.pos_system.DTO.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements SuperDTO, CustomerResponse {
    private String cusId;
    private String cusName;
    private String cusAddress;
    private String cusSalary;
    private List<OrderDTO> orders = new ArrayList<>();
}
