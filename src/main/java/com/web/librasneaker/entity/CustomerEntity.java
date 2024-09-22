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

import java.util.Date;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class CustomerEntity extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(length = EntityProperties.LENGTH_NAME)
    private String name;

    @Column(length = EntityProperties.LENGTH_EMAIL)
    private String email;

    @Column
    private Date dateOfBirth;

    @Column
    private Integer sex;

    @Column(length = EntityProperties.LENGTH_PASSWORD)
    private String password;

    @Column(length = EntityProperties.LENGTH_URL)
    private String avatar;

    @Column
    private Integer deleteFlag = 0;

}
