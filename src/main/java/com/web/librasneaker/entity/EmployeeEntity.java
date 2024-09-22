package com.web.librasneaker.entity;

import com.web.librasneaker.config.constant.classconstant.EntityProperties;
import com.web.librasneaker.config.constant.enumconstant.Role;
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
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class EmployeeEntity extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(length = EntityProperties.LENGTH_NAME)
    private String name;

    @Column(length = EntityProperties.LENGTH_EMAIL)
    private String email;

    @Column(length = EntityProperties.LENGTH_PASSWORD)
    private String password;

    @Column(length = EntityProperties.LENGTH_URL)
    private String avatar;

    @Column
    private Role role;

    @Column
    private Integer deleteFlag = 0;

}
