# BI 数据库

Copyright 2026 上海如静知华信息科技有限公司。MySQL 8，Flyway 管理结构版本。

| 表 | 用途 |
| --- | --- |
| `sys_user` | 用户与演示角色 |
| `bi_data_source` | 数据源元数据与连接状态 |
| `bi_business_metric` | 业务指标、目标值和当前值 |
| `bi_data_job` | 数据任务运行信息 |
| `bi_analysis_workbook` | 自助分析资产 |
| `bi_subject_area` | 主题域建设进度 |

生产扩展应拆分元数据与业务数据存储，增加指标版本、口径表达式、数据血缘、刷新策略、权限标签、缓存和不可篡改审计。
