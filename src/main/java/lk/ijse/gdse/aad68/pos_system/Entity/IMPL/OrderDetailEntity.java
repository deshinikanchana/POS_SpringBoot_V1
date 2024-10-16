package lk.ijse.gdse.aad68.pos_system.Entity.IMPL;

import jakarta.persistence.*;
import lk.ijse.gdse.aad68.pos_system.Entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orderDetails")
public class OrderDetailEntity implements SuperEntity {
    @Id
    private String orderDetailIndex;
    @ManyToOne(optional = false)
    @JoinColumn(name = "orderId",nullable = false)
    private OrderEntity order;
    @ManyToOne(optional = false)
    @JoinColumn(name = "itemCode",nullable = false)
    private ItemEntity item;
    private int buyQty;
    private int itemTotal;

}
