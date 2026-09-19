/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.controller;

import cn.zhuatech.csm.common.ApiResponse;
import cn.zhuatech.csm.service.RenewalReadinessService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/admin")
public class RenewalReadinessController {
    private final RenewalReadinessService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public RenewalReadinessController(RenewalReadinessService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/renewal-readiness")
    public ApiResponse<RenewalReadinessService.Result> evaluate(@Valid @RequestBody RenewalReadinessService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
