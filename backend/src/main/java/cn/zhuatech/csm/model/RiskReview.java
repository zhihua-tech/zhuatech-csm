/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.csm.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="csm_risk_review") public class RiskReview extends BaseEntity {
    public enum Result { PENDING, PASSED, FAILED }
    @Column(nullable=false,unique=true,length=32) private String riskReviewNo; @ManyToOne(optional=false,fetch=FetchType.LAZY) private SuccessPlan successPlan;
    @Column(nullable=false,length=30) private String riskReviewType; @Column(nullable=false) private int riskReviewQty; @Column(nullable=false) private int defectQty; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Result result;
    @Column(length=50) private String inspector; @Column(nullable=false) private LocalDateTime createdAt;
    protected RiskReview(){} public RiskReview(String riskReviewNo,SuccessPlan successPlan,String riskReviewType,int riskReviewQty,int defectQty,Result result,String inspector){this.riskReviewNo=riskReviewNo;this.successPlan=successPlan;this.riskReviewType=riskReviewType;this.riskReviewQty=riskReviewQty;this.defectQty=defectQty;this.result=result;this.inspector=inspector;this.createdAt=LocalDateTime.now();}
    public String getRiskReviewNo(){return riskReviewNo;} public SuccessPlan getSuccessPlan(){return successPlan;} public String getRiskReviewType(){return riskReviewType;} public int getRiskReviewQty(){return riskReviewQty;} public int getDefectQty(){return defectQty;} public Result getResult(){return result;} public String getInspector(){return inspector;}
}
