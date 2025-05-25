package com.web.librasneaker.repository;

import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import com.web.librasneaker.dto.productManagement.ProductManagementResponse;
import com.web.librasneaker.dto.productManagement.ProductStatisticsResponse;
import com.web.librasneaker.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,String> {
    @Query(value = """
    SELECT
        ROW_NUMBER() OVER (ORDER BY p.created_date DESC) AS rowNum,
        p.id AS productId,
        p.name AS productName,
        COALESCE(SUM(pd.quantity), 0) AS totalQuantity,
        p.status AS status,
        p.created_date AS createdDate
    FROM
        products p
    LEFT JOIN
        product_details pd ON p.id = pd.product_id
    WHERE 
        (:#{#req.name} IS NULL OR :#{#req.name} = '' OR p.name LIKE %:#{#req.name}%)
        AND (:#{#req.status} IS NULL OR :#{#req.status} LIKE '' OR p.status = :#{#req.status})
    GROUP BY
        p.id, p.name, p.status
    ORDER BY p.created_date DESC
    """,
            countQuery = """
    SELECT COUNT(p.id)
    FROM
        products p
    LEFT JOIN
        product_details pd ON p.id = pd.product_id
    WHERE 
        (:#{#req.name} IS NULL OR :#{#req.name} = '' OR p.name LIKE %:#{#req.name}%)
        AND (:#{#req.status} IS NULL OR :#{#req.status} LIKE '' OR p.status = :#{#req.status})
    GROUP BY p.id, p.name, p.status, p.created_date
    """,
            nativeQuery = true)
    Page<ProductManagementResponse> getProductManagementResponse(Pageable pageable, @Param("req") FindProductManagementDTO req);

    Optional<ProductEntity> getProductEntitiesByName(String name);

    Long countByDeleteFlag(Integer deleteFlag);

    @Query(value = """
    SELECT 
        p.id AS productId,
        p.name AS productName,
        pd.url_image as urlImg,
        SUM(bd.quantity) AS totalSoldQuantity
    FROM 
        products p
    JOIN 
        product_details pd ON p.id = pd.product_id
    JOIN 
        bill_details bd ON pd.id = bd.product_detail_id
    JOIN 
        bills b ON bd.bill_id = b.id
    WHERE 
        b.status = 1 
        AND bd.delete_flag = 0
    GROUP BY 
        p.id, p.name, pd.url_image
    ORDER BY 
        totalSoldQuantity DESC
    LIMIT 3
    """, nativeQuery = true)
    List<ProductStatisticsResponse> getTopMostSoldProducts();
}
