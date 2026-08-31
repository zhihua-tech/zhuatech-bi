/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CertifiedMetricReleaseServiceTest {
    private final CertifiedMetricReleaseService service = new CertifiedMetricReleaseService();

    @Test void releasesAControlledMetric() {
        var result = service.assess(new CertifiedMetricReleaseService.Request(
            "GMV", "finance-owner", true, true, true, 10, 30, 5, 10, 0));
        assertThat(result.decision()).isEqualTo(CertifiedMetricReleaseService.Decision.RELEASE);
        assertThat(result.certified()).isTrue();
    }

    @Test void blocksStaleOrUncontrolledMetrics() {
        var result = service.assess(new CertifiedMetricReleaseService.Request(
            "MARGIN", "finance-owner", true, false, true, 90, 30, 0, 10, 1));
        assertThat(result.decision()).isEqualTo(CertifiedMetricReleaseService.Decision.BLOCKED);
        assertThat(result.blockers()).hasSize(3);
    }

    @Test void routesReconciliationVarianceToReview() {
        var result = service.assess(new CertifiedMetricReleaseService.Request(
            "REVENUE", "finance-owner", true, true, true, 5, 30, 20, 10, 0));
        assertThat(result.decision()).isEqualTo(CertifiedMetricReleaseService.Decision.REVIEW);
    }
}
