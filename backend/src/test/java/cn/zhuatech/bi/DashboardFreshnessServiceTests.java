/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi;

import cn.zhuatech.bi.service.DashboardFreshnessService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class DashboardFreshnessServiceTests {
    private final DashboardFreshnessService service = new DashboardFreshnessService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test
    void blocksDashboardWithSeverelyStaleDataset() {
        var result = service.evaluate(new DashboardFreshnessService.Request("EXEC-DASHBOARD", List.of(
            new DashboardFreshnessService.DatasetFreshness("sales_daily", 40, 60, 0),
            new DashboardFreshnessService.DatasetFreshness("inventory_hourly", 190, 60, 3))));

        assertEquals("BLOCKED", result.status());
        assertEquals("inventory_hourly", result.worstDataset());
        assertTrue(result.staleDatasets().contains("inventory_hourly"));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test
    void recognizesFreshDashboard() {
        var result = service.evaluate(new DashboardFreshnessService.Request("STORE-DASHBOARD", List.of(
            new DashboardFreshnessService.DatasetFreshness("sales_hourly", 35, 60, 0),
            new DashboardFreshnessService.DatasetFreshness("traffic_hourly", 50, 60, 0))));

        assertEquals("HEALTHY", result.status());
        assertEquals(100, result.healthScore());
    }
}
