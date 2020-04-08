<template>
<el-dialog v-if='dialogFormVisible' v-bind:title="title" :visible.sync="dialogFormVisible" width="60%">
  <el-form :model="data" :inline="true" v-bind:disabled="isDetail" label-width="180px">
    <el-tabs type="border-card" >
      <el-tab-pane label="产品基本信息">

        <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="登记机构代码" prop="registerCode">
                  <el-input  v-model="data.registerCode" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="理财产品代码" prop="fundCode">
                <el-input  v-model="data.fundCode" />
            </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="理财产品名称" prop="fundName">
                <el-input  v-model="data.fundName" />
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8">
            <el-form-item label="产品类型" prop="fundType">
                <el-select v-model="data.fundType" style="width:203px">
                  <el-option
                  v-for="item in this.$param.fundType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
            </el-form-item>
            </el-col>
            <el-col :span="8" >
              <el-form-item label="销售对象" prop="saleObject">
                <el-select v-model="data.saleObject" style="width:203px">
                  <el-option
                  v-for="item in this.$param.saleObject"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="产品募集方式" prop="ipoType">
              <el-select v-model="data.ipoType" style="width:203px">
                  <el-option
                  v-for="item in this.$param.ipoType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8">
            <el-form-item label="交易方式" prop="tradeType">
              <el-select v-model="data.tradeType" style="width:203px">
                  <el-option
                  v-for="item in this.$param.tradeType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
            </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="赎回本金返还方式" prop="redeReturnType">
                <el-select v-model="data.redeReturnType" style="width:203px">
                  <el-option
                  v-for="item in this.$param.redeReturnType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
            </el-form-item>
            </el-col>
            <el-col :span="8" >
              <el-form-item label="默认分红方式" prop="bonusType">
                <el-select v-model="data.bonusType" style="width:203px">
                  <el-option
                  v-for="item in this.$param.bonusType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8">
              <el-form-item label="认购起日" prop="ipoStartDate">
                <el-date-picker style="width:203px"
                v-model="data.ipoStartDate"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="认购止日" prop="ipoEndDate">
                <el-date-picker style="width:203px"
                v-model="data.ipoEndDate"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="成立日期" prop="setupDate">
                <el-date-picker style="width:203px"
                v-model="data.setupDate"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8">
              <el-form-item label="封闭结束时间" prop="alimitDay">
                <el-date-picker style="width:203px"
                v-model="data.alimitDay"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="巨额赎回比例" prop="maxRedeem">
                <el-input-number v-model="data.maxRedeem" :min="0" :max="1" :controls="false" :step=0.0001 style="width:203px" step-strictly></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="计息属性" prop="interProperty">
                <el-select v-model="data.interProperty" style="width:203px">
                  <el-option
                  v-for="item in this.$param.interProperty"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8">
              <el-form-item label="计息天数" prop="interDate">
                <el-input-number v-model="data.interDate" controls-position="right" :min="0" :max="365" :step=1 style="width:203px" step-strictly></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="认购计息天数" prop="incomeInterDate">
                <el-select v-model="data.incomeInterDate" style="width:203px">
                  <el-option
                  v-for="item in this.$param.incomeInterDate"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="收费方式" prop="shareClass">
                <el-select v-model="data.shareClass" style="width:203px">
                  <el-option
                  v-for="item in this.$param.shareClass"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8">
              <el-form-item label="币种" prop="currencyType">
                <el-input v-model="data.currencyType" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钞汇标志" prop="cashFlag">
                <el-select v-model="data.cashFlag" style="width:203px">
                  <el-option
                  v-for="item in this.$param.cashFlag"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="收益币种" prop="incomeCurrType">
                <el-input v-model="data.incomeCurrType" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8">
              <el-form-item label="本金返还币种" prop="costCurrType">
                <el-input v-model="data.costCurrType" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="产品发行价格" prop="issuePrice">
                <el-input v-model="data.issuePrice" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="理财登记编码" prop="fundRegistCode">
                <el-input v-model="data.fundRegistCode" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8">
              <el-form-item label="允许违约赎回标志" prop="breachRede">
                <el-select v-model="data.breachRede" style="width:203px">
                  <el-option
                  v-for="item in this.$param.breachRede"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
              </el-form-item>
            </el-col>
          </el-row>
      </el-tab-pane>

      <el-tab-pane label="行情信息" >
          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="登记机构代码" prop="registerCode">
                  <el-input  v-model="data.registerCode" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="理财产品代码" prop="fundCode">
                <el-input  v-model="data.fundCode" />
            </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="理财产品名称" prop="fundName">
                <el-input  v-model="data.fundName" />
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span=24 style="margin-top: 15px">
            <el-col :span="8">
            <el-form-item label="产品状态" prop="fundStatus">
                <el-select v-model="data.fundStatus" style="width:203px">
                  <el-option
                  v-for="item in this.$param.fundStatus"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
            </el-form-item>
            </el-col>
            <el-col :span="8" >
              <el-form-item label="产品单位净值" prop="registerCode">
                  <el-input  :value="data.registerCode" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="收益率/净值公告日期" prop="psDate">
              <el-date-picker style="width:203px"
                v-model="data.psDate"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span=24 style="margin-top: 15px">
            <el-col :span="8">
            <el-form-item label="分红日/发放日" prop="dividentDate">
              <el-date-picker style="width:203px"
                v-model="data.dividentDate"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
            </el-col>
            <el-col :span="8" >
              <el-form-item label="除权日" prop="xrDate">
                <el-date-picker style="width:203px"
                  v-model="data.xrDate"
                  type="date"
                  format="yyyy 年 MM 月 dd 日"
                  value-format="yyyy-MM-dd">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="权益登记日期" prop="registDate">
              <el-date-picker style="width:203px"
                v-model="data.registDate"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span=24 style="margin-top: 15px">
            <el-col :span="8">
            <el-form-item label="个人发行方式" prop="indiIssueType">
              <el-select v-model="data.indiIssueType" style="width:203px">
                <el-option
                v-for="item in this.$param.indiIssueType"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            </el-col>
            <el-col :span="8" >
              <el-form-item label="机构发行方式" prop="instIssueType">
                <el-select v-model="data.instIssueType" style="width:203px">
                  <el-option
                  v-for="item in this.$param.instIssueType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="认购方式" prop="subsType">
              <el-select v-model="data.subsType" style="width:203px">
                <el-option
                v-for="item in this.$param.subsType"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span=24 style="margin-top: 15px">
            <el-col :span="8">
            <el-form-item label="交易费收取方式" prop="collectFeeType">
                <el-select v-model="data.collectFeeType" style="width:203px">
                  <el-option
                  v-for="item in this.$param.collectFeeType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
            </el-form-item>
            </el-col>
            <el-col :span="8" >
              <el-form-item label="交易价格" prop="tradPrice">
                  <el-input  :value="data.tradPrice" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="下一开放日" prop="nextTradeDate">
              <el-date-picker style="width:203px"
                v-model="data.nextTradeDate"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span=24 style="margin-top: 15px">
            <el-col :span="8">
            <el-form-item label="净值类型" prop="netValueType">
                <el-select v-model="data.netValueType" style="width:203px">
                  <el-option
                  v-for="item in this.$param.netValueType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
            </el-form-item>
            </el-col>
            <el-col :span="8" >
              <el-form-item label="累计单位净值" prop="accumulativeNav">
                  <el-input  :value="data.accumulativeNav" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="累计单位分红" prop="totalDivident">
              <el-input  :value="data.totalDivident" />
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span=24 style="margin-top: 15px">
            <el-col :span="8">
            <el-form-item label="保本净值" prop="guarantNav">
              <el-input  :value="data.guarantNav" />
            </el-form-item>
            </el-col>
            <el-col :span="8" >
              <el-form-item label="万份理财产品单位收益" prop="fundIncome">
                  <el-input  :value="data.fundIncome" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="理财产品七日年化收益率" prop="yield">
              <el-input-number v-model="data.yield" controls-position="right" :min="0" :max="1" :step=0.01 style="width:203px"></el-input-number>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span=24 style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="万份理财产品单位收益" prop="fundIncome">
                <el-input-number v-model="data.fundIncome" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="理财产品年化收益率" prop="yearIncome">
              <el-input-number v-model="data.yearIncome" controls-position="right" :min="0" :max="1" :step=0.01 style="width:203px" step-strictly></el-input-number>
            </el-form-item>
            </el-col>
            <el-form-item label="理财产品当日总收益" prop="dayIncome">
              <el-input-number v-model="data.dayIncome" controls-position="right" :min="0" style="width:203px"></el-input-number>
            </el-form-item>
          </el-row>

          <el-row :span=24 style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="产品价值线数值" prop="valueLine">
                <el-input-number v-model="data.valueLine" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="理财产品转换状态" prop="convertStatus">
              <el-select v-model="data.convertStatus" style="width:203px">
                  <el-option
                  v-for="item in this.$param.convertStatus"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
            </el-form-item>
            </el-col>
            <el-form-item label="定期定额状态" prop="periodicStatus">
              <el-select v-model="data.periodicStatus" style="width:203px">
                  <el-option
                  v-for="item in this.$param.periodicStatus"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
            </el-form-item>
          </el-row>

          <el-row :span=24 style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="转托管状态" prop="transferAgenStatus">
                <el-select v-model="data.transferAgenStatus" style="width:203px">
                  <el-option
                  v-for="item in this.$param.transferAgenStatus"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="公告标志" prop="announcFlag">
              <el-select v-model="data.announcFlag" style="width:203px">
                  <el-option
                  v-for="item in this.$param.announcFlag"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
            </el-form-item>
            </el-col>
          </el-row>

      </el-tab-pane>
      <el-tab-pane label="交易限制信息">

        <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="登记机构代码" prop="registerCode">
                  <el-input  v-model="data.registerCode" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="理财产品代码" prop="fundCode">
                <el-input  v-model="data.fundCode" />
            </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="理财产品名称" prop="fundName">
                <el-input  v-model="data.fundName" />
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="产品最低持有份额" prop="indiMinBalance">
                  <el-input-number v-model="data.indiMinBalance" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="当日实时赎回份额上限" prop="maxRedeDay">
                <el-input-number v-model="data.maxRedeDay" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="定时定额申购金额上限" prop="maxPeriodSub">
              <el-input-number v-model="data.maxPeriodSub" controls-position="right" :min="0" style="width:203px"></el-input-number>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="个人首次最低认购份数" prop="indiUnitSubsVol">
                  <el-input-number v-model="data.indiUnitSubsVol" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="个人首次最低认购金额" prop="indiUnitSubsAmt">
                <el-input-number v-model="data.indiUnitSubsAmt" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="个人追加认购份数" prop="indiAddSubsVol">
              <el-input-number v-model="data.indiAddSubsVol" controls-position="right" :min="0" style="width:203px"></el-input-number>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="个人追加认购金额" prop="indiAddSubsAmt">
                  <el-input-number v-model="data.indiAddSubsAmt" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="个人最高认购份数" prop="indiMaxSubsVol">
                <el-input-number v-model="data.indiMaxSubsVol" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="个人最高认购金额" prop="indiMaxSubsAmt">
              <el-input-number v-model="data.indiMaxSubsAmt" controls-position="right" :min="0" style="width:203px"></el-input-number>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="个人认购份数单位" prop="indiUnitSubsVol">
                  <el-input-number v-model="data.indiUnitSubsVol" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="个人认购金额单位" prop="indiUnitSubsAmt">
                <el-input-number v-model="data.indiUnitSubsAmt" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="个人首次申购最低金额" prop="indiMinBidsAmt">
              <el-input-number v-model="data.indiMinBidsAmt" controls-position="right" :min="0" style="width:203px"></el-input-number>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="个人追加申购金额" prop="indiAddBidsAmt">
                  <el-input-number v-model="data.indiAddBidsAmt" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="个人最高申购金额" prop="indiMaxBidsAmt">
                <el-input-number v-model="data.indiMaxBidsAmt" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="个人当日累计购买最大金额" prop="indiDayMaxBuy">
              <el-input-number v-model="data.indiDayMaxBuy" controls-position="right" :min="0" style="width:203px"></el-input-number>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="个人当日累计赎回最大份额" prop="indiAddBidsAmt">
                  <el-input-number v-model="data.indiAddBidsAmt" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="个人最低赎回份数" prop="indiMinRedeVol">
                <el-input-number v-model="data.indiDayMaxRede" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="个人最大赎回份额" prop="indiMaxRede">
              <el-input-number v-model="data.indiMaxRede" controls-position="right" :min="0" style="width:203px"></el-input-number>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="个人最低产品转换份额" prop="indiMinConvVol">
                  <el-input-number v-model="data.indiMinConvVol" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="个人最低定期定额投资份额" prop="indiMinPs">
                <el-input-number v-model="data.indiMinPs" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="个人单人单日快速赎回上限" prop="indiMaxRedeDayFast">
              <el-input-number v-model="data.indiMaxRedeDayFast" controls-position="right" :min="0" style="width:203px"></el-input-number>
            </el-form-item>
            </el-col>
          </el-row>

          <el-row :span="24" style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="个人对私赎回基数" prop="indiRedeemBase">
                  <el-input-number v-model="data.indiRedeemBase" controls-position="right" :min="0" style="width:203px"></el-input-number>
              </el-form-item>
            </el-col>
          </el-row>

      </el-tab-pane>
      <el-tab-pane label="其它信息" >
        <el-row :span="24" style="margin-top: 15px">
          <el-col :span="8" >
            <el-form-item label="登记机构代码" prop="registerCode">
                  <el-input  v-model="data.registerCode" />
              </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="理财产品代码" prop="fundCode">
                <el-input  v-model="data.fundCode" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="理财产品名称" prop="fundName">
                <el-input  v-model="data.fundName" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :span="24" style="margin-top: 15px">
          <el-col :span="8" >
            <el-form-item label="产品简称" prop="fundNameAbbr">
                  <el-input  v-model="data.fundNameAbbr" />
              </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品发起人" prop="fundSponsor">
                <el-input  v-model="data.fundSponsor" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品面值" prop="faceValue">
                <el-input  v-model="data.faceValue" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :span="24" style="margin-top: 15px">
          <el-col :span="8" >
            <el-form-item label="本金保障标识" prop="breakEven">
              <el-select v-model="data.breakEven" style="width:203px">
                <el-option
                v-for="item in this.$param.breakEven"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
              </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="收益保障标识" prop="benefitGuarantee">
              <el-select v-model="data.benefitGuarantee" style="width:203px">
                <el-option
                v-for="item in this.$param.benefitGuarantee"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="托管人代码" prop="fundTrustee">
                <el-input  v-model="data.fundTrustee" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :span="24" style="margin-top: 15px">
          <el-col :span="8" >
            <el-form-item label="中债产品划分" prop="debtFundType">
              <el-select v-model="data.debtFundType" style="width:203px">
                <el-option
                v-for="item in this.$param.debtFundType"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="允许销售的中债客户组别" prop="clientGroup">
              <el-select v-model="data.clientGroup" style="width:203px">
                <el-option
                v-for="item in this.$param.clientGroup"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="购买（认购）撤单" prop="cancelType">
              <el-select v-model="data.cancelType" style="width:203px">
                <el-option
                v-for="item in this.$param.cancelType"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :span="24" style="margin-top: 15px">
          <el-col :span="8" >
            <el-form-item label="违约赎回费率" prop="breaRedeFee">
                  <el-input  v-model="data.breaRedeFee" />
              </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="允许部分赎回" prop="partRede">
              <el-select v-model="data.partRede" style="width:203px">
                <el-option
                v-for="item in this.$param.partRede"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="超额分层比例" prop="excessiveRatio">
                <el-input  v-model="data.excessiveRatio" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :span="24" style="margin-top: 15px">
          <el-col :span="8" >
            <el-form-item label="认购利息利率" prop="interestRate">
                  <el-input  v-model="data.interestRate" />
              </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="赎回费归理财资产比例" prop="redeFeeBackRatio">
              <el-input  v-model="data.redeFeeBackRatio" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="锁定期天数" prop="lockDays">
                <el-input  v-model="data.lockDays" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :span="24" style="margin-top: 15px">
          <el-col :span="8" >
            <el-form-item label="是否校验合格投资者" prop="isCheckInvester">
                  <el-input  v-model="data.interestRate" />
              </el-form-item>
          </el-col>
        </el-row>
        
      </el-tab-pane>
    </el-tabs>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button type="primary" @click="close">确 定</el-button>
  </div>
</el-dialog>

</template>
<script>
export default {
  data () {
    return {
      dialogFormVisible: false,
      title: '',
      data: [],
      formLabelWidth: '120px',
      isDetail: true
    }
  },
  methods: {
    show (data, title) {
      this.dialogFormVisible = true
      this.data = data
      this.title = title
    },
    close () {
      this.dialogFormVisible = false
    }
  }
}
</script>
