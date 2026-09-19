/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.controller;

import cn.zhuatech.bi.common.ApiResponse;
import cn.zhuatech.bi.service.MetricAnomalyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/bi")
public class MetricInsightController {
    private final MetricAnomalyService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public MetricInsightController(MetricAnomalyService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/metric-anomaly")
    public ApiResponse<MetricAnomalyService.Result> explain(@Valid @RequestBody MetricAnomalyService.Request request) {
        return ApiResponse.ok(service.explain(request));
    }
}
