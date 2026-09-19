/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.controller; import cn.zhuatech.csm.common.ApiResponse; import cn.zhuatech.csm.dto.CsmDto.*; import cn.zhuatech.csm.service.CsmService; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/admin") @PreAuthorize("hasAnyRole('CSM_DIRECTOR','QUALITY','ADMIN')") public class CustomerSuccessAdminController {private final CsmService service;/**
                                                                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                           */
public CustomerSuccessAdminController(CsmService service){this.service=service;}/**
                                                                                                                                                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                           */
@GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.adminDashboard());}/**
                                                                                                                                                                                                                                                                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                                                */
@GetMapping("/work-orders") public ApiResponse<List<SuccessPlanView>> orders(){return ApiResponse.ok(service.successPlans());}}
