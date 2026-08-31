/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.controller;

import cn.zhuatech.bi.common.ApiResponse;
import cn.zhuatech.bi.service.CertifiedMetricReleaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/bi")
public class CertifiedMetricReleaseController {
    private final CertifiedMetricReleaseService service;

    public CertifiedMetricReleaseController(CertifiedMetricReleaseService service) { this.service = service; }

    @PostMapping("/certified-metric-release")
    public ApiResponse<CertifiedMetricReleaseService.Assessment> assess(
        @Valid @RequestBody CertifiedMetricReleaseService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
