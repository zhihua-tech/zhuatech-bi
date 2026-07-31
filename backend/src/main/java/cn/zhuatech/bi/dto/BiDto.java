/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bi.dto;

import cn.zhuatech.bi.model.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public final class BiDto {
    private BiDto() {}
    public record AccountView(Long id,String accountCode,String accountName,String bankName,String accountType,BigDecimal balance,BigDecimal availableBalance,String currency,String status){public static AccountView from(DataSource x){return new AccountView(x.getId(),x.getAccountCode(),x.getAccountName(),x.getBankName(),x.getAccountType(),x.getBalance(),x.getAvailableBalance(),x.getCurrency(),x.getStatus());}}
    public record BusinessMetricView(Long id,String businessMetricNo,String customerName,String sourceDocument,BigDecimal amount,BigDecimal receivedAmount,LocalDate dueDate,String owner,String status){public static BusinessMetricView from(BusinessMetric x){return new BusinessMetricView(x.getId(),x.getBusinessMetricNo(),x.getCustomerName(),x.getSourceDocument(),x.getAmount(),x.getReceivedAmount(),x.getDueDate(),x.getOwner(),x.getStatus());}}
    public record DataJobView(Long id,String dataJobNo,String supplierName,String sourceDocument,BigDecimal amount,BigDecimal paidAmount,LocalDate dueDate,String applicant,String status){public static DataJobView from(DataJob x){return new DataJobView(x.getId(),x.getDataJobNo(),x.getSupplierName(),x.getSourceDocument(),x.getAmount(),x.getPaidAmount(),x.getDueDate(),x.getApplicant(),x.getStatus());}}
    public record ExpenseView(Long id,String claimNo,String claimant,String department,String category,String purpose,BigDecimal amount,LocalDate expenseDate,String status){public static ExpenseView from(AnalysisWorkbook x){return new ExpenseView(x.getId(),x.getClaimNo(),x.getClaimant(),x.getDepartment(),x.getCategory(),x.getPurpose(),x.getAmount(),x.getExpenseDate(),x.getStatus());}}
    public record SubjectAreaView(Long id,String subjectAreaNo,String department,String subjectName,Integer fiscalYear,BigDecimal annualAmount,BigDecimal occupiedAmount,BigDecimal actualAmount,String status){public static SubjectAreaView from(SubjectArea x){return new SubjectAreaView(x.getId(),x.getSubjectAreaNo(),x.getDepartment(),x.getSubjectName(),x.getFiscalYear(),x.getAnnualAmount(),x.getOccupiedAmount(),x.getActualAmount(),x.getStatus());}}
    public record Dashboard(BigDecimal cashBalance,BigDecimal availableCash,BigDecimal businessMetricAmount,BigDecimal overdueBusinessMetric,BigDecimal dataJobAmount,BigDecimal expensePending,BigDecimal subjectAreaExecutionRate,List<BusinessMetricView> upcomingBusinessMetrics,List<DataJobView> upcomingDataJobs){}
    public record CreateBusinessMetricRequest(@NotBlank String businessMetricNo,@NotBlank @Size(max=100) String customerName,@NotBlank String sourceDocument,@NotNull @Positive BigDecimal amount,@NotNull @FutureOrPresent LocalDate dueDate,@NotBlank String owner){}
    public record RecordReceiptRequest(@NotNull @Positive BigDecimal amount){}
    public record SubmitExpenseRequest(@NotBlank String claimant,@NotBlank String department,@NotBlank String category,@NotBlank @Size(max=160) String purpose,@NotNull @Positive BigDecimal amount,@NotNull @PastOrPresent LocalDate expenseDate){}
}
