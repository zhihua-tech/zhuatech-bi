/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.repository;
import cn.zhuatech.bi.model.DataSource;import org.springframework.data.jpa.repository.JpaRepository;import java.util.Optional;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
public interface DataSourceRepository extends JpaRepository<DataSource,Long>{/**
                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                              */
Optional<DataSource> findByAccountCode(String accountCode);}
