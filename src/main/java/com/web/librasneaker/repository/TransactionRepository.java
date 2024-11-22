package com.web.librasneaker.repository;

import com.web.librasneaker.dto.billHistoryManagement.BillHistoryResponse;
import com.web.librasneaker.dto.transactionManagement.TransactionResponse;
import com.web.librasneaker.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,String> {

    @Query(value = """
        SELECT
            t.id AS id,
            t.payment_id AS paymentId,
            t.bill_id AS billId,
            t.money AS money,
            t.status ,
            p.type_method as typeMethod,
            t.created_date AS createdDate,
            e.name as employeeName,
            t.type AS typeTransaction
        FROM
            transactions t
        LEFT JOIN
            payment_method p ON p.id = t.payment_id
        LEFT JOIN
            bills b on b.id = t.bill_id
        LEFT JOIN
            employees e on e.id = b.employee_id
        WHERE
            t.bill_id = :billId

        """, nativeQuery = true)
    List<TransactionResponse> getTransaction(@Param("billId") String billId);

}
