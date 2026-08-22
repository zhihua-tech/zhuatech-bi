/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bi.repository;
import cn.zhuatech.bi.model.SubjectArea;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface SubjectAreaRepository extends JpaRepository<SubjectArea,Long>{List<SubjectArea> findByFiscalYearOrderByDepartmentAsc(int fiscalYear);}
