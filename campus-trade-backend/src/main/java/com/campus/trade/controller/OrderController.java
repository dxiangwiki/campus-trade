package com.campus.trade.controller;

import com.campus.trade.common.Result;
import com.campus.trade.dto.OrderCreateDTO;
import com.campus.trade.entity.Order;
import com.campus.trade.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /** 创建订单 */
    @PostMapping("/create")
    public Result<Order> create(@RequestBody OrderCreateDTO dto) {
        return Result.success(orderService.create(dto.getProductId(), dto.getBuyerId()));
    }

    /** 按买家分页查询订单 */
    @GetMapping("/page")
    public Result<Page<Order>> page(
            @RequestParam Long buyerId,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize) {
        return Result.success(orderService.pageByBuyer(buyerId, pageNo, pageSize));
    }
}