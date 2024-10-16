package lk.ijse.gdse.aad68.pos_system.Service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad68.pos_system.CustomOBJ.Custom.CustomerResponse;
import lk.ijse.gdse.aad68.pos_system.CustomOBJ.IMPL.CustomerErrorResponse;
import lk.ijse.gdse.aad68.pos_system.DAO.CustomerDAO;
import lk.ijse.gdse.aad68.pos_system.DTO.IMPL.CustomerDTO;
import lk.ijse.gdse.aad68.pos_system.Entity.IMPL.CustomerEntity;
import lk.ijse.gdse.aad68.pos_system.Exception.CustomerNotFoundException;
import lk.ijse.gdse.aad68.pos_system.Exception.DataPersistFailedException;
import lk.ijse.gdse.aad68.pos_system.Service.CustomerService;
import lk.ijse.gdse.aad68.pos_system.Util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private final CustomerDAO customerDAO;
    @Autowired
    private final Mapping mapping;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity savedCustomer =
                customerDAO.save(mapping.CustDTOToEntity(customerDTO));
        if(savedCustomer == null && savedCustomer.getCusId() == null ) {
            throw new DataPersistFailedException("Cannot save Customer");
        }
    }
    @Override
    public void updateCustomer(String cusId,CustomerDTO customerDTO) {
        Optional<CustomerEntity> tmpCustEntity = customerDAO.findById(cusId);
        if(!tmpCustEntity.isPresent()){
            throw new CustomerNotFoundException("Customer not found");
        }else {
            tmpCustEntity.get().setCusName(customerDTO.getCusName());
            tmpCustEntity.get().setCusAddress(customerDTO.getCusAddress());
            tmpCustEntity.get().setCusSalary(customerDTO.getCusSalary());
        }
    }
    @Override
    public void deleteCustomer(String cusId) {
        Optional<CustomerEntity> temp = customerDAO.findById(cusId);
        if(!temp.isPresent()){
            throw new CustomerNotFoundException("Customer not found");
        }else {
            customerDAO.deleteById(cusId);
        }
    }
    @Override
    public CustomerResponse getSelectedCustomer(String cusId) {
        if(customerDAO.existsById((cusId))){

            CustomerEntity specificCusEnt = customerDAO.getReferenceById(cusId);
            return mapping.CustEntityToDTO(specificCusEnt);

        }else{
            return new CustomerErrorResponse(0,"Customer Not Found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapping.CustEntityListToDTOList(customerDAO.findAll());
    }
}
