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
@Table(name = "cart_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class CartDetailEntity extends PrimaryEntity {
    @Column(name = "cart_id",length = EntityProperties.LENGTH_ID)
    private String cartId;

    @Column(name = "product_detail_id",length = EntityProperties.LENGTH_ID)
    private String productDetailId;

    @Column
    private int quantity;
}
