/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bi.controller;

import cn.zhuatech.bi.common.ApiResponse;
import cn.zhuatech.bi.service.MetricAnomalyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bi")
public class MetricInsightController {
    private final MetricAnomalyService service;
    public MetricInsightController(MetricAnomalyService service) { this.service = service; }

    @PostMapping("/metric-anomaly")
    public ApiResponse<MetricAnomalyService.Result> explain(@Valid @RequestBody MetricAnomalyService.Request request) {
        return ApiResponse.ok(service.explain(request));
    }
}
