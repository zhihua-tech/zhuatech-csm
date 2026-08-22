/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.csm;
import cn.zhuatech.csm.service.CustomerHealthAlertService;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
class CustomerHealthAlertServiceTests {private final CustomerHealthAlertService service=new CustomerHealthAlertService();@Test void identifiesCriticalCustomer(){var r=service.evaluate(new CustomerHealthAlertService.Request(-30,2,30,-20,false,30,45));assertEquals("CRITICAL",r.status());assertTrue(r.renewalWindow());}@Test void keepsStrongCustomerHealthy(){var r=service.evaluate(new CustomerHealthAlertService.Request(20,0,90,50,true,0,180));assertEquals("HEALTHY",r.status());}}
