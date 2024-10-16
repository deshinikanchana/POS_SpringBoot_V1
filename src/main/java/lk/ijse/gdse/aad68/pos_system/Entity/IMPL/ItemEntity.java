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
@Table(name = "items")
public class ItemEntity implements SuperEntity {
    @Id
    private String itemCode;
    private String itemName;
    private int itemQty;
    private int itemPrice;
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = false)
    private List<OrderDetailEntity> orderDetailList;
}
