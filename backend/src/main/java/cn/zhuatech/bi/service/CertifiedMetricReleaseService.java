/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class CertifiedMetricReleaseService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.definitionApproved()) blockers.add("指标口径尚未获业务负责人批准");
        if (!request.lineageComplete()) blockers.add("指标血缘不完整");
        if (!request.rowLevelPolicyConfigured()) blockers.add("行级数据权限未配置");
        if (request.lastRefreshAgeMinutes() > request.slaMinutes()) blockers.add("数据刷新已超过 SLA");
        if (request.openCriticalQualityIssues() > 0) blockers.add("存在未关闭的严重数据质量问题");
        if (!blockers.isEmpty()) {
            actions.add("关闭全部阻断项并重新执行指标认证");
            return new Assessment(Decision.BLOCKED, false, blockers, actions);
        }
        if (request.reconciliationVarianceBps() > request.toleranceBps()) {
            actions.add("由数据责任人复核对账差异并留存审批意见");
            return new Assessment(Decision.REVIEW, false, blockers, actions);
        }
        actions.add("发布认证指标并记录版本、责任人和审计时间");
        return new Assessment(Decision.RELEASE, true, blockers, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String metricCode, @NotBlank String dataOwner,
                          boolean definitionApproved, boolean lineageComplete,
                          boolean rowLevelPolicyConfigured,
                          @Min(0) int lastRefreshAgeMinutes, @Min(1) int slaMinutes,
                          @Min(0) int reconciliationVarianceBps, @Min(0) int toleranceBps,
                          @Min(0) int openCriticalQualityIssues) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Assessment(Decision decision, boolean certified, List<String> blockers,
                             List<String> actions) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { RELEASE, REVIEW, BLOCKED }
}
