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
@Table(name = "payment_method")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class PaymentEntity extends PrimaryEntity {

    @Column(name = "type_method")
    private Integer typeMethod;

}
