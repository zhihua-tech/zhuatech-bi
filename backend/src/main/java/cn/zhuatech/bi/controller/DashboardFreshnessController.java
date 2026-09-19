/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.controller;

import cn.zhuatech.bi.common.ApiResponse;
import cn.zhuatech.bi.service.DashboardFreshnessService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/bi/insights")
public class DashboardFreshnessController {
    private final DashboardFreshnessService service;

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DashboardFreshnessController(DashboardFreshnessService service) {
        this.service = service;
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/dashboard-freshness")
    public ApiResponse<DashboardFreshnessService.Result> evaluate(
        @Valid @RequestBody DashboardFreshnessService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
