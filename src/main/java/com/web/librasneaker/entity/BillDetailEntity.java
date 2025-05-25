package com.web.librasneaker.entity;

import com.web.librasneaker.config.constant.classconstant.EntityProperties;
import com.web.librasneaker.entity.base.PrimaryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "bill_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class BillDetailEntity extends PrimaryEntity {

    @Column(name = "product_detail_id", length = EntityProperties.LENGTH_ID)
    private String productDetailId;

    @Column(name = "bill_id", length = EntityProperties.LENGTH_ID)
    private String billId;

    @Column(name = "quantity")
    private int quantity;

    @Column()
    private double price;

    @Column
    private Integer deleteFlag;

    @Column(name = "discount_amount")
    private Double discountAmount; // Số tiền giảm giá cho sản phẩm này

    @Column(name = "final_price")
    private Double finalPrice; // Giá sau khi giảm

}
