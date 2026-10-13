package com.campus.trade.repository;

import com.campus.trade.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /** 按买家分页查询订单 */
    Page<Order> findByBuyerIdOrderByCreateTimeDesc(Long buyerId, Pageable pageable);

    /** 按卖家分页查询订单 */
    Page<Order> findBySellerIdOrderByCreateTimeDesc(Long sellerId, Pageable pageable);
}