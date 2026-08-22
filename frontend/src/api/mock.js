/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
export const accounts=[
  {code:'DS-ERP-PROD',name:'ERP 生产库',bank:'MySQL 8 · 经营与财务主题',type:'数据库',balance:286.45,available:278.83,status:'正常'},
  {code:'DS-CRM-CLOUD',name:'CRM 业务数据',bank:'REST API · 客户与商机主题',type:'API',balance:128.62,available:128.62,status:'正常'},
  {code:'DS-MES-REALTIME',name:'MES 实时数据',bank:'Kafka · 生产执行主题',type:'消息流',balance:18.64,available:17.56,status:'正常'},
  {code:'DS-HR-FILE',name:'人力月度快照',bank:'SFTP · 组织与人员主题',type:'文件',balance:76.20,available:72.4,status:'维护中'}]
export const receivables=[
  {no:'KPI-REV-001',customer:'营业收入',source:'财务主题 · 月度经营口径',amount:860,received:735,due:'07-31',owner:'顾清禾',status:'正常'},
  {no:'KPI-PIPE-006',customer:'销售漏斗',source:'CRM 主题 · 加权商机口径',amount:1260,received:982,due:'07-31',owner:'许知遥',status:'正常'},
  {no:'KPI-OTD-018',customer:'订单准时交付率',source:'供应链主题 · OTIF 口径',amount:96.8,received:94.2,due:'07-31',owner:'林清越',status:'预警'},
  {no:'KPI-GPM-009',customer:'项目毛利率',source:'项目主题 · 完工百分比口径',amount:38.5,received:36.1,due:'07-31',owner:'唐予安',status:'正常'},
  {no:'KPI-OEE-023',customer:'综合设备效率',source:'制造主题 · OEE 口径',amount:85.0,received:82.6,due:'07-31',owner:'苏景行',status:'待复核'}]
export const payables=[
  {no:'JOB-2607-112',supplier:'ERP 日增量同步',source:'ODS · 经营主题',amount:186.5,paid:186.5,due:'02:15',applicant:'江叙',status:'已完成'},
  {no:'JOB-2607-108',supplier:'CRM 商机快照',source:'DWD · 销售主题',amount:98,paid:98,due:'03:20',applicant:'苏景行',status:'已完成'},
  {no:'JOB-2607-119',supplier:'MES 班次汇总',source:'DWS · 制造主题',amount:286.4,paid:264.8,due:'每 15 分钟',applicant:'温书屿',status:'运行中'},
  {no:'JOB-2607-126',supplier:'管理驾驶舱刷新',source:'ADS · 管理主题',amount:65,paid:0,due:'08:00',applicant:'陆嘉言',status:'待运行'}]
export const expenses=[
  {no:'ANL-260731-034',person:'温书屿',dept:'咨询交付部',category:'客户分析',purpose:'重点客户收入与回款贡献分析',amount:'12 个指标',date:'07-31',status:'编辑中'},
  {no:'ANL-260731-029',person:'江叙',dept:'研发中心',category:'研发分析',purpose:'研发投入与版本交付效率分析',amount:'8 个指标',date:'07-30',status:'已发布'},
  {no:'ANL-260730-086',person:'顾呈',dept:'产品中心',category:'产品分析',purpose:'产品收入结构和毛利变化分析',amount:'16 个指标',date:'07-29',status:'已发布'},
  {no:'ANL-260729-061',person:'唐予安',dept:'数据服务部',category:'质量分析',purpose:'主数据完整率与问题闭环分析',amount:'6 个指标',date:'07-28',status:'待复核'}]
export const budgets=[
  {dept:'销售主题域',subject:'收入、客户与商机',annual:320,occupied:36,actual:284,rate:88.8,status:'正常'},
  {dept:'供应链主题域',subject:'采购、库存与履约',annual:268,occupied:28,actual:246,rate:91.8,status:'正常'},
  {dept:'制造主题域',subject:'产量、质量与设备',annual:120,occupied:16.8,actual:94,rate:78.3,status:'预警'},
  {dept:'财务主题域',subject:'收入、成本与现金流',annual:96,occupied:9.2,actual:92,rate:95.8,status:'正常'},
  {dept:'人力主题域',subject:'组织、人员与效能',annual:148,occupied:12.5,actual:136,rate:91.9,status:'正常'}]
export const cashflow=[92,118,105,136,124,158,145,172,151,188,176,204]
