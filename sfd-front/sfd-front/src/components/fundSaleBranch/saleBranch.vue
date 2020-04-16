<template >
  <div>
    <hr />
    <div class="block">
        <el-form ref="form" :inline="true" :model="form" label-width="80px" size="medium">
            <el-row>
            <el-form-item label="登记机构" prop="registerCode">
             <el-input  v-model="form.registerCode"/>
            </el-form-item>
            <el-form-item label="产品代码" prop="fundCode">
                <el-input  v-model="form.fundCode"/>
            </el-form-item>
            </el-row>
            <el-row>
            <el-form-item>
                <el-button type="primary" @click="query()" plain>查询</el-button>
                <el-button @click="resetForm('form')" >重置</el-button>
                <el-button @click="modifyFundSale()" type="primary" v-bind:disabled="allowEdit">修改</el-button>
                <!-- <el-button @click="deleteFundSale()" type="danger" v-bind:disabled="allowDelete" >删除</el-button> -->
                <el-popconfirm
                icon="el-icon-info"
                iconColor="red"
                @onConfirm="deleteFundSale()"
                title="确定删除选中的数据吗？"
                >
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
    <el-table-column prop="orgAmts" label="销售分行"  align="center">
   <template slot-scope="scope">
         <ul  v-for="OrgAmt in scope.row.orgAmts.slice(0,3)"><li style="padding-left: 0;list-style: none;">{{OrgAmt.orgName}}</li></ul>
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
  <saleBranchDetail ref="saleBranchDetail"></saleBranchDetail>
  </div>
</template>

<script>
import {dateFormat} from '@/utils/commonUtil'
import saleBranchDetail from './saleBranchDetail'

export default {
  name: 'saleBranch',
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
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/saleBranchDate/getBranchDate',
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
      this.$refs.saleBranchDetail.show(row, 'detail')
    },
    addFundSale () {
      this.$refs.saleBranchDetail.show({}, 'add')
    },
    modifyFundSale () {
      this.$refs.saleBranchDetail.show(this.multipleSelection[0], 'modify')
    },
    deleteFundSale () {
      console.log(this.multipleSelection)
      let _this = this
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundSale/deleteFundSale',
        data: this.$qs.stringify(this.form)
      }).then((result) => {
        _this.fundList = result.data.dataList
        _this.totalRows = result.data.totalRows
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
    }
  },
  components: {
    saleBranchDetail
  }
}
</script>

<style scoped>
@import "../../static/css/common.css";
</style>
