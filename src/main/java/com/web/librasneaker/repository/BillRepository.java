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

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<BillEntity,String> {
        @Query(value = """
        SELECT
           ROW_NUMBER() OVER (ORDER BY b.created_date DESC) AS rowNum,
           b.id,
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
          (:#{#req.searchTerm} IS NULL OR :#{#req.searchTerm} LIKE '' OR
                  b.code LIKE %:#{#req.searchTerm}% OR
                  c.name LIKE %:#{#req.searchTerm}%)
                 AND (:#{#req.datePaymentStart} IS NULL OR b.date_payment >= :#{#req.datePaymentStart})
                 AND (:#{#req.datePaymentEnd} IS NULL OR b.date_payment <= :#{#req.datePaymentEnd})
                 AND (:#{#req.status} IS NULL OR b.status = :#{#req.status})
        ORDER BY b.created_date DESC
    """, countQuery = """
        SELECT COUNT(b.id)
        FROM bills b
        LEFT JOIN customers c ON c.id = b.customer_id
        LEFT JOIN employees e ON e.id = b.employee_id
        WHERE
          (:#{#req.searchTerm} IS NULL OR :#{#req.searchTerm} LIKE '' OR
                   b.code LIKE %:#{#req.searchTerm}% OR
                   c.name LIKE %:#{#req.searchTerm}%)
                  AND (:#{#req.datePaymentStart} IS NULL OR b.date_payment >= :#{#req.datePaymentStart})
                  AND (:#{#req.datePaymentEnd} IS NULL OR b.date_payment <= :#{#req.datePaymentEnd})
                  AND (:#{#req.status} IS NULL OR b.status = :#{#req.status})
    """, nativeQuery = true)
        Page<BillResponse> getBillResponse(Pageable pageable, @Param("req") FindBillDTO req);

    @Query(value = """
    SELECT
       ROW_NUMBER() OVER (ORDER BY b.created_date DESC) AS rowNum,
       b.id,
       b.code AS billCode,
       b.total_amount AS totalAmount,
       b.date_payment AS datePayment,
       b.type AS type,
       b.phone_number AS phoneNumber,
       b.recipient,
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
   WHERE b.id = :id
""", nativeQuery = true)
    Optional<BillResponse> getInfoBill(@Param("id") String id);


    Optional<BillEntity> getBillEntityByCode(String code);


}
