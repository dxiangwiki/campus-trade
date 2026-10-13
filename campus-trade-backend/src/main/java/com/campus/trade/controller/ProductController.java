package com.campus.trade.controller;

import com.campus.trade.common.Result;
import com.campus.trade.entity.Product;
import com.campus.trade.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /** 分页查询在售商品 */
    @GetMapping("/page")
    public Result<Page<Product>> page(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "8") int pageSize,
            @RequestParam(required = false) String keyword) {
        return Result.success(productService.page(pageNo, pageSize, keyword));
    }

    /** 发布商品 */
    @PostMapping("/publish")
    public Result<Product> publish(@RequestBody Product product) {
        return Result.success(productService.publish(product));
    }

    /** 按 id 查商品 */
    @GetMapping("/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        return Result.success(productService.getById(id));
    }
}