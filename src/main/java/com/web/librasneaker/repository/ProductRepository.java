package com.web.librasneaker.repository;

import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import com.web.librasneaker.dto.productManagement.ProductManagementResponse;
import com.web.librasneaker.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,String> {
    @Query(value = """
    SELECT
        ROW_NUMBER() OVER (ORDER BY p.id DESC) AS rowNum,
        p.id AS productId,
        p.name AS productName,
        pd.description AS description,
        b.name AS brandName,
        t.name AS typeName,
        m.name AS materialName,
        c.name AS colorName,
        sz.name AS sizeName,
        pd.price AS price,
        p.status AS status
    FROM
        products p
    LEFT JOIN
        product_details pd ON p.id = pd.product_id
    LEFT JOIN
        brands b ON p.brand_id = b.id
    LEFT JOIN
        types_shoe t ON p.type_id = t.id
    LEFT JOIN
        materials m ON p.material_id = m.id
    LEFT JOIN
        colors c ON pd.color_id = c.id
    LEFT JOIN
        sizes sz ON pd.size_id = sz.id
    WHERE 
        (:#{#req.name} IS NULL OR :#{#req.name} LIKE '' OR p.name LIKE %:#{#req.name}%)
        AND (:#{#req.brandId} IS NULL OR :#{#req.brandId} LIKE '' OR b.id = :#{#req.brandId})
        AND (:#{#req.typeId} IS NULL OR :#{#req.typeId} LIKE '' OR t.id = :#{#req.typeId})
        AND (:#{#req.materialId} IS NULL OR :#{#req.materialId} LIKE '' OR m.id = :#{#req.materialId})
        AND (:#{#req.status} IS NULL OR :#{#req.status} LIKE '' OR p.status = :#{#req.status})
        AND (:#{#req.colorId} IS NULL OR :#{#req.colorId} LIKE '' OR pd.color_id = :#{#req.colorId})
        AND (:#{#req.sizeId} IS NULL OR :#{#req.sizeId} LIKE '' OR pd.size_id = :#{#req.sizeId})
        AND (:#{#req.price} IS NULL OR :#{#req.price} LIKE '' OR pd.price = :#{#req.price})
    ORDER BY p.id DESC
    """, countQuery = """
    SELECT COUNT(p.id) 
    FROM
        products p
    LEFT JOIN
        product_details pd ON p.id = pd.product_id
    LEFT JOIN
        brands b ON p.brand_id = b.id
    LEFT JOIN
        types_shoe t ON p.type_id = t.id
    LEFT JOIN
        materials m ON p.material_id = m.id
    LEFT JOIN
        colors c ON pd.color_id = c.id
    LEFT JOIN
        sizes sz ON pd.size_id = sz.id
    WHERE 
        (:#{#req.name} IS NULL OR :#{#req.name} LIKE '' OR p.name LIKE %:#{#req.name}%)
        AND (:#{#req.brandId} IS NULL OR :#{#req.brandId} LIKE '' OR b.id = :#{#req.brandId})
        AND (:#{#req.typeId} IS NULL OR :#{#req.typeId} LIKE '' OR t.id = :#{#req.typeId})
        AND (:#{#req.materialId} IS NULL OR :#{#req.materialId} LIKE '' OR m.id = :#{#req.materialId})
        AND (:#{#req.status} IS NULL OR :#{#req.status} LIKE '' OR p.status = :#{#req.status})
        AND (:#{#req.colorId} IS NULL OR :#{#req.colorId} LIKE '' OR pd.color_id = :#{#req.colorId})
        AND (:#{#req.sizeId} IS NULL OR :#{#req.sizeId} LIKE '' OR pd.size_id = :#{#req.sizeId})
        AND (:#{#req.price} IS NULL OR :#{#req.price} LIKE '' OR pd.price = :#{#req.price})
    """, nativeQuery = true)
    Page<ProductManagementResponse> getProductManagementResponse(Pageable pageable, @Param("req") FindProductManagementDTO req);

    Optional<ProductEntity> getProductEntitiesByName(String name);



}
