/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bi.controller;

import cn.zhuatech.bi.common.ApiResponse;
import cn.zhuatech.bi.service.DashboardFreshnessService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bi/insights")
public class DashboardFreshnessController {
    private final DashboardFreshnessService service;

    public DashboardFreshnessController(DashboardFreshnessService service) {
        this.service = service;
    }

    @PostMapping("/dashboard-freshness")
    public ApiResponse<DashboardFreshnessService.Result> evaluate(
        @Valid @RequestBody DashboardFreshnessService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
