/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class MetricAnomalyService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result explain(Request request) {
        double zScore = Math.abs(request.currentValue() - request.baselineValue()) / request.standardDeviation();
        int score = Math.min(100, (int) Math.round(zScore * 15 + request.businessImpact() * .4
            + (request.dataFreshnessHours() > 24 ? 15 : 0)));
        String severity = score >= 80 ? "CRITICAL" : score >= 50 ? "HIGH" : score >= 25 ? "WATCH" : "NORMAL";
        String direction = request.currentValue() > request.baselineValue() ? "UP"
            : request.currentValue() < request.baselineValue() ? "DOWN" : "FLAT";
        List<String> actions = new ArrayList<>();
        if (request.dataFreshnessHours() > 24) actions.add("先刷新数据源再确认业务异常");
        if (zScore >= 3) actions.add("按维度下钻并定位主要贡献项");
        if (score >= 50) actions.add("通知指标负责人确认原因与行动计划");
        if (actions.isEmpty()) actions.add("继续按当前频率监控指标");
        return new Result(request.metricName(), round(zScore), direction, score, severity, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private double round(double value) { return Math.round(value * 100.0) / 100.0; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String metricName, double currentValue, double baselineValue,
                          @Positive double standardDeviation, @Min(0) @Max(100) int businessImpact,
                          @Min(0) int dataFreshnessHours) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(String metricName, double zScore, String direction,
                         int anomalyScore, String severity, List<String> actions) {}
}
