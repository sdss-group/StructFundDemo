<template >
  <div>
    <hr />
    <div class="block">
        <el-form ref="form" :inline="true" :model="form" label-width="80px" size="medium">
            <el-row>
            <el-form-item label="登记机构" prop="registerCode">
              <el-select v-model="form.registerCode" style="width:203px">
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
            <el-form-item>
                <el-button type="primary" @click="findFundSaleChannelPage()" plain>查询</el-button>
                <el-button @click="resetForm('form')" >重置</el-button>
                <el-button @click="addFundSaleChannel()" type="primary">新增</el-button>
                <el-button @click="modifyFundSaleChannel()" type="primary">修改</el-button>
                <el-popconfirm
                icon="el-icon-info"
                iconColor="red"
                @onConfirm="deleteFundSaleChannel()"
                title="确定删除选中的数据吗？"
                >
                <el-button slot="reference" type="danger">删除</el-button>
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
    <el-table-column prop="updateDate" label="操作日期"  align="center"></el-table-column>
    <el-table-column prop="userId" label="操作人"  align="center"></el-table-column>
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
  <fundSaleChannelDetail ref="fundSaleChannelDetail"></fundSaleChannelDetail>
  </div>
</template>

<script>
import {arrToMap, dateFormat} from '@/utils/commonUtil'
import fundSaleChannelDetail from './fundSaleChannelDetail'

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
        pageSize: 10,
      

      },
      fundList: [],
      registerCodeList: [
        {value: '新致', label: '新致'},
        {value: '其他', label: '其他'},
      ],
      multipleSelection: [],
      allowEdit: true,
      allowDelete: true
    }
  },
  methods: {
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    findFundSaleChannelPage () {
      let _this = this
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundSaleChannel/findFundSaleChannelPage',
        data: this.$qs.stringify(this.form)
        
      }).then((result) => {
        _this.fundList = result.data.pageList
        _this.totalRows = result.data.totalCount
      }).catch((error) => {
        console.log(error)
      })
    },
    dateFormat: dateFormat,
    handleCurrentChange (current) {
      this.form.currentPage = current
      this.findFundSaleChannelPage()
    },
    openDetail (row, column, event) {
      this.$refs.fundSaleChannelDetail.show(row, 'detail')
    },
    addFundSaleChannel () {
      this.$refs.fundSaleChannelDetail.show({}, 'add')
    },
    modifyFundSaleChannel () {
      this.$refs.fundSaleChannelDetail.show(this.multipleSelection[0], 'modify')
    },
    deleteFundSaleChannel () {
      console.log(this.multipleSelection)
      let _this = this
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundSaleChannel/deleteFundSaleChannel',
        data: this.multipleSelection 
      }).then((result) => {
        _this.fundList = result.data.pageList
        _this.totalRows = result.data.totalCount
        _this.findFundSaleChannelPage()
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
     fundSaleChannelDetail
  },
  created () {
    this.findFundSaleChannelPage()
  }
}
</script>

<style scoped>
@import "../../static/css/common.css";
</style>
