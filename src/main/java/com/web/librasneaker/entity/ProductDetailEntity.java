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
@Table(name = "product_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class ProductDetailEntity extends PrimaryEntity {

    @Column(name = "size_id",length = EntityProperties.LENGTH_ID)
    private String sizeId;

    @Column(name = "color_id",length = EntityProperties.LENGTH_ID)
    private String colorId;

    @Column(name = "product_id",length = EntityProperties.LENGTH_ID)
    private String productId;

    @Column
    private Double price;

    @Column
    private Long quantity;

    @Column
    private String urlImage;

    @Column
    private Integer status = 1;

    @Column
    private Integer deleteFlag = 0;

}
