/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.repository; import cn.zhuatech.csm.model.RiskReview; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface RiskReviewRepository extends JpaRepository<RiskReview,Long>{List<RiskReview> findTop10ByOrderByIdDesc();long countByResult(RiskReview.Result result);}
