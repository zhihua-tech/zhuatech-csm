/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerEscalationGovernanceService {
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.accountOwnerAssigned()) blockers.add("客户升级事件未指定责任人");
        if (!request.executiveSponsorAssigned() && request.severity() >= 4) blockers.add("重大客户事件未指定高层责任人");
        if (!request.customerCommunicationApproved()) blockers.add("客户沟通方案尚未批准");
        if (!request.dataScopeContained()) blockers.add("事件影响范围尚未控制");
        if (!blockers.isEmpty()) {
            actions.add("维持重大事件状态并补齐责任、沟通和止损控制");
            return new Assessment(Decision.BLOCKED, blockers, actions);
        }
        if (request.slaRemainingMinutes() <= request.estimatedRecoveryMinutes()
            || !request.rootCauseOwnerAssigned() || !request.recoveryPlanApproved()) {
            if (request.slaRemainingMinutes() <= request.estimatedRecoveryMinutes()) actions.add("升级 SLA 违约风险并更新客户预期");
            if (!request.rootCauseOwnerAssigned()) actions.add("指定根因分析负责人和完成期限");
            if (!request.recoveryPlanApproved()) actions.add("完成服务恢复计划审批");
            return new Assessment(Decision.EXECUTIVE_REVIEW, blockers, actions);
        }
        actions.add("执行恢复计划并按沟通节奏持续更新客户");
        return new Assessment(Decision.PROCEED, blockers, actions);
    }

    public record Request(@NotBlank String escalationId, @NotBlank String accountId,
                          @Min(1) int severity, boolean accountOwnerAssigned,
                          boolean executiveSponsorAssigned, boolean customerCommunicationApproved,
                          boolean dataScopeContained, @Min(0) int slaRemainingMinutes,
                          @Min(0) int estimatedRecoveryMinutes, boolean rootCauseOwnerAssigned,
                          boolean recoveryPlanApproved) {}
    public record Assessment(Decision decision, List<String> blockers, List<String> actions) {}
    public enum Decision { PROCEED, EXECUTIVE_REVIEW, BLOCKED }
}
