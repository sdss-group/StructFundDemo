<template>
<el-dialog v-bind:title="title" :visible.sync="dialogFormVisible" width="60%">
  <el-form :model="data" :inline="true" v-bind:disabled="isDetail" label-width="150px">
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
                <el-input-number v-model="data.maxRedeem" controls-position="right" :min="0" :max="1" :step=0.1 style="width:203px"></el-input-number>
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
                <el-input-number v-model="data.interDate" controls-position="right" :min="0" :max="365" :step=1 style="width:203px"></el-input-number>
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
            <el-form-item label="产品代码" prop="fundCode">
                <el-input  v-model="data.fundCode" />
            </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="产品名称" prop="fundName">
                <el-input  v-model="data.fundName" />
            </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="产品状态" prop="fundStatus">
                <el-select v-model="data.fundStatus" >
                  <el-option
                  v-for="item in this.$param.fundStatus"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
            </el-form-item>
            </el-col>
          </el-row>
          <el-row :span=24 style="margin-top: 15px">
            <el-col :span="8" >
              <el-form-item label="产品单位净值" prop="registerCode">
                  <el-input  :value="data.registerCode" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="产品代码" prop="fundCode">
                <el-input  v-model="data.fundCode" />
            </el-form-item>
            </el-col>
          </el-row>
      </el-tab-pane>
      <el-tab-pane label="角色管理">角色管理</el-tab-pane>
      <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>
    </el-tabs>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
  </div>
</el-dialog>

</template>
<script>
import { numberLimit } from '@/utils/commonUtil'
export default {
  data () {
    return {
      dialogFormVisible: false,
      title: '',
      data: [],
      formLabelWidth: '120px',
      isDetail: false
    }
  },
  methods: {
    show (data, title) {
      this.dialogFormVisible = true
      this.data = data
      this.title = title
    },
    numberLimit: numberLimit
  }
}
</script>
