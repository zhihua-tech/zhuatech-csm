/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.repository; import cn.zhuatech.csm.model.CustomerPortfolio; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface CustomerPortfolioRepository extends JpaRepository<CustomerPortfolio,Long>{Optional<CustomerPortfolio> findByCode(String code);}
