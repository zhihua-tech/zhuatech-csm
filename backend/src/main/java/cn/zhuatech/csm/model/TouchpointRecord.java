/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="csm_touchpoint_record") public class TouchpointRecord extends BaseEntity {
    @ManyToOne(optional=false,fetch=FetchType.LAZY) private SuccessPlan successPlan; @Column(nullable=false,length=50) private String operationName; @Column(nullable=false) private int goodQty; @Column(nullable=false) private int defectQty;
    @Column(nullable=false,length=50) private String operatorName; @Column(nullable=false) private LocalDateTime reportedAt; @Column(length=200) private String remark;
    protected TouchpointRecord(){} public TouchpointRecord(SuccessPlan successPlan,String operationName,int goodQty,int defectQty,String operatorName,String remark){this.successPlan=successPlan;this.operationName=operationName;this.goodQty=goodQty;this.defectQty=defectQty;this.operatorName=operatorName;this.reportedAt=LocalDateTime.now();this.remark=remark;}
    public SuccessPlan getSuccessPlan(){return successPlan;} public String getOperationName(){return operationName;} public int getGoodQty(){return goodQty;} public int getDefectQty(){return defectQty;} public String getOperatorName(){return operatorName;} public LocalDateTime getReportedAt(){return reportedAt;}
}
