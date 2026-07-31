/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.csm.repository; import cn.zhuatech.csm.model.SuccessPlan; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface SuccessPlanRepository extends JpaRepository<SuccessPlan,Long>{List<SuccessPlan> findAllByOrderByDueDateAsc();List<SuccessPlan> findByCustomerPortfolioCodeOrderByDueDateAsc(String code);long countByStatus(SuccessPlan.Status status);}
