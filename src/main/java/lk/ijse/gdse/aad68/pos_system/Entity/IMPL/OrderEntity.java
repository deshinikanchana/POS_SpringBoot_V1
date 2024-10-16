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
@Table(name = "orders")
public class OrderEntity implements SuperEntity {
    @Id
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "cusId",nullable = false)
    private CustomerEntity customer;
    private String orderDate;
    private int orderTotal;
    private int discount;
    private int subTotal;
    private int cash;
    private int balance;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderDetailEntity> orderDetailsList;
}
