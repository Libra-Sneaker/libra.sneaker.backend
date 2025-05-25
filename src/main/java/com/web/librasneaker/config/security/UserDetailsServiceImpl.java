package com.web.librasneaker.config.security;

import com.web.librasneaker.entity.CustomerEntity;
import com.web.librasneaker.entity.EmployeeEntity;
import com.web.librasneaker.repository.CustomerRepository;
import com.web.librasneaker.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String userName) throws UsernameNotFoundException {
        EmployeeEntity employeeEntity = employeeRepository.findByEmailAndDeleteFlagFalse(userName);
        if (Objects.isNull(employeeEntity)) {
            throw new UsernameNotFoundException("Employee not found with username: " + userName);
        }
        System.out.println("Found employee: " + employeeEntity.getEmail() + ", password: " + employeeEntity.getPassword());
        return UserDetailsImpl.buildEmployee(employeeEntity);
    }

    @Transactional
    public UserDetailsImpl loadCustomerByUsername(String userName) throws UsernameNotFoundException {
        CustomerEntity customerEntity = customerRepository.findByEmailAndDeleteFlagFalse(userName);
        if (Objects.isNull(customerEntity)) {
            throw new UsernameNotFoundException("Customer not found with username: " + userName);
        }
        return UserDetailsImpl.buildCustomer(customerEntity);
    }

}
