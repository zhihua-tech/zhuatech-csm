/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.controller;
import cn.zhuatech.csm.common.ApiResponse;import cn.zhuatech.csm.service.CustomerHealthAlertService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/csm/insights/customer-health") public class CustomerHealthAlertController {private final CustomerHealthAlertService service;/**
                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                   */
public CustomerHealthAlertController(CustomerHealthAlertService service){this.service=service;}/**
                                                                                                                                                                                                                                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                  */
@PostMapping ApiResponse<CustomerHealthAlertService.Result> evaluate(@Valid @RequestBody CustomerHealthAlertService.Request request){return ApiResponse.ok(service.evaluate(request));}}
