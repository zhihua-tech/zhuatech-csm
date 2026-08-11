/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.csm.controller;
import cn.zhuatech.csm.common.ApiResponse;import cn.zhuatech.csm.service.CustomerHealthAlertService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/csm/insights/customer-health") public class CustomerHealthAlertController {private final CustomerHealthAlertService service;public CustomerHealthAlertController(CustomerHealthAlertService service){this.service=service;}@PostMapping ApiResponse<CustomerHealthAlertService.Result> evaluate(@Valid @RequestBody CustomerHealthAlertService.Request request){return ApiResponse.ok(service.evaluate(request));}}
