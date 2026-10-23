<!-- 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ -->
<script setup>
import { ref, computed, onBeforeUnmount } from 'vue'
const open=ref(false), busy=ref(false), error=ref(''), result=ref(null)
const title="存量收入留存桥接分析", description="区分存量扩张、降购、流失和新增收入，计算NRR与GRR，避免把新增客户收入混入存量留存分子。", sample={"customers":[{"id":"A","opening":100,"closing":120},{"id":"B","opening":100,"closing":0},{"id":"C","opening":0,"closing":500}]}
const input=ref(JSON.stringify(sample,null,2)), tokenKey="zhuatech-csm-token"
const labels={"id":"编号","status":"状态","type":"类型","name":"指标","openingRecurringRevenue":"期初经常性收入","cohortClosingRevenue":"原客户期末收入","ratesDefined":"留存率可计算","nrrPercent":"净收入留存（%）","grrPercent":"毛收入留存（%）","expansion":"增购","contraction":"缩减","churn":"流失","newRevenue":"新客收入","customers":"客户收入桥接","opening":"期初收入","closing":"期末收入","movement":"变动类型"}
const label=key=>labels[key]||key
let controller
const summaries=computed(()=>result.value?Object.entries(result.value).filter(([,v])=>!Array.isArray(v)&&typeof v!=='object'):[])
const lists=computed(()=>result.value?Object.entries(result.value).filter(([,v])=>Array.isArray(v)):[])
const columns=rows=>[...new Set(rows.flatMap(row=>row&&typeof row==='object'&&!Array.isArray(row)?Object.keys(row):['value']))].sort()
const cell=(row,key)=>row&&typeof row==='object'&&!Array.isArray(row)?row[key]:row
const format=value=>value===null||value===undefined?'—':typeof value==='object'?JSON.stringify(value):typeof value==='boolean'?(value?'是':'否'):String(value)
/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
function reset(){if(busy.value)return;input.value=JSON.stringify(sample,null,2);result.value=null;error.value=''}
/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
async function run(){
 if(busy.value)return
 error.value='';result.value=null
 try{
  if(new TextEncoder().encode(input.value).length>131072)throw Error('输入超过128 KiB，请分批处理')
  let payload
  try{payload=JSON.parse(input.value)}catch{throw Error('输入JSON格式错误，请检查引号和逗号')}
  if(!payload||Array.isArray(payload)||typeof payload!=='object')throw Error('请输入JSON对象')
  const token=localStorage.getItem(tokenKey)
  if(token==='demo-token')throw Error('当前为演示模式，请登录真实后端后使用本功能')
  const headers={'Content-Type':'application/json'}
  if(!token)throw Error('请先登录真实后端后使用本功能')
  headers.Authorization='Bearer '+token
  busy.value=true;controller=new AbortController()
  const timer=setTimeout(()=>controller.abort(),15000)
  let response
  try{response=await fetch((import.meta.env.VITE_API_BASE_URL||'/api').replace(/\/$/,'')+'/workbench/revenue-retention-bridge',{method:'POST',headers,body:JSON.stringify(payload),signal:controller.signal})}finally{clearTimeout(timer)}
  let body
  try{body=await response.json()}catch{throw Error('后端未返回有效JSON，请检查服务或代理配置')}
  if(!response.ok||body.success!==true)throw Error(response.status===401?'登录已失效或凭据不正确，请重新登录':body.message||'请求失败：'+response.status)
  result.value=body.data
 }catch(e){error.value=e.name==='AbortError'?'请求超时，请稍后重试':e.message||'处理失败'}
 finally{busy.value=false;controller=null}
}
/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
function download(){if(!result.value)return;const url=URL.createObjectURL(new Blob([JSON.stringify(result.value,null,2)],{type:'application/json;charset=utf-8'}));const a=document.createElement('a');a.href=url;a.download='revenue-retention-bridge-result.json';a.click();setTimeout(()=>URL.revokeObjectURL(url),1000)}
onBeforeUnmount(()=>controller?.abort())
</script>
<template>
 <div class="ai-tool-entry"><button type="button" @click="open=!open" :aria-expanded="open" aria-controls="ai-practical-tool">业务计算工具</button></div>
 <section v-if="open" id="ai-practical-tool" class="ai-tool-workspace" role="region" :aria-label="title" @keydown.esc="open=false" tabindex="-1">
  <header><div><h2>{{title}}</h2><p>{{description}}</p></div><button type="button" @click="open=false" aria-label="关闭业务计算工具">关闭</button></header>
  <p class="ai-tool-notice">只处理本次提交的数据，不写入业务记录、不调用外部模型。输入示例是合成数据。沿用当前账号JWT；不会保存额外账号或密码。</p>
  <form @submit.prevent="run">
   <label class="ai-tool-editor">请求参数 JSON<textarea v-model="input" rows="13" maxlength="131072" spellcheck="false" :disabled="busy"></textarea></label>
   <div class="ai-tool-actions"><button type="submit" :disabled="busy">{{busy?'正在处理…':'执行计算'}}</button><button type="button" @click="reset" :disabled="busy">恢复示例</button><button type="button" @click="download" :disabled="!result||busy">导出完整结果</button></div>
  </form>
  <p v-if="error" role="alert" class="ai-tool-error">{{error}}</p>
  <div v-if="result" aria-live="polite"><h3>计算结果</h3><dl class="ai-tool-summary"><template v-for="[k,v] in summaries" :key="k"><dt>{{label(k)}}</dt><dd>{{format(v)}}</dd></template></dl>
   <section v-for="[key,rows] in lists" :key="key"><h4>{{label(key)}}（{{rows.length}}）</h4><p v-if="rows.length===0">无记录</p><div v-else class="ai-tool-table"><table><thead><tr><th v-for="k in columns(rows.slice(0,100))" :key="k">{{label(k)}}</th></tr></thead><tbody><tr v-for="(row,index) in rows.slice(0,100)" :key="index"><td v-for="k in columns(rows.slice(0,100))" :key="k">{{format(cell(row,k))}}</td></tr></tbody></table></div><p v-if="rows.length>100">当前显示前100行，导出包含完整{{rows.length}}行。</p></section>
  </div><p v-else-if="!busy&&!error" class="ai-tool-empty">尚无计算结果。可恢复示例后执行，或按接口文档输入自己的数据。</p>
 </section>
</template>
<style scoped>
.ai-tool-entry{position:fixed;right:24px;bottom:18px;z-index:1100}.ai-tool-entry button,.ai-tool-workspace button{border:1px solid #bbc6d2;border-radius:3px;background:#fff;color:#24384d;font:inherit;padding:8px 14px;cursor:pointer;white-space:nowrap;flex-shrink:0}.ai-tool-entry button{background:#233f5e;color:white;border-color:#233f5e}.ai-tool-workspace{position:fixed;inset:24px 24px 60px auto;width:min(1000px,calc(100vw - 48px));box-sizing:border-box;z-index:1200;background:white;border:1px solid #aebdca;box-shadow:0 6px 24px #13253633;overflow:auto;padding:22px;color:#233446;font:13px/1.6 system-ui,sans-serif}.ai-tool-workspace header{display:flex;align-items:flex-start;justify-content:space-between;gap:20px;border-bottom:1px solid #dfe5eb;padding-bottom:12px}.ai-tool-workspace h2{font-size:19px;margin:0 0 6px}.ai-tool-workspace h3{font-size:16px}.ai-tool-workspace h4{font-size:14px;margin:18px 0 6px}.ai-tool-workspace p{margin:6px 0}.ai-tool-notice{padding:10px 0;color:#5d6c7d}.ai-tool-credentials{display:flex;gap:16px;margin:12px 0}.ai-tool-credentials label{flex:1}.ai-tool-workspace input,.ai-tool-workspace textarea{display:block;box-sizing:border-box;width:100%;border:1px solid #bac5d1;border-radius:2px;padding:8px;font:inherit;color:#20344b;background:#fff}.ai-tool-workspace textarea{font:12px/1.6 ui-monospace,monospace;resize:vertical;min-height:180px}.ai-tool-actions{display:flex;gap:10px;margin:12px 0;flex-wrap:wrap}.ai-tool-actions button:first-child{background:#274f75;color:white}.ai-tool-workspace button:disabled{opacity:.5;cursor:not-allowed}.ai-tool-error{color:#a32d2b;background:#fff2f1;border-left:3px solid #a32d2b;padding:10px}.ai-tool-summary{display:grid;grid-template-columns:minmax(180px,1fr) 2fr;margin:0}.ai-tool-summary dt,.ai-tool-summary dd{overflow-wrap:anywhere;min-width:0;margin:0;padding:6px 10px;border-bottom:1px solid #e0e6ec}.ai-tool-table{overflow:auto;max-height:400px;border:1px solid #dfe5eb}.ai-tool-table table{width:100%;border-collapse:collapse}.ai-tool-table th,.ai-tool-table td{text-align:left;border-bottom:1px solid #e1e6eb;padding:8px;max-width:380px;overflow-wrap:anywhere;vertical-align:top}.ai-tool-table th{position:sticky;top:0;background:#f0f3f6}.ai-tool-empty{color:#687889;padding:18px 0}@media(max-width:640px){.ai-tool-workspace{inset:8px 8px 60px;width:auto;padding:14px}.ai-tool-credentials{flex-direction:column}.ai-tool-summary{grid-template-columns:1fr 1fr}.ai-tool-entry{right:12px}}
</style>
