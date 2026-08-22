/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.config;

import cn.zhuatech.csm.model.*;
import cn.zhuatech.csm.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(CustomerPortfolioRepository customerPortfolios, SuccessPlanRepository orders,
                           HealthSignalRepository healthSignals, RiskReviewRepository riskReviews,
                           UserRepository users, PasswordEncoder encoder) {
        return args -> {
            if (customerPortfolios.count() > 0) return;
            CustomerPortfolio chemistry = customerPortfolios.save(new CustomerPortfolio("PORT-CHEM", "战略客户组合", "客户成功中心", 180));
            CustomerPortfolio micro = customerPortfolios.save(new CustomerPortfolio("PORT-MICRO", "成长客户组合", "研发中心", 120));
            CustomerPortfolio material = customerPortfolios.save(new CustomerPortfolio("PORT-MAT", "续约客户组合", "工程中心", 96));

            SuccessPlan t1 = orders.save(new SuccessPlan("SP-260801-018", "GB-T-228", "华东制造集团成功计划", material, 24, 16, 1, LocalDate.now().plusDays(1), SuccessPlan.Status.RUNNING, "S260801-A"));
            SuccessPlan t2 = orders.save(new SuccessPlan("SP-260801-021", "HPLC-042", "医药客户续约计划", chemistry, 18, 8, 0, LocalDate.now().plusDays(1), SuccessPlan.Status.RUNNING, "S260801-C"));
            SuccessPlan t3 = orders.save(new SuccessPlan("SP-260802-006", "ISO-4833", "新能源客户价值提升计划", micro, 12, 0, 0, LocalDate.now().plusDays(3), SuccessPlan.Status.RELEASED, "S260802-B"));
            SuccessPlan t4 = orders.save(new SuccessPlan("SP-260731-015", "ICP-017", "零售客户风险恢复计划", chemistry, 20, 20, 1, LocalDate.now(), SuccessPlan.Status.COMPLETED, "S260731-D"));

            healthSignals.saveAll(List.of(
                new HealthSignal("HS-HPLC-03", "产品采用率信号 03", chemistry, HealthSignal.Status.RUNNING, 88),
                new HealthSignal("HS-ICP-02", "合同续约信号", chemistry, HealthSignal.Status.IDLE, 76),
                new HealthSignal("HS-UTM-05", "关键人互动信号", material, HealthSignal.Status.RUNNING, 91),
                new HealthSignal("HS-INC-08", "服务工单风险信号 08", micro, HealthSignal.Status.ALARM, 62)
            ));
            riskReviews.saveAll(List.of(
                new RiskReview("RR-260801-032", t1, "留样风险评审", 6, 0, RiskReview.Result.PASSED, "周妍"),
                new RiskReview("RR-260801-011", t2, "前处理风险评审", 3, 0, RiskReview.Result.PASSED, "陆承"),
                new RiskReview("RR-260801-018", t4, "结果风险评审", 5, 1, RiskReview.Result.FAILED, "周妍"),
                new RiskReview("RR-260802-003", t3, "收样确认", 4, 0, RiskReview.Result.PENDING, "陆承")
            ));
            String demo = encoder.encode("Demo@2026");
            users.saveAll(List.of(
                new UserAccount("operator", demo, "陆承", UserAccount.Role.SUCCESS_MANAGER, "PORT-CHEM"),
                new UserAccount("planner", demo, "周妍", UserAccount.Role.CSM_DIRECTOR, null),
                new UserAccount("quality", demo, "顾清", UserAccount.Role.QUALITY, null),
                new UserAccount("admin", encoder.encode("ZhuaTech@2026"), "系统管理员", UserAccount.Role.ADMIN, null)
            ));
        };
    }
}
