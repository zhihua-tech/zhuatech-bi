/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi;

import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.http.MediaType;import org.springframework.test.web.servlet.MockMvc;import java.time.LocalDate;import java.util.regex.*;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@SpringBootTest @AutoConfigureMockMvc
class BiApiIntegrationTests {
    @Autowired MockMvc mvc;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private String login(String username,String password)throws Exception{String json=mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\""+username+"\",\"password\":\""+password+"\"}")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();Matcher m=Pattern.compile("\\\"token\\\":\\\"([^\\\"]+)\\\"").matcher(json);if(!m.find())throw new AssertionError("登录响应中缺少 token");return m.group(1);}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void adminCanReadAnalyticsDashboard()throws Exception{String token=login("admin","admin123");mvc.perform(get("/api/bi/dashboard").header("Authorization","Bearer "+token)).andExpect(status().isOk()).andExpect(jsonPath("$.data.cashBalance").isNumber()).andExpect(jsonPath("$.data.upcomingBusinessMetrics").isArray());mvc.perform(get("/api/bi/data-sources").header("Authorization","Bearer "+token)).andExpect(status().isOk()).andExpect(jsonPath("$.data.length()").value(3));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void analystCanCreateAndUpdateBusinessMetric()throws Exception{String token=login("finance","finance123");String body="{\"businessMetricNo\":\"AR-TEST-001\",\"customerName\":\"测试客户\",\"sourceDocument\":\"测试合同\",\"amount\":10000,\"dueDate\":\""+LocalDate.now().plusDays(5)+"\",\"owner\":\"测试经营\"}";String json=mvc.perform(post("/api/bi/business-metrics").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isOk()).andExpect(jsonPath("$.data.status").value("待收款")).andReturn().getResponse().getContentAsString();Matcher id=Pattern.compile("\\\"id\\\":(\\d+)").matcher(json);if(!id.find())throw new AssertionError("响应缺少 id");mvc.perform(patch("/api/bi/business-metrics/"+id.group(1)+"/value").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content("{\"amount\":10000}")).andExpect(status().isOk()).andExpect(jsonPath("$.data.status").value("已结清"));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void employeeCanSubmitExpense()throws Exception{String token=login("employee","employee123");String body="{\"claimant\":\"业务员工\",\"department\":\"产品中心\",\"category\":\"交通费\",\"purpose\":\"客户访谈交通\",\"amount\":128,\"expenseDate\":\""+LocalDate.now()+"\"}";mvc.perform(post("/api/bi/analysis-workbooks").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isOk()).andExpect(jsonPath("$.data.status").value("待审批"));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void analystCanExplainMetricAnomaly()throws Exception{String token=login("finance","finance123");mvc.perform(post("/api/bi/metric-anomaly").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content("{\"metricName\":\"订单取消率\",\"currentValue\":150,\"baselineValue\":100,\"standardDeviation\":10,\"businessImpact\":80,\"dataFreshnessHours\":2}")).andExpect(status().isOk()).andExpect(jsonPath("$.data.zScore").value(5.0)).andExpect(jsonPath("$.data.direction").value("UP")).andExpect(jsonPath("$.data.anomalyScore").value(100)).andExpect(jsonPath("$.data.severity").value("CRITICAL"));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void anonymousRequestIsDenied()throws Exception{mvc.perform(get("/api/bi/data-sources")).andExpect(status().isForbidden());}
}
