/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.repository;
import cn.zhuatech.bi.model.DataJob;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface DataJobRepository extends JpaRepository<DataJob,Long>{List<DataJob> findAllByOrderByDueDateAsc();}
