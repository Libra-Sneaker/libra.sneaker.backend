package com.web.librasneaker.repository;

import com.web.librasneaker.dto.customerManagement.CustomerNameResponse;
import com.web.librasneaker.dto.customerManagement.CustomerRespone;
import com.web.librasneaker.dto.customerManagement.FindByNameDTO;
import com.web.librasneaker.dto.customerManagement.FindCustomerDTO;
import com.web.librasneaker.dto.customerManagement.TopCustomerResponse;
import com.web.librasneaker.entity.CustomerEntity;
import com.web.librasneaker.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    @Query(value = """
    SELECT
        ROW_NUMBER() OVER (ORDER BY c.created_date DESC) AS rowNum,
        c.id AS customerId,
        c.code AS customerCode,
        c.name AS customerName,
        c.address AS address,
        c.email AS email,
        c.date_of_birth AS dateOfBirth,
        c.phone_number AS phoneNumber,
        c.sex AS sex,
        c.delete_flag AS deleteFlag
    FROM
        customers c
    WHERE
       (
           :#{#req.searchTerm} IS NULL OR :#{#req.searchTerm} LIKE '' OR
           c.code LIKE %:#{#req.searchTerm}% OR
           c.name LIKE %:#{#req.searchTerm}% OR
           c.phone_number LIKE %:#{#req.searchTerm}% OR
           c.email LIKE %:#{#req.searchTerm}%
       )
       AND (:#{#req.sex} IS NULL OR c.sex = :#{#req.sex})
       AND (:#{#req.deleteFlag} IS NULL OR c.delete_flag = :#{#req.deleteFlag})
    ORDER BY c.created_date DESC
""", countQuery = """
    SELECT COUNT(c.id)
    FROM customers c
    WHERE
        (   :#{#req.searchTerm} IS NULL OR :#{#req.searchTerm} LIKE '' OR
            c.code LIKE %:#{#req.searchTerm}% OR
            c.name LIKE %:#{#req.searchTerm}% OR
            c.phone_number LIKE %:#{#req.searchTerm}% OR
            c.email LIKE %:#{#req.searchTerm}%
        )
        AND (:#{#req.sex} IS NULL OR c.sex = :#{#req.sex})
        AND (:#{#req.deleteFlag} IS NULL OR c.delete_flag = :#{#req.deleteFlag})
""", nativeQuery = true)
    Page<CustomerRespone> getCustomerResponse(Pageable pageable, @Param("req") FindCustomerDTO req);



    CustomerEntity findByEmailAndDeleteFlagFalse(String email);

    Optional<CustomerEntity> findByCode(String code);

    boolean existsByEmail(String email);

    Optional<CustomerEntity> findByEmailAndIdNot(String email, String id);


    @Query("SELECT MAX(c.code) FROM CustomerEntity c")
    String findMaxCode();

    @Query(value = """
        SELECT 
        c.id AS customerId,
        c.code AS customerCode,
        c.name AS customerName,
        c.address AS address,
        c.email AS email,
        c.date_of_birth AS dateOfBirth,
        c.phone_number AS phoneNumber,
        c.sex AS sex,
        c.delete_flag AS deleteFlag
        FROM customers c
        WHERE 
        (:#{#req.name} IS NULL OR :#{#req.name} = '' OR c.name LIKE %:#{#req.name}%)
        """, nativeQuery = true)
    List<CustomerNameResponse> searchByName(@Param("req") FindByNameDTO req);

    @Query(value = """
        SELECT 
            c.id AS customerId,
            c.name AS customerName,
            c.code AS customerCode,
            c.phone_number AS phoneNumber,
            c.email AS email,
            COUNT(b.id) AS totalOrders,
            SUM(b.final_amount) AS totalSpent
        FROM 
            customers c
        JOIN 
            bills b ON c.id = b.customer_id
        WHERE 
            b.status = 1 
            AND b.delete_flag = 0
        GROUP BY 
            c.id, c.name, c.code, c.phone_number, c.email
        ORDER BY 
            totalSpent DESC
        LIMIT 3
        """, nativeQuery = true)
    List<TopCustomerResponse> getTop3Customers();

    @Query(value = """
        SELECT COUNT(DISTINCT c.id)
        FROM customers c
        JOIN bills b ON c.id = b.customer_id
        WHERE 
            b.status = 1 
            AND b.delete_flag = 0
            AND MONTH(b.created_date) = MONTH(CURRENT_DATE)
            AND YEAR(b.created_date) = YEAR(CURRENT_DATE)
        """, nativeQuery = true)
    Long getTotalCustomersThisMonth();

}
