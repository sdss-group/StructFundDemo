<template >
  <div>
    <hr />
    <div class="block">
        <el-form ref="form" :inline="true" :model="form" label-width="80px" size="medium">
            <el-row>
            <el-form-item label="登记机构" prop="registerCode">
                <el-input  :value="form.registerCode" readonly />
            </el-form-item>
            <el-form-item label="产品代码" prop="fundCode">
                <el-input  v-model="form.fundCode"/>
            </el-form-item>
            </el-row>
            <el-row>
            <el-form-item label="产品类型" prop="fundType">
                <el-select v-model="form.fundType" style="width:203px">
                    <el-option
                    v-for="item in param.fundType"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="查询条件">
                <el-input />
            </el-form-item>
            </el-row>
            <el-row>
            <el-form-item label="认购起日" prop="ipoStartDate">
                <el-date-picker style="width:203px"
                v-model="form.ipoStartDate"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyyMMdd">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="认购止日" prop="ipoEndDate">
                <el-date-picker style="width:203px"
                v-model="form.ipoEndDate"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyyMMdd">
                </el-date-picker>
            </el-form-item>
            </el-row>
            <el-row>
            <el-form-item>
                <el-button type="primary" @click="query()" plain>查询</el-button>
                <el-button @click="resetForm('form')" >重置</el-button>
            </el-form-item>
            </el-row>
    </el-form>
  </div>
  <hr/>
  <div class="block">
    <el-table :data="fundList" border stripe style="width: 100%">
    <el-table-column fixed prop="registerCode" label="登记机构" width="150" align="center"></el-table-column>
    <el-table-column fixed prop="fundCode" label="产品代码" width="150" align="center"></el-table-column>
    <el-table-column prop="fundname" label="产品名称" width="150" align="center"></el-table-column>
    <el-table-column prop="fundType" label="产品类型" width="150" align="center">
      <template slot-scope="scope">
        <span>{{ fundType.get(scope.row.fundType) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="saleObject" label="销售对象" width="150" align="center">
      <template slot-scope="scope">
        <span>{{ saleObject.get(scope.row.saleObject) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="ipoType" label="产品募集方式" width="150" align="center">
      <template slot-scope="scope">
        <span>{{ ipoType.get(scope.row.ipoType) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="tradeType" label="交易方式" width="150" align="center">
      <template slot-scope="scope">
        <span>{{ tradeType.get(scope.row.tradeType) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="redeReturnType" label="赎回本金返还方式" width="150" align="center">
      <template slot-scope="scope">
        <span>{{ redeReturnType.get(scope.row.redeReturnType) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="bonusType" label="默认的分红方式" width="150" align="center">
      <template slot-scope="scope">
        <span>{{ bonusType.get(scope.row.bonusType) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="ipoStartDate" label="认购起日" width="150" align="center" :formatter="dateFormat"></el-table-column>
    <el-table-column prop="ipoEndDate" label="认购止日" width="150" align="center" :formatter="dateFormat"></el-table-column>
    <el-table-column prop="setupDate" label="成立日期" width="150" align="center" :formatter="dateFormat"></el-table-column>
    <el-table-column prop="alimitDay" label="封闭结束日期" width="150" align="center" :formatter="dateFormat"></el-table-column>
    <el-table-column prop="maxRedeem" label="巨额赎回比例" width="150" align="center"></el-table-column>
    <el-table-column prop="interProperty" label="计息属性" width="150" align="center">
      <template slot-scope="scope">
        <span>{{ interProperty.get(scope.row.interProperty) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="interDate" label="计息天数" width="150" align="center"></el-table-column>
    <el-table-column prop="incomeInterDate" label="认购计息天数" width="150" align="center"></el-table-column>
    <el-table-column prop="shareClass" label="收费方式" width="150" align="center">
      <template slot-scope="scope">
        <span>{{ shareClass.get(scope.row.shareClass) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="currencyType" label="币种" width="150" align="center"></el-table-column>
    <el-table-column prop="cashFlag" label="钞汇标志" width="150" align="center">
      <template slot-scope="scope">
        <span>{{ cashFlag.get(scope.row.cashFlag) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="incomeCurrType" label="收益币种" width="150" align="center"></el-table-column>
    <el-table-column prop="costCurrType" label="本金返还币种" width="150" align="center"></el-table-column>
    <el-table-column prop="issuePrice" label="产品发行价格" width="150" align="center"></el-table-column>
    <el-table-column prop="fundRegistCode" label="理财登记编码" width="150" align="center"></el-table-column>
    <el-table-column prop="breachRede" label="允许违约赎回标志" width="150" align="center">
      <template slot-scope="scope">
        <span>{{ breachRede.get(scope.row.breachRede) }}</span>
      </template>
    </el-table-column>
  </el-table>
  </div>
  </div>
</template>

<script>
import {arrToMap, dateFormat} from '@/utils/commonUtil'

export default {
  name: 'baseinfo',
  data () {
    return {
      param: this.$param,
      form: {
        registerCode: 'TA',
        fundCode: '',
        fundType: '',
        ipoStartDate: '',
        ipoEndDate: ''

      },
      fundList: [],
      fundType: arrToMap(this.$param.fundType),
      saleObject: arrToMap(this.$param.saleObject),
      ipoType: arrToMap(this.$param.ipoType),
      tradeType: arrToMap(this.$param.tradeType),
      bonusType: arrToMap(this.$param.bonusType),
      redeReturnType: arrToMap(this.$param.redeReturnType),
      interProperty: arrToMap(this.$param.interProperty),
      shareClass: arrToMap(this.$param.shareClass),
      cashFlag: arrToMap(this.$param.cashFlag),
      breachRede: arrToMap(this.$param.breachRede)
    }
  },
  methods: {
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    query () {
      let _this = this
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/baseinfo/queryBaseinfo',
        data: this.$qs.stringify(this.form)
      }).then((result) => {
        console.log(result)
        _this.fundList = result.data
      }).catch((error) => {
        console.log(error)
      })
    },
    dateFormat: dateFormat
  }
}
</script>
