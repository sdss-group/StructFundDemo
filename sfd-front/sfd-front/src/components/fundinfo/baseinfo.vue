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
                    v-for="item in this.$param.fundType"
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
                value-format="yyyy-MM-dd">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="认购止日" prop="ipoEndDate">
                <el-date-picker style="width:203px"
                v-model="form.ipoEndDate"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
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
    <el-table :data="fundList" border stripe style="width: 100%" @row-dblclick="openDetail" >
    <el-table-column fixed prop="registerCode" label="登记机构"  align="center"></el-table-column>
    <el-table-column fixed prop="fundCode" label="产品代码"  align="center"></el-table-column>
    <el-table-column prop="fundName" label="产品名称"  align="center"></el-table-column>
    <el-table-column prop="fundType" label="产品类型"  align="center">
      <template slot-scope="scope">
        <span v-if="arrToMap($param.fundType).get(scope.row.fundType)">{{arrToMap($param.fundType).get(scope.row.fundType)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="nav" label="产品单位净值"  align="center"></el-table-column>
    <el-table-column prop="fundStatus" label="产品状态"  align="center">
      <template slot-scope="scope">
        <span v-if="$param.fundStatus[scope.row.fundStatus]" >{{ $param.fundStatus[scope.row.fundStatus].label }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="ipoStartDate" label="认购起日"  align="center" :formatter="dateFormat"></el-table-column>
    <el-table-column prop="ipoEndDate" label="认购止日"  align="center" :formatter="dateFormat"></el-table-column>
    <el-table-column prop="setupDate" label="成立日期"  align="center" :formatter="dateFormat"></el-table-column>
    <el-table-column prop="shareClass" label="收费方式"  align="center">
      <template slot-scope="scope">
        <span v-if="arrToMap($param.shareClass).get(scope.row.shareClass)">{{arrToMap($param.shareClass).get(scope.row.shareClass)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="tradeType" label="交易方式"  align="center">
      <template slot-scope="scope">
        <span v-if="arrToMap($param.tradeType).get(scope.row.tradeType)">{{arrToMap($param.tradeType).get(scope.row.tradeType)}}</span>
      </template>
    </el-table-column>
  </el-table>
  </div>
  <div class="block">
    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="form.currentPage"
      layout="total, prev, pager, next, jumper"
      :total="totalRows">
    </el-pagination>
  </div>
  <baseinfoDetail ref="baseinfoDetail"></baseinfoDetail>
  </div>
</template>

<script>
import {arrToMap, dateFormat} from '@/utils/commonUtil'
import baseinfoDetail from './baseinfoDetail'

export default {
  name: 'baseinfo',
  data () {
    return {
      totalRows: 0,
      detailVisible: false,
      form: {
        registerCode: 'ZX',
        fundCode: '',
        fundType: '',
        ipoStartDate: '',
        currentPage: 1,
        ipoEndDate: '',
        pageSize: 10

      },
      fundList: []
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
        _this.fundList = result.data.dataList
        _this.totalRows = result.data.totalRows
      }).catch((error) => {
        console.log(error)
      })
    },
    dateFormat: dateFormat,
    handleCurrentChange (current) {
      this.form.currentPage = current
      this.query()
    },
    openDetail (row, column, event) {
      this.$refs.baseinfoDetail.show(row, '产品信息查看')
    },
    arrToMap: arrToMap
  },
  components: {
    baseinfoDetail
  }
}
</script>
