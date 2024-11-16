package com.web.librasneaker.repository;

import com.web.librasneaker.dto.billManagement.BillResponse;
import com.web.librasneaker.dto.billManagement.FindBillDTO;
import com.web.librasneaker.entity.BillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<BillEntity,String> {
    @Query(value = """
    SELECT
       ROW_NUMBER() OVER (ORDER BY b.created_date DESC) AS rowNum,
       b.code AS billCode,
       b.total_amount AS totalAmount,
       b.date_payment AS datePayment,
       b.type AS type,
       b.address AS address,
       c.id AS customerId,
       c.name AS customerName,
       e.id AS employeeId,
       e.name AS employeeName, 
       b.created_date AS createdDate,
       b.status AS status
   FROM
       bills b
   LEFT JOIN
       customers c ON c.id = b.customer_id
   LEFT JOIN
       employees e ON e.id = b.employee_id
    WHERE
      (
          '' IS NULL OR '' LIKE '' OR
          b.code LIKE '' OR
          c.name LIKE '' OR
          b.status LIKE ''
      )
      OR ( ('' IS NULL OR b.created_date >= '')
      AND ('' IS NULL OR b.created_date <= ''))
    ORDER BY b.created_date DESC
""", countQuery = """
    SELECT COUNT(b.id)
    FROM bills b
    LEFT JOIN customers c ON c.id = b.customer_id
    LEFT JOIN employees e ON e.id = b.employee_id
    WHERE
      (
          '' IS NULL OR '' LIKE '' OR
          b.code LIKE '' OR
          c.name LIKE '' OR
          b.status LIKE ''
      )
      OR ( ('' IS NULL OR b.created_date >= '')
      AND ('' IS NULL OR b.created_date <= ''))
""", nativeQuery = true)
    Page<BillResponse> getBillResponse(Pageable pageable, @Param("req") FindBillDTO req);


    Optional<BillEntity> getBillEntityByCode(String code);


}
