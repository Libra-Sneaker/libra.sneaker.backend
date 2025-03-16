package com.web.librasneaker.repository;

import com.web.librasneaker.dto.billDetailManagement.BillDetailResponse;
import com.web.librasneaker.dto.billHistoryManagement.BillHistoryResponse;
import com.web.librasneaker.entity.BillDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetailEntity,String> {
    @Query(value = """
        SELECT 
            bd.id AS id,
            pd.url_image as urlImg,
            p.name as productName,
            bd.price as price,
            bd.quantity as quantity,
            c.name as color,
            s.name as size,
            bd.product_detail_id as productDetailId,
            pd.quantity as productDetailQuantity
        FROM
            bill_details bd
         LEFT JOIN
            product_details pd on pd.id = bd.product_detail_id
         LEFT JOIN 
            products p on p.id = pd.product_id 
         LEFT JOIN 
            colors c on c.id = pd.color_id 
         LEFT JOIN 
            sizes s on s.id = pd.size_id 
        WHERE
            bd.bill_id = :billId AND bd.delete_flag = :deleteFlag
            
        """, nativeQuery = true)
    List<BillDetailResponse> getBillDetail(@Param("billId") String billId, @Param("deleteFlag") Integer deleteFlag);

    @Query("SELECT bd FROM BillDetailEntity bd WHERE bd.billId = :billId AND bd.productDetailId = :productDetailId AND bd.deleteFlag = 0")
    Optional<BillDetailEntity> findByBillIdAndProductDetailId(@Param("billId") String billId, @Param("productDetailId") String productDetailId);

    List<BillDetailEntity> findByBillId(String billId);
}
