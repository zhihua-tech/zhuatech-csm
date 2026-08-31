# 企业级客户升级事件治理

[知华科技（上海如静知华信息科技有限公司）](https://www.zhuatech.cn/)为 CSM 开源版增加重大客户事件治理。

`POST /api/enterprise/csm/customer-escalation-governance` 检查客户责任人、高层责任人、客户沟通、影响遏制、恢复 SLA、根因责任和恢复计划，返回 `PROCEED / EXECUTIVE_REVIEW / BLOCKED`。

企业落地时建议与服务台、状态通知和客户健康度联动，并保存事件时间线、审批意见和客户沟通证据。
