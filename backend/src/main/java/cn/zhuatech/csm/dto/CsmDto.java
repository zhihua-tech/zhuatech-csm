/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.dto;
import jakarta.validation.constraints.*; import java.time.*; import java.util.List;
public final class CsmDto { private CsmDto(){}
    public record Metric(String label,String value,String hint,String tone){}
    public record SuccessPlanView(Long id,String orderNo,String productCode,String productName,String customerPortfolio,String workshop,int plannedQty,int completedQty,int defectQty,LocalDate dueDate,String status,String batchNo,int progress){}
    public record HealthSignalView(String code,String name,String customerPortfolio,String status,int oee,LocalDateTime lastHeartbeat){}
    public record RiskReviewView(String riskReviewNo,String orderNo,String productName,String riskReviewType,int riskReviewQty,int defectQty,String result,String inspector){}
    public record Dashboard(List<Metric> metrics,List<SuccessPlanView> successPlans,List<HealthSignalView> healthSignal,List<RiskReviewView> riskReviews){}
    public record ReportRequest(@NotBlank String operationName,@Positive int goodQty,@PositiveOrZero int defectQty,@Size(max=200) String remark){}
    public record ReportResult(String orderNo,int completedQty,int defectQty,int progress,String status){}
}
