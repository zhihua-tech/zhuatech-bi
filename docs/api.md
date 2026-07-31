# BI API 摘要

Copyright 2026 上海如静知华信息科技有限公司。

登录：`POST /api/auth/login`。其余接口使用 JWT；业务前缀 `/api/bi`。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/dashboard` | 核心经营指标与数据任务摘要 |
| GET | `/data-sources` | 数据源连接状态和处理量 |
| GET / POST | `/business-metrics` | 指标目录与新建指标 |
| PATCH | `/business-metrics/{id}/value` | 更新指标当前值 |
| GET | `/data-jobs` | 数据任务运行记录 |
| GET / POST | `/analysis-workbooks` | 自助分析工作簿 |
| GET | `/subject-areas` | 主题域与覆盖进度 |

演示角色：`ADMIN`、`DATA_ANALYST`、`EMPLOYEE`。生产环境必须增加行列权限、指标口径版本、下载审计、脱敏策略和查询资源配额。
