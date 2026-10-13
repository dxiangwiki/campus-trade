package com.campus.trade.service;

import com.campus.trade.entity.Order;
import com.campus.trade.entity.Product;
import com.campus.trade.entity.User;
import com.campus.trade.repository.OrderRepository;
import com.campus.trade.repository.ProductRepository;
import com.campus.trade.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    /** 创建订单 */
    @Transactional(rollbackFor = Exception.class)
    public Order create(Long productId, Long buyerId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("商品不存在"));

        if (product.getStatus() != 0) {
            throw new RuntimeException("商品已售出或已下架");
        }
        if (product.getSellerId().equals(buyerId)) {
            throw new RuntimeException("不能购买自己发布的商品");
        }

        Order order = new Order();
        order.setOrderNo("ORD" + System.currentTimeMillis()
                + UUID.randomUUID().toString().substring(0, 4).toUpperCase());
        order.setProductId(productId);
        order.setBuyerId(buyerId);
        order.setSellerId(product.getSellerId());
        order.setAmount(product.getPrice());
        order.setStatus(0);
        order.setCreateTime(LocalDateTime.now());
        Order saved = orderRepository.save(order);

        // 修改商品状态为已售
        product.setStatus(1);
        productRepository.save(product);

        return saved;
    }

    /** 按买家分页查订单 */
    public Page<Order> pageByBuyer(Long buyerId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Order> result = orderRepository
                .findByBuyerIdOrderByCreateTimeDesc(buyerId, pageable);
        // 补充商品标题 + 买家昵称
        result.getContent().forEach(o -> {
            productRepository.findById(o.getProductId())
                    .ifPresent(p -> o.setProductTitle(p.getTitle()));
            userRepository.findById(o.getBuyerId())
                    .ifPresent(u -> o.setBuyerName(u.getNickname()));
        });
        return result;
    }
}