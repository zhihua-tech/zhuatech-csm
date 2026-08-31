/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerEscalationGovernanceServiceTest {
    private final CustomerEscalationGovernanceService service = new CustomerEscalationGovernanceService();

    @Test void proceedsWithGovernedRecovery() {
        var result = service.assess(new CustomerEscalationGovernanceService.Request(
            "ESC-001", "ACC-001", 4, true, true, true, true, 240, 120, true, true));
        assertThat(result.decision()).isEqualTo(CustomerEscalationGovernanceService.Decision.PROCEED);
    }

    @Test void blocksUnownedUncontainedEscalation() {
        var result = service.assess(new CustomerEscalationGovernanceService.Request(
            "ESC-002", "ACC-002", 5, false, false, false, false, 60, 120, false, false));
        assertThat(result.decision()).isEqualTo(CustomerEscalationGovernanceService.Decision.BLOCKED);
        assertThat(result.blockers()).hasSize(4);
    }

    @Test void routesRecoveryRiskToExecutiveReview() {
        var result = service.assess(new CustomerEscalationGovernanceService.Request(
            "ESC-003", "ACC-003", 3, true, false, true, true, 60, 90, false, false));
        assertThat(result.decision()).isEqualTo(CustomerEscalationGovernanceService.Decision.EXECUTIVE_REVIEW);
        assertThat(result.actions()).hasSize(3);
    }
}
