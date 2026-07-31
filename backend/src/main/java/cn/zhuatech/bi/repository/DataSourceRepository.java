/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bi.repository;
import cn.zhuatech.bi.model.DataSource;import org.springframework.data.jpa.repository.JpaRepository;import java.util.Optional;
public interface DataSourceRepository extends JpaRepository<DataSource,Long>{Optional<DataSource> findByAccountCode(String accountCode);}
