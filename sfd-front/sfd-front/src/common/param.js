
const fundType = [
  {value: '1', label: '现金管理类产品'},
  {value: '2', label: '净值性开放产品'},
  {value: '3', label: '固定期限产品'}
]
const saleObject = [
  {value: '1', label: '个人'},
  {value: '2', label: '对公(公司)'},
  {value: '3', label: '同业'},
  {value: '4', label: '集团客户'},
  {value: '5', label: '信用卡待挖潜客户'}
]
const ipoType = [
  {value: '0', label: '公募'},
  {value: '1', label: '私募'}
]
const tradeType = [
  {value: '0', label: '开放式'},
  {value: '1', label: '半封闭式'},
  {value: '2', label: '封闭式'},
  {value: '3', label: '半开放式'}
]
const redeReturnType = [
  {value: '0', label: '实时返还'},
  {value: '1', label: 'T日返还'},
  {value: '2', label: '期末返还'}
]
const bonusType = [
  {value: '0', label: '红利再投资'},
  {value: '1', label: '现金红利'}
]
const interProperty = [
  {value: '0', label: '不计息'},
  {value: '1', label: '计息'}
]
const shareClass = [
  {value: '0', label: '前收费'},
  {value: '1', label: '后收费'}
]
const cashFlag = [
  {value: '0', label: '现钞'},
  {value: '1', label: '现汇'},
  {value: '9', label: '人民币或不区分'}
]
const breachRede = [
  {value: '0', label: '不允许'},
  {value: '0', label: '允许'}
]

const param = {
  fundType,
  saleObject,
  ipoType,
  tradeType,
  redeReturnType,
  bonusType,
  interProperty,
  shareClass,
  cashFlag,
  breachRede
}

export default param
