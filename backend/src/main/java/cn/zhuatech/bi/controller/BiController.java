/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.controller;

import cn.zhuatech.bi.common.ApiResponse;import cn.zhuatech.bi.dto.BiDto.*;import cn.zhuatech.bi.service.BiService;import jakarta.validation.Valid;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/bi")
public class BiController {
    private final BiService service; /**
                                      * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                      */
public BiController(BiService service){this.service=service;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.dashboard());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/data-sources") public ApiResponse<List<AccountView>> accounts(){return ApiResponse.ok(service.accounts());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/business-metrics") public ApiResponse<List<BusinessMetricView>> businessMetrics(){return ApiResponse.ok(service.businessMetrics());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/data-jobs") public ApiResponse<List<DataJobView>> dataJobs(){return ApiResponse.ok(service.dataJobs());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/analysis-workbooks") public ApiResponse<List<ExpenseView>> expenses(){return ApiResponse.ok(service.expenses());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/subject-areas") public ApiResponse<List<SubjectAreaView>> subjectAreas(){return ApiResponse.ok(service.subjectAreas());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/business-metrics") @PreAuthorize("hasAnyRole('ADMIN','DATA_ANALYST')") public ApiResponse<BusinessMetricView> createBusinessMetric(@Valid @RequestBody CreateBusinessMetricRequest request){return ApiResponse.ok("业务指标创建成功",service.createBusinessMetric(request));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PatchMapping("/business-metrics/{id}/value") @PreAuthorize("hasAnyRole('ADMIN','DATA_ANALYST')") public ApiResponse<BusinessMetricView> recordReceipt(@PathVariable Long id,@Valid @RequestBody RecordReceiptRequest request){return ApiResponse.ok("指标值更新成功",service.recordReceipt(id,request));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/analysis-workbooks") @PreAuthorize("hasAnyRole('ADMIN','DATA_ANALYST','EMPLOYEE')") public ApiResponse<ExpenseView> submitExpense(@Valid @RequestBody SubmitExpenseRequest request){return ApiResponse.ok("分析工作簿已提交",service.submitExpense(request));}
}
