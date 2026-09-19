/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity
@Table(name="bi_data_job")
public class DataJob extends BaseEntity {
    @Column(nullable=false,unique=true,length=32) private String dataJobNo;
    @Column(nullable=false,length=100) private String supplierName;
    @Column(nullable=false,length=100) private String sourceDocument;
    @Column(nullable=false,precision=16,scale=2) private BigDecimal amount;
    @Column(nullable=false,precision=16,scale=2) private BigDecimal paidAmount;
    @Column(nullable=false) private LocalDate dueDate;
    @Column(nullable=false,length=40) private String applicant;
    @Column(nullable=false,length=20) private String status;

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    protected DataJob() {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DataJob(String no,String supplier,String source,BigDecimal amount,BigDecimal paid,LocalDate due,String applicant,String status){this.dataJobNo=no;this.supplierName=supplier;this.sourceDocument=source;this.amount=amount;this.paidAmount=paid;this.dueDate=due;this.applicant=applicant;this.status=status;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String getDataJobNo(){return dataJobNo;} /**
                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                     */
public String getSupplierName(){return supplierName;} /**
                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                           */
public String getSourceDocument(){return sourceDocument;} /**
                                                                                                                                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                     */
public BigDecimal getAmount(){return amount;} /**
                                                                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                   */
public BigDecimal getPaidAmount(){return paidAmount;} /**
                                                                                                                                                                                                                                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                         */
public LocalDate getDueDate(){return dueDate;} /**
                                                                                                                                                                                                                                                                                                                        * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                        */
public String getApplicant(){return applicant;} /**
                                                                                                                                                                                                                                                                                                                                                                        * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                        */
public String getStatus(){return status;}
}
