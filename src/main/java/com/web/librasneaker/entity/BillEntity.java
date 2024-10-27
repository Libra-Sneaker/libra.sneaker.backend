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
import org.hibernate.Length;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "bills")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class BillEntity extends PrimaryEntity {
    @Column(length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column
    private String type;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "date_payment")
    private String datePayment;

    @Column
    private String address;

    @Column
    private String status;

    @Column(name = "employee-id",length = EntityProperties.LENGTH_ID)
    private String employeeId;

    @Column(name = "customer-id",length = EntityProperties.LENGTH_ID)
    private String customerId;

}
