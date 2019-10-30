package com.pramati.kart.productManagement.repository;

import com.pramati.kart.productManagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

 @Query("SELECT m FROM Product m  left outer join  Category  c on c.id=m.categoryId WHERE  CONCAT(COALESCE(c.name,''), '', m.name, '', m.description, '', COALESCE(c.description,'')) LIKE %:searchKey%")
    List<Product> findProduct( String searchKey);


}
