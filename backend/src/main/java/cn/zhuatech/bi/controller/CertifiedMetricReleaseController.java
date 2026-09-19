/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.controller;

import cn.zhuatech.bi.common.ApiResponse;
import cn.zhuatech.bi.service.CertifiedMetricReleaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/bi")
public class CertifiedMetricReleaseController {
    private final CertifiedMetricReleaseService service;

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public CertifiedMetricReleaseController(CertifiedMetricReleaseService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/certified-metric-release")
    public ApiResponse<CertifiedMetricReleaseService.Assessment> assess(
        @Valid @RequestBody CertifiedMetricReleaseService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
