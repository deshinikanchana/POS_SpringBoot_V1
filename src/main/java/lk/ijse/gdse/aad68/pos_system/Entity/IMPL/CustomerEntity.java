package lk.ijse.gdse.aad68.pos_system.Entity.IMPL;

import jakarta.persistence.*;
import lk.ijse.gdse.aad68.pos_system.Entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class CustomerEntity implements SuperEntity {
    @Id
    private String cusId;
    private String cusName;
    private String cusAddress;
    private String cusSalary;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = false)
    private List<OrderEntity> orders;
}
