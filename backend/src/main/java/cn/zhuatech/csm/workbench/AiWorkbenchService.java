/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.workbench;

import static cn.zhuatech.csm.workbench.WorkbenchInput.*;

import java.math.*;
import java.util.*;
import org.springframework.stereotype.Service;

/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
  /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
  public Map<String, Object> execute(Map<String, Object> r) {
    validate(r);

    keys(r, "customers");
    Set<String> ids = new HashSet<>();
    BigDecimal opening = BigDecimal.ZERO,
        retained = BigDecimal.ZERO,
        gross = BigDecimal.ZERO,
        expansion = BigDecimal.ZERO,
        contraction = BigDecimal.ZERO,
        churn = BigDecimal.ZERO,
        newRevenue = BigDecimal.ZERO;
    List<Map<String, Object>> out = new ArrayList<>();
    for (var c : rows(r, "customers", 1, 500)) {
      keys(c, "id", "opening", "closing");
      String id = id(c, "id");
      unique(ids, id);
      var a = money(c, "opening");
      var b = money(c, "closing");
      String movement;
      if (a.signum() == 0) {
        newRevenue = newRevenue.add(b);
        movement = b.signum() > 0 ? "NEW" : "INACTIVE";
      } else {
        opening = opening.add(a);
        retained = retained.add(b);
        gross = gross.add(a.min(b));
        if (b.signum() == 0) {
          churn = churn.add(a);
          movement = "CHURN";
        } else if (b.compareTo(a) > 0) {
          expansion = expansion.add(b.subtract(a));
          movement = "EXPANSION";
        } else if (b.compareTo(a) < 0) {
          contraction = contraction.add(a.subtract(b));
          movement = "CONTRACTION";
        } else movement = "UNCHANGED";
      }
      out.add(Map.of("id", id, "opening", a, "closing", b, "movement", movement));
    }
    return Map.of(
        "openingRecurringRevenue",
        opening,
        "cohortClosingRevenue",
        retained,
        "ratesDefined",
        opening.signum() > 0,
        "nrrPercent",
        opening.signum() == 0
            ? BigDecimal.ZERO
            : retained.multiply(new BigDecimal("100")).divide(opening, 6, RoundingMode.HALF_UP),
        "grrPercent",
        opening.signum() == 0
            ? BigDecimal.ZERO
            : gross.multiply(new BigDecimal("100")).divide(opening, 6, RoundingMode.HALF_UP),
        "expansion",
        expansion,
        "contraction",
        contraction,
        "churn",
        churn,
        "newRevenue",
        newRevenue,
        "customers",
        out);
  }
}
