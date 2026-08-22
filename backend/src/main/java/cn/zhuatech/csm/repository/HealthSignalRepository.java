/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.repository; import cn.zhuatech.csm.model.HealthSignal; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface HealthSignalRepository extends JpaRepository<HealthSignal,Long>{List<HealthSignal> findAllByOrderByCodeAsc();long countByStatus(HealthSignal.Status status);}
