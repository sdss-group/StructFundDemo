<template >
  <div>
    <hr />
    <div class="block">
        <el-form ref="form" :inline="true" :model="form" label-width="80px" size="medium">
            <el-row>
            <el-form-item label="登记机构" prop="registerCode">
          <el-select v-model="form.registerCode" style="width:203px">
            <el-option
              v-for="item in $param.registerCode"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
          
        </el-form-item>
            <!-- <el-form-item label="产品代码" prop="fundCode">
                <el-input  v-model="form.fundCode"/>
            </el-form-item> -->
             <el-form-item label="产品代码" prop="fundCode">
          <el-select v-model="form.fundCode" style="width:203px">
            <el-option
              v-for="item in $param.fundCode"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
          
        </el-form-item>
            </el-row>
            <el-row>
            <el-form-item>
                <el-button type="primary" @click="query()" plain>查询</el-button>
                <el-button @click="resetForm('form')" >重置</el-button>
                 <el-button @click="costParamAdd()" type="primary">新增</el-button>
                <el-button @click="modifyCostParam()" type="primary" v-bind:disabled="allowEdit">修改</el-button>
                
                
             <el-popconfirm
                icon="el-icon-info"
                iconColor="red"
                @onConfirm="deleteCostParam()"
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
    <el-table :data="fundList" border stripe style="width: 100%" @row-dblclick="openDetail"  @selection-change="handleSelectionChange" >
   <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column fixed prop="registration" label="登记机构"  align="center"></el-table-column>
    <el-table-column fixed prop="fundCode" label="产品代码"  align="center"></el-table-column>
    <el-table-column fixed prop="registerCode" label="登记机构代码"  align="center"></el-table-column>
    <el-table-column fixed prop="paymentMethod" label="认购费支付方式"  align="center"></el-table-column>
    <el-table-column fixed prop="subscriptionRates" label="认购费率"  align="center"></el-table-column>
    <el-table-column fixed prop="subscriptionFee" label="申购费支付方式"  align="center"></el-table-column>
    <el-table-column fixed prop="subscriptionRate" label="申购费率"  align="center"></el-table-column>
    <el-table-column fixed prop="redemptionFee" label="赎回费支付方式"  align="center"></el-table-column>
    <el-table-column fixed prop="redemptionRate" label="赎回费率"  align="center"></el-table-column>
    <el-table-column fixed prop="salesFee" label="销售费支付方式"  align="center"></el-table-column>
    <el-table-column fixed prop="salesProcedureRate" label="销售手续费率"  align="center"></el-table-column>
    <el-table-column fixed prop="managementFeePayment" label="管理费支付方式"  align="center"></el-table-column>
    <el-table-column fixed prop="managementFeeRate" label="管理费率"  align="center"></el-table-column>
    <el-table-column fixed prop="excessPerformanceCompenstation" label="超额业绩报酬支付方式"  align="center"></el-table-column>
    <el-table-column fixed prop="proportion" label="分成比例"  align="center"></el-table-column>
    <el-table-column fixed prop="finalMaintainer" label="最后维护人"  align="center"></el-table-column>
    <el-table-column fixed prop="finalUpdateTime" label="最后更新时间"  align="center" :formatter="dateFormat"></el-table-column>
 <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="50"
        label="操作">
        <template slot-scope="scope">
            <el-button type="text" size="medium" @click="deleteHandle(scope.row)"><i class="el-icon-delete"></i></el-button>
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
  <costparametersDetail ref="costparametersDetail"></costparametersDetail>
  </div>
</template>

<script>
import {dateFormat} from '@/utils/commonUtil'
import costparametersDetail from './costparametersDetail'


export default {
  name: 'costparam',
  data () {
    return {
      totalRows: 0,
      detailVisible: false,
      form: {
        registerCode: '',
        fundCode: '',
        fundType: '', 
        currentPage: 1,
        pageSize: 10

      },
      fundList: [],
      allowEdit: true,
      multipleSelection: [],
      allowDelete: true
    }
  },
    created () {
        this.query()
     },
  methods: {
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    query () {
      let _this = this
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/costParameters/costSelect',
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
     
    costParamAdd () {
      this.$refs.costparametersDetail.show({}, 'add')
    },
    modifyCostParam(){
      this.$refs.costparametersDetail.show(this.multipleSelection[0], 'modify')

    },
    //批量 删除
      deleteCostParam (data) {
      let _this = this
       this.$confirm(`确定对所选项进行[${this.multipleSelection.length === 1 ? ' 删除 ' : ' 批量删除 '}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
      this.$ajax({
        method: 'post',
        headers: {'Content-Type': 'application/json;charset=UTF-8'},
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/costParameters/costDeleteAll',
        data: JSON.stringify(this.multipleSelection)
      }).then((response) => {
        this.$message({
            message:response.data.result,
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.query()
            }
          })
      }).catch((error) => {
        console.log(error)
      })
      })
    },
       
    deleteHandle (rows) {
      this.$confirm(`确定对所选项进行[${rows.length === 1 ? ' 删除 ' : ' 删除 '}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax({
          method: 'post',
          headers: {'Content-Type': 'application/json;charset=UTF-8'},
          url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/costParameters/costDelete',
          data: JSON.stringify(rows),
          traditional: true
        }).then((response) => {
          this.$message({
            message:response.data.result,
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.query()
            }
          })
        })
      })
    },
    openDetail (row, column, event) {
      this.$refs.costparametersDetail.show(row, 'detail')
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
   costparametersDetail
  }
}
</script>
