/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.csm.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RenewalReadinessService {
    public Result evaluate(Request request) {
        int score = Math.max(0, Math.min(100, (int) Math.round(request.adoptionScore() * .35
            + request.healthScore() * .35 + (request.daysToRenewal() <= 90 ? 10 : 0)
            + (request.executiveSponsor() ? 10 : 0) + (request.expansionOpportunity() ? 10 : 0)
            - request.unresolvedCases() * 8)));
        String readiness = score >= 70 ? "READY" : score >= 40 ? "WATCH" : "AT_RISK";
        List<String> actions = new ArrayList<>();
        if (request.adoptionScore() < 70) actions.add("制定关键功能采用提升计划");
        if (request.unresolvedCases() > 0) actions.add("续约沟通前关闭高优先级服务问题");
        if (!request.executiveSponsor()) actions.add("建立客户高层关系并确认业务价值");
        if (actions.isEmpty()) actions.add("按续约节奏准备价值回顾材料");
        return new Result(request.customerName(), score, readiness, "AT_RISK".equals(readiness), actions);
    }

    public record Request(@NotBlank String customerName, @Min(0) int daysToRenewal,
                          @Min(0) @Max(100) int adoptionScore,
                          @Min(0) @Max(100) int healthScore,
                          @Min(0) int unresolvedCases, boolean executiveSponsor,
                          boolean expansionOpportunity) {}
    public record Result(String customerName, int readinessScore, String readiness,
                         boolean escalationRequired, List<String> actions) {}
}
