package com.campus.trade.service;

import com.campus.trade.entity.Product;
import com.campus.trade.entity.User;
import com.campus.trade.repository.ProductRepository;
import com.campus.trade.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    /** 分页查询在售商品 */
    public Page<Product> page(int pageNo, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Product> result;
        if (keyword == null || keyword.trim().isEmpty()) {
            result = productRepository.findByStatusOrderByCreateTimeDesc(0, pageable);
        } else {
            result = productRepository
                    .findByStatusAndTitleContainingOrderByCreateTimeDesc(0, keyword, pageable);
        }
        // 补充卖家昵称
        result.getContent().forEach(p -> {
            userRepository.findById(p.getSellerId())
                    .ifPresent(u -> p.setSellerName(u.getNickname()));
        });
        return result;
    }

    /** 发布商品 */
    public Product publish(Product product) {
        product.setId(null);
        product.setStatus(0);
        product.setCreateTime(LocalDateTime.now());
        return productRepository.save(product);
    }

    /** 按 id 查 */
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("商品不存在"));
    }
}