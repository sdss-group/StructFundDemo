<template >
  <div>
    <hr />
    <div class="block">
        <el-form ref="form" :inline="true" :model="form" label-width="80px" size="medium">
            <el-row>
            <el-form-item label="登记机构" prop="registerCode">
              <el-select v-model="form.fundType" style="width:203px">
                <el-option
                v-for="item in registerCodeList"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="产品代码" prop="fundCode">
                <el-input  v-model="form.fundCode"/>
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
                <el-button @click="addFundSale()" type="primary">新增</el-button>
                <el-button @click="modifyFundSale()" type="primary" v-bind:disabled="allowEdit">修改</el-button>
                <!-- <el-button @click="deleteFundSale()" type="danger" v-bind:disabled="allowDelete" >删除</el-button> -->
                <el-popconfirm
                icon="el-icon-info"
                iconColor="red"
                @onConfirm="deleteFundSale()"
                title="确定删除选中的数据吗？"
                >
                <el-button slot="reference" type="danger" v-bind:disabled="allowDelete">删除</el-button>
                </el-popconfirm>
            </el-form-item>
            </el-row>
    </el-form>
  </div>
  <hr/>
  <div class="block">
    <el-table :data="fundList"
    border stripe style="width: 100%"
    @row-dblclick="openDetail"
    @selection-change="handleSelectionChange">
    >
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column fixed prop="registerCode" label="登记机构"  align="center"></el-table-column>
    <el-table-column fixed prop="fundCode" label="产品代码"  align="center"></el-table-column>
    <el-table-column prop="fundName" label="产品名称"  align="center"></el-table-column>
    <el-table-column prop="charge" label="是否收费"  align="center">
      <template slot-scope="scope">
        <span v-if="arrToMap($param.charge).get(scope.row.charge)">{{arrToMap($param.charge).get(scope.row.charge)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="delayedEntry" label="是否延时入账"  align="center">
      <template slot-scope="scope">
        <span v-if="arrToMap($param.delayedEntry).get(scope.row.delayedEntry)">{{arrToMap($param.delayedEntry).get(scope.row.delayedEntry)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="subsCapType" label="认购期扣账方式"  align="center">
      <template slot-scope="scope">
        <span v-if="arrToMap($param.subsCapType).get(scope.row.subsCapType)">{{arrToMap($param.subsCapType).get(scope.row.subsCapType)}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="purchCapType" label="申购期扣账方式"  align="center">
    <template slot-scope="scope">
      <span v-if="arrToMap($param.purchCapType).get(scope.row.purchCapType)">{{arrToMap($param.purchCapType).get(scope.row.purchCapType)}}</span>
    </template>
    </el-table-column>
    <el-table-column prop="minBala" label="最低募集金额"  align="center"></el-table-column>
    <el-table-column prop="maxBala" label="最高募集金额"  align="center"></el-table-column>
    <el-table-column prop="incomeType" label="收益类型"  align="center">
    <template slot-scope="scope">
      <span v-if="arrToMap($param.incomeType).get(scope.row.incomeType)">{{arrToMap($param.incomeType).get(scope.row.incomeType)}}</span>
    </template>
    </el-table-column>
    <el-table-column prop="operator" label="操作人"  align="center"></el-table-column>
    <el-table-column prop="authorizer" label="授权人"  align="center"></el-table-column>
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
  <fundSaleDetailForm ref="fundSaleDetailForm"></fundSaleDetailForm>
  </div>
</template>

<script>
import {arrToMap, dateFormat} from '@/utils/commonUtil'
import fundSaleDetailForm from './fundSaleDetailForm'

export default {
  name: 'fundSaleset',
  data () {
    return {
      totalRows: 0,
      detailVisible: false,
      form: {
        registerCode: '',
        fundCode: '',
        fundType: '',
        ipoStartDate: '',
        currentPage: 1,
        ipoEndDate: '',
        pageSize: 10

      },
      fundList: [],
      registerCodeList: {value: 'ZX', label: '中信'},
      multipleSelection: [],
      allowEdit: true,
      allowDelete: true
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
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundSale/queryFundSaleInfo',
        data: this.$qs.stringify(this.form)
      }).then((result) => {
        _this.fundList = result.data.dataList
        console.log(result.data.dataList[0].tradeEnd)
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
      this.$refs.fundSaleDetailForm.show(row, 'detail')
    },
    addFundSale () {
      this.$refs.fundSaleDetailForm.show({}, 'add')
    },
    modifyFundSale () {
      this.$refs.fundSaleDetailForm.show(this.multipleSelection[0], 'modify')
    },
    deleteFundSale () {
      console.log(this.multipleSelection)
      let _this = this
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundSale/deleteFundSale',
        data: this.multipleSelection
      }).then((result) => {
        _this.fundList = result.data.dataList
        _this.totalRows = result.data.totalRows
        _this.query()
      }).catch((error) => {
        console.log(error)
      })
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
      if (this.multipleSelection.length == 1) {
        this.allowEdit = false
        this.allowDelete = false
      } else if (this.multipleSelection.length > 1) {
        this.allowEdit = true
        this.allowDelete = false
      } else {
        this.allowEdit = true
        this.allowDelete = true
      }
    },
    arrToMap: arrToMap
  },
  components: {
    fundSaleDetailForm
  }
}
</script>

<style scoped>
@import "../../static/css/common.css";
</style>
