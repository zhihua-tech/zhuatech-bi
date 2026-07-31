<!-- Copyright 2026 上海如静知华信息科技有限公司 -->
<script setup>import StatusBadge from '../../components/StatusBadge.vue';import {expenses} from '../../api/mock'</script>
<template><div class="employee-mobile"><header><div class="mobile-brand"><span>ZH</span><div><b>知华数据</b><small>ZHUATECH BI</small></div></div><button>♢</button></header><main>
<section class="mobile-welcome"><p>7 月 31 日 · 星期五</p><h1>下午好，温书屿</h1><span>你关注的经营指标和分析订阅已更新</span></section>
<section class="expense-wallet"><header><span>我的经营指标</span><button>查看看板 →</button></header><strong>18<small> 项</small></strong><div><span><b>14 项</b><small>状态正常</small></span><span><b>3 项</b><small>需要关注</small></span><span><b>1 项</b><small>触发预警</small></span></div></section>
<section class="quick-actions"><button><i>＋</i><span>新建分析</span><small>拖拽生成图表</small></button><button><i>↗</i><span>订阅报表</span><small>每日定时推送</small></button><button><i>▤</i><span>指标收藏</span><small>18 项已收藏</small></button></section>
<section class="mobile-section"><header><h2>最近看板</h2><button>全部 8 个 →</button></header><article v-for="x in expenses.slice(0,3)" :key="x.no"><span class="expense-icon">{{x.category[0]}}</span><div><b>{{x.purpose}}</b><small>{{x.date}} · {{x.category}} · {{x.no}}</small></div><aside><b>{{x.amount}}</b><StatusBadge :value="x.status"/></aside></article></section>
<section class="policy-tip"><i>i</i><div><b>指标异常提醒</b><p>制造主题“准时交付率”低于目标值 2.6%，请关注三项延期订单。</p></div><button>查看分析</button></section>
</main><nav><a class="active"><i>⌂</i><span>首页</span></a><a><i>▤</i><span>看板</span></a><button>＋</button><a><i>◎</i><span>指标</span></a><a><i>◇</i><span>我的</span></a></nav></div></template>
