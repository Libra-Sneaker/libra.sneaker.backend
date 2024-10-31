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
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class TransactionEntity extends PrimaryEntity {

    @Column(name = "bill_id", length = EntityProperties.LENGTH_ID)
    private String billId;

    @Column(name = "payment_id", length = EntityProperties.LENGTH_ID)
    private String paymentId;

    @Column
    private String status;

    @Column
    private Double money;

}