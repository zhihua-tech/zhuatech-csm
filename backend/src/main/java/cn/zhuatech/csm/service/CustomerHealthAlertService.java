/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.service;
import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.util.*;
@Service public class CustomerHealthAlertService {
 public Result evaluate(Request r){int score=100;List<String> actions=new ArrayList<>();if(r.usageTrend()<0){score-=Math.min(30,Math.abs(r.usageTrend()));actions.add("制定产品使用恢复计划");}score-=Math.min(40,r.openCriticalCases()*15);if(r.openCriticalCases()>0)actions.add("升级处理关键支持工单");if(r.adoptionRate()<60){score-=20;actions.add("补齐核心功能采用场景");}if(r.nps()<0){score-=15;actions.add("安排客户高层回访");}if(!r.executiveSponsor())score-=10;if(r.paymentOverdueDays()>0){score-=20;actions.add("协同财务处理逾期款项");}score=Math.max(0,score);String status=score>=75?"HEALTHY":score>=50?"AT_RISK":"CRITICAL";if(actions.isEmpty())actions.add("客户健康稳定，保持价值复盘节奏");return new Result(score,status,r.daysToRenewal()<=90,actions);}
 public record Request(@Min(-100) @Max(100) int usageTrend,@Min(0) int openCriticalCases,@Min(0) @Max(100) int adoptionRate,@Min(-100) @Max(100) int nps,@NotNull Boolean executiveSponsor,@Min(0) int paymentOverdueDays,@Min(0) int daysToRenewal){}
 public record Result(int healthScore,String status,boolean renewalWindow,List<String> actions){}
}
