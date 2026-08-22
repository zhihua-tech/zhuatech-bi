/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.service;

import cn.zhuatech.bi.common.BusinessException;
import cn.zhuatech.bi.dto.BiDto.*;
import cn.zhuatech.bi.model.*;
import cn.zhuatech.bi.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(readOnly=true)
public class BiService {
    private final DataSourceRepository accounts; private final BusinessMetricRepository businessMetrics; private final DataJobRepository dataJobs; private final AnalysisWorkbookRepository expenses; private final SubjectAreaRepository subjectAreas;
    public BiService(DataSourceRepository accounts,BusinessMetricRepository businessMetrics,DataJobRepository dataJobs,AnalysisWorkbookRepository expenses,SubjectAreaRepository subjectAreas){this.accounts=accounts;this.businessMetrics=businessMetrics;this.dataJobs=dataJobs;this.expenses=expenses;this.subjectAreas=subjectAreas;}

    public Dashboard dashboard(){
        var accountList=accounts.findAll(); var businessMetricList=businessMetrics.findAllByOrderByDueDateAsc(); var dataJobList=dataJobs.findAllByOrderByDueDateAsc(); var expenseList=expenses.findAllByOrderByExpenseDateDesc(); var subjectAreaList=subjectAreas.findByFiscalYearOrderByDepartmentAsc(LocalDate.now().getYear());
        BigDecimal cash=sum(accountList.stream().map(DataSource::getBalance).toList());
        BigDecimal available=sum(accountList.stream().map(DataSource::getAvailableBalance).toList());
        BigDecimal ar=sum(businessMetricList.stream().map(x->x.getAmount().subtract(x.getReceivedAmount())).toList());
        BigDecimal overdue=sum(businessMetricList.stream().filter(x->x.getDueDate().isBefore(LocalDate.now())&&!"已结清".equals(x.getStatus())).map(x->x.getAmount().subtract(x.getReceivedAmount())).toList());
        BigDecimal ap=sum(dataJobList.stream().map(x->x.getAmount().subtract(x.getPaidAmount())).toList());
        BigDecimal pending=sum(expenseList.stream().filter(x->"待审批".equals(x.getStatus())||"待付款".equals(x.getStatus())).map(AnalysisWorkbook::getAmount).toList());
        BigDecimal annual=sum(subjectAreaList.stream().map(SubjectArea::getAnnualAmount).toList()); BigDecimal actual=sum(subjectAreaList.stream().map(SubjectArea::getActualAmount).toList());
        BigDecimal rate=annual.signum()==0?BigDecimal.ZERO:actual.multiply(new BigDecimal("100")).divide(annual,1,RoundingMode.HALF_UP);
        return new Dashboard(cash,available,ar,overdue,ap,pending,rate,businessMetricList.stream().limit(6).map(BusinessMetricView::from).toList(),dataJobList.stream().limit(6).map(DataJobView::from).toList());
    }
    private BigDecimal sum(List<BigDecimal> values){return values.stream().reduce(BigDecimal.ZERO,BigDecimal::add);}
    public List<AccountView> accounts(){return accounts.findAll().stream().map(AccountView::from).toList();}
    public List<BusinessMetricView> businessMetrics(){return businessMetrics.findAllByOrderByDueDateAsc().stream().map(BusinessMetricView::from).toList();}
    public List<DataJobView> dataJobs(){return dataJobs.findAllByOrderByDueDateAsc().stream().map(DataJobView::from).toList();}
    public List<ExpenseView> expenses(){return expenses.findAllByOrderByExpenseDateDesc().stream().map(ExpenseView::from).toList();}
    public List<SubjectAreaView> subjectAreas(){return subjectAreas.findByFiscalYearOrderByDepartmentAsc(LocalDate.now().getYear()).stream().map(SubjectAreaView::from).toList();}
    @Transactional public BusinessMetricView createBusinessMetric(CreateBusinessMetricRequest request){if(businessMetrics.findByBusinessMetricNo(request.businessMetricNo()).isPresent())throw new BusinessException("应收单号已存在");return BusinessMetricView.from(businessMetrics.save(new BusinessMetric(request.businessMetricNo(),request.customerName(),request.sourceDocument(),request.amount(),BigDecimal.ZERO,request.dueDate(),request.owner(),"待收款")));}
    @Transactional public BusinessMetricView recordReceipt(Long id,RecordReceiptRequest request){var item=businessMetrics.findById(id).orElseThrow(()->new BusinessException("应收记录不存在"));if("已结清".equals(item.getStatus()))throw new BusinessException("该应收已结清");if(item.getReceivedAmount().add(request.amount()).compareTo(item.getAmount())>0)throw new BusinessException("收款金额不能超过剩余应收");item.recordReceipt(request.amount());return BusinessMetricView.from(item);}
    @Transactional public ExpenseView submitExpense(SubmitExpenseRequest request){String no="BX-"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));return ExpenseView.from(expenses.save(new AnalysisWorkbook(no,request.claimant(),request.department(),request.category(),request.purpose(),request.amount(),request.expenseDate(),"待审批")));}
}
