package lk.ijse.gdse.aad68.pos_system.Service;

import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.CustomerResponse;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(String cusId,CustomerDTO customerDTO);
    void deleteCustomer(String cusId);
    CustomerResponse getSelectedCustomer(String cusId);
    List<CustomerDTO> getAllCustomers();
}
