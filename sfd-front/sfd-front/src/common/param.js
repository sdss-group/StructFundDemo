
const fundType = [// 产品类型
  {value: '1', label: '现金管理类产品'},
  {value: '2', label: '净值性开放产品'},
  {value: '3', label: '固定期限产品'}
]
const saleObject = [// 销售对象
  {value: '1', label: '个人'},
  {value: '2', label: '对公(公司)'},
  {value: '3', label: '同业'},
  {value: '4', label: '集团客户'},
  {value: '5', label: '信用卡待挖潜客户'}
]
const ipoType = [// 产品募集方式
  {value: '0', label: '公募'},
  {value: '1', label: '私募'}
]
const tradeType = [// 交易方式
  {value: '0', label: '开放式'},
  {value: '1', label: '半封闭式'},
  {value: '2', label: '封闭式'},
  {value: '3', label: '半开放式'}
]
const redeReturnType = [// 赎回本金返还方式
  {value: '0', label: '实时返还'},
  {value: '1', label: 'T日返还'},
  {value: '2', label: '期末返还'}
]
const bonusType = [// 默认分红方式
  {value: '0', label: '红利再投资'},
  {value: '1', label: '现金红利'}
]
const interProperty = [// 计息属性
  {value: '0', label: '不计息'},
  {value: '1', label: '计息'}
]
const shareClass = [// 收费方式
  {value: '0', label: '前收费'},
  {value: '1', label: '后收费'}
]
const cashFlag = [// 钞汇标志
  {value: '0', label: '现钞'},
  {value: '1', label: '现汇'},
  {value: '9', label: '人民币或不区分'}
]
const breachRede = [// 允许违约赎回标志
  {value: '0', label: '不允许'},
  {value: '1', label: '允许'}
]

const lotStatus = [
  {value: '0', label: '初始化', type: 'info'},
  {value: '1', label: '正在执行', type: 'warning'},
  {value: '2', label: '执行成功', type: 'success'},
  {value: '3', label: '执行失败', type: 'danger'}
]

const fundStatus = [// 产品状态
  {value: '0', label: '正常开放'},
  {value: '1', label: '认购期'},
  {value: '3', label: '发行失败'},
  {value: '4', label: '暂停交易'},
  {value: '5', label: '暂停申购'},
  {value: '6', label: '暂停赎回'},
  {value: '7', label: '理财产品封闭'},
  {value: 'a', label: '理财产品终止'},
  {value: 'b', label: '基金信息未下发'}
]
const incomeInterDate = [// 认购计息天数
  {value: '0', label: '360'},
  {value: '1', label: '365'}
]

const indiIssueType = [// 个人发行方式
  {value: '0', label: '末日比例配售'},
  {value: '1', label: '比例发行'},
  {value: '3', label: '摇号'},
  {value: '4', label: '先来先买'}
]

const instIssueType = [// 机构发行方式
  {value: '0', label: '末日比例配售'},
  {value: '1', label: '比例发行'},
  {value: '3', label: '摇号'},
  {value: '4', label: '先来先买'}
]

const subsType = [// 认购方式
  {value: '0', label: '金额认购'},
  {value: '1', label: '份额认购'}
]

const collectFeeType = [// 交易费收取方式
  {value: '0', label: '价内费'},
  {value: '1', label: '价外费'}
]

const netValueType = [// 净值类型
  {value: '0', label: '普通净值'},
  {value: '1', label: '申购净值'},
  {value: '2', label: '赎回净值'}
]

const fundIncomeFlag = [// 理财产品万份收益正负
  {value: '0', label: '正'},
  {value: '1', label: '负'}
]

const convertStatus = [// 理财产品转换状态
  {value: '0', label: '可转入转出'},
  {value: '1', label: '只可转入'},
  {value: '2', label: '只可转出'},
  {value: '3', label: '不可转换'}
]

const periodicStatus = [// 定期定额状态
  {value: '0', label: '允许定期定额业务'},
  {value: '1', label: '仅允许定投业务'},
  {value: '2', label: '仅允许定赎业务'},
  {value: '3', label: '禁止定期定额业务'}
]

const transferAgenStatus = [// 转托管状态
  {value: '0', label: '允许所有转托管'},
  {value: '1', label: '仅允许场外转托管'},
  {value: '2', label: '仅允许跨市场转托管'},
  {value: '3', label: '禁止所有转托管'}
]

const announcFlag = [// 公告标志
  {value: '0', label: '不公告'},
  {value: '1', label: '公告'}
]

const breakEven = [// 本金保障标识
  {value: '0', label: '否'},
  {value: '1', label: '是'}
]

const benefitGuarantee = [// 收益保障标识
  {value: '0', label: '否'},
  {value: '1', label: '是'}
]

const debtFundType = [// 中债产品划分
  {value: '1', label: '普通个人产品'},
  {value: '2', label: '高净值产品'},
  {value: '3', label: '私行专属'},
  {value: '6', label: '机构专属'},
  {value: '7', label: '同业专属'}
]

const clientGroup = [// 允许销售的中债客户组别
  {value: '1', label: '一般个人客户'},
  {value: '2', label: '高资产净值客户'},
  {value: '3', label: '私人银行客户'},
  {value: '4', label: '银行业金融机构客户'},
  {value: '5', label: '非银行业金融机构客户'},
  {value: '6', label: '法人机构其他'},
  {value: '7', label: '非法人资产管理品'},
  {value: '8', label: '非法人机构'}
]

const cancelType = [// 购买（认购）撤单
  {value: '0', label: '不允许撤单'},
  {value: '1', label: '只允许当日撤单'},
  {value: '2', label: '允许撤单'}
]

const partRede = [// 允许部分赎回
  {value: '0', label: '否'},
  {value: '1', label: '是'}
]

const isCheckInvester = [// 是否校验合格投资者
  {value: '0', label: '不需要'},
  {value: '1', label: '需要'}
]
const moneyTypeAllot = {
  '1': '每天T+N到账',
  '2': '募集结束日+N到账'
}

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
  breachRede,
  lotStatus,
  fundStatus,
  incomeInterDate,
  indiIssueType,
  instIssueType,
  subsType,
  collectFeeType,
  netValueType,
  fundIncomeFlag,
  convertStatus,
  periodicStatus,
  transferAgenStatus,
  announcFlag,
  breakEven,
  benefitGuarantee,
  debtFundType,
  clientGroup,
  cancelType,
  partRede,
  isCheckInvester,
  moneyTypeAllot
}

export default param
