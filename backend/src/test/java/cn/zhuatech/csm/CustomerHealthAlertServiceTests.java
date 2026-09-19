/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.csm;
import cn.zhuatech.csm.service.CustomerHealthAlertService;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class CustomerHealthAlertServiceTests {private final CustomerHealthAlertService service=new CustomerHealthAlertService();/**
                                                                                                                          * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                          */
@Test void identifiesCriticalCustomer(){var r=service.evaluate(new CustomerHealthAlertService.Request(-30,2,30,-20,false,30,45));assertEquals("CRITICAL",r.status());assertTrue(r.renewalWindow());}/**
                                                                                                                                                                                                                                                                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                              */
@Test void keepsStrongCustomerHealthy(){var r=service.evaluate(new CustomerHealthAlertService.Request(20,0,90,50,true,0,180));assertEquals("HEALTHY",r.status());}}
