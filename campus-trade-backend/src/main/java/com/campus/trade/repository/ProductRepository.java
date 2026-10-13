package com.campus.trade.repository;

import com.campus.trade.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /** 按状态分页查询（商品列表用） */
    Page<Product> findByStatusOrderByCreateTimeDesc(Integer status, Pageable pageable);

    /** 按状态 + 标题模糊分页查询（搜索用） */
    Page<Product> findByStatusAndTitleContainingOrderByCreateTimeDesc(
            Integer status, String title, Pageable pageable);
}