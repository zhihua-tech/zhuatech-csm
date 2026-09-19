/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.controller;

import cn.zhuatech.csm.common.ApiResponse;
import cn.zhuatech.csm.service.CustomerEscalationGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/csm")
public class CustomerEscalationGovernanceController {
    private final CustomerEscalationGovernanceService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public CustomerEscalationGovernanceController(CustomerEscalationGovernanceService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/customer-escalation-governance")
    public ApiResponse<CustomerEscalationGovernanceService.Assessment> assess(
        @Valid @RequestBody CustomerEscalationGovernanceService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
