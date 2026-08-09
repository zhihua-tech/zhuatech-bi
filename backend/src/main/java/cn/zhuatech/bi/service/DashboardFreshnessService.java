/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bi.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DashboardFreshnessService {
    public Result evaluate(Request request) {
        DatasetFreshness worst = request.datasets().stream()
            .max(Comparator.comparingDouble(this::freshnessRatio)).orElseThrow();
        List<String> staleDatasets = request.datasets().stream()
            .filter(dataset -> freshnessRatio(dataset) > 1.5 || dataset.consecutiveFailures() > 0)
            .map(DatasetFreshness::datasetName).toList();
        boolean blocked = request.datasets().stream()
            .anyMatch(dataset -> freshnessRatio(dataset) >= 3 || dataset.consecutiveFailures() >= 3);
        String status = blocked ? "BLOCKED" : staleDatasets.isEmpty() ? "HEALTHY" : "DEGRADED";
        int healthScore = (int) Math.round(request.datasets().stream().mapToDouble(dataset -> {
            double latenessPenalty = Math.max(0, freshnessRatio(dataset) - 1) * 30;
            double failurePenalty = dataset.consecutiveFailures() * 10D;
            return Math.max(0, 100 - latenessPenalty - failurePenalty);
        }).average().orElse(0));

        List<String> actions = new ArrayList<>();
        if (blocked) actions.add("阻止仪表盘对外发布并展示数据延迟告警");
        if (!staleDatasets.isEmpty()) actions.add("重跑异常数据集并核对上游连接与调度资源");
        if (staleDatasets.isEmpty()) actions.add("保持当前刷新策略并记录数据血缘检查结果");
        return new Result(request.dashboardCode(), status, healthScore,
            worst.datasetName(), staleDatasets, actions);
    }

    private double freshnessRatio(DatasetFreshness dataset) {
        return (double) dataset.lastSuccessAgeMinutes() / dataset.expectedIntervalMinutes();
    }

    public record Request(@NotBlank String dashboardCode,
                          @NotEmpty List<@Valid DatasetFreshness> datasets) {}

    public record DatasetFreshness(@NotBlank String datasetName,
                                   @Min(0) int lastSuccessAgeMinutes,
                                   @Min(1) int expectedIntervalMinutes,
                                   @Min(0) int consecutiveFailures) {}

    public record Result(String dashboardCode, String status, int healthScore,
                         String worstDataset, List<String> staleDatasets,
                         List<String> actions) {}
}
