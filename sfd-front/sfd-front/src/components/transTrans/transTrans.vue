<template>
  <div class="mod-config">
    <!--表格菜单-->
    <el-form :inline="true" :rules="dataRule" :model="dataForm" ref="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item label="登记机构" prop="registerCode">
        <el-select v-model="dataForm.registerCode" @change="queryFundCode(dataForm.registerCode)" clearable>
          <el-option v-for="item in registerCodeList" :key="item.index" :label="item.registerCode"
                     :value="item.registerCode">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="产品代码" prop="fundCode">
        <el-select v-model="dataForm.fundCode" @change="queryAgency(dataForm.fundCode)" clearable>
          <el-option v-for="item in fundCodeList" :key="item.index" :label="item.fundCode"
                     :value="item.fundCode">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button type="primary" @click="resetForm('dataForm')">重置</el-button>
        <el-button type="primary" :disabled="isDisabled" @click="exportExcel()">导出</el-button>
      </el-form-item>
    </el-form>
    <!--表格-->
    <el-table
      :data="dataList"
      v-loading="dataListLoading"
      style="width: 100%;">
      <el-table-column
        prop="registerCode"
        header-align="center"
        align="center"
        label="登记机构">
      </el-table-column>
      <el-table-column
        prop="fundCode"
        header-align="center"
        align="center"
        label="产品代码">
      </el-table-column>
      <el-table-column
        prop="regionCode"
        header-align="center"
        align="center"
        label="分行代码">
      </el-table-column>
      <el-table-column
        prop="orgName"
        header-align="center"
        align="center"
        label="分行名称">
      </el-table-column>
      <el-table-column
        prop="renshenCount"
        header-align="center"
        align="center"
        label="认（申）购申请笔数">
      </el-table-column>
      <el-table-column
        prop="renshenAmt"
        header-align="center"
        align="center"
        label="认（申）购申请金额">
      </el-table-column>
      <el-table-column
        prop="shuhuiCount"
        header-align="center"
        align="center"
        label="赎回申请笔数">
      </el-table-column>
      <el-table-column
        prop="shuhuiVol"
        header-align="center"
        align="center"
        label="赎回申请份额">
      </el-table-column>
      <el-table-column
        prop="currencyType"
        header-align="center"
        align="center"
        label="币种">
      </el-table-column>
      <el-table-column
        prop="aggregationDate"
        header-align="center"
        align="center"
        label="统计日期">
      </el-table-column>
    </el-table>
    <!--分页-->
    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="dataForm.currentPage"
      layout="total, prev, pager, next, jumper"
      :total="totalRows">
    </el-pagination>
    <!--导出表格-->
    <el-table :data="dataListOut" v-loading="dataListLoading" style="width: 100%;" id="out-table" v-show="false">
      <el-table-column prop="registerCode" header-align="center" align="center" label="登记机构"></el-table-column>
      <el-table-column prop="fundCode" header-align="center" align="center" label="产品代码"></el-table-column>
      <el-table-column prop="regionCode" header-align="center" align="center" label="分行代码"></el-table-column>
      <el-table-column prop="orgName" header-align="center" align="center" label="分行名称"></el-table-column>
      <el-table-column prop="renshenCount" header-align="center" align="center" label="认（申）购申请笔数"></el-table-column>
      <el-table-column prop="renshenAmt" header-align="center" align="center" label="认（申）购申请金额"></el-table-column>
      <el-table-column prop="shuhuiCount" header-align="center" align="center" label="赎回申请笔数"></el-table-column>
      <el-table-column prop="shuhuiVol" header-align="center" align="center" label="赎回申请份额"></el-table-column>
      <el-table-column prop="currencyType" header-align="center" align="center" label="币种"></el-table-column>
      <el-table-column prop="aggregationDate" header-align="center" align="center" label="统计日期"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import FileSaver from 'file-saver'
import XLSX from 'xlsx'

export default {
  data () {
    return {
      dataForm: {
        registerCode: '',
        fundCode: '',
        currentPage: 1,
        pageSize: 10
      },
      isDisabled: true,
      totalRows: 0,
      dataList: [],
      dataListOut: [],
      dataListLoading: false,
      registerCodeList: [],
      fundCodeList: [],
      dataRule: {
        registerCode: [
          { required: true, message: '请选择登记机构', trigger: 'blur' }
        ],
        fundCode: [
          { required: true, message: '请选择产品代码', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    this.initAgencyAndProcode()
  },
  methods: {
    getDataList () {
      this.$refs['dataForm'].validate((valid) => {
        this.dataListLoading = true
        this.$ajax({
          method: 'post',
          url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/queryTrans/transTrans',
          data: this.$qs.stringify(this.dataForm)
        }).then((result) => {
          this.dataList = result.data.dataList
          this.dataListOut = result.data.dataListOut
          this.totalRows = result.data.totalRows
          if (result.data.dataList.length > 0) {
            this.isDisabled = false
          } else {
            this.isDisabled = true
          }
        })
        this.dataListLoading = false
      })
    },
    handleCurrentChange (current) {
      this.dataForm.currentPage = current
      this.getDataList()
    },
    // 重置
    resetForm (formName) {
      this.$refs[formName].resetFields()
      this.dataList = []
      this.dataListOut = []
      this.totalRows = 0
      this.isDisabled = true
    },
    // 导出
    exportExcel () {
      let xlsxParam = { raw: true }
      let wb = XLSX.utils.table_to_book(document.querySelector('#out-table'), xlsxParam)
      let wbout = XLSX.write(wb, {
        bookType: 'xlsx',
        bookSST: true,
        type: 'array'
      })
      let time = new Date()
      try {
        FileSaver.saveAs(
          new Blob([wbout], {type: 'application/octet-stream'}),
          '交易类交易' + time.toLocaleDateString() + '.xlsx'
        )
      } catch (e) {
        if (typeof console !== 'undefined') {
          console.log(e, wbout)
        }
      }
      return wbout
    },
    // 登记机构
    async initAgencyAndProcode () {
      this.registerCodeList = []
      this.fundCodeList = []
      let result = await this.$req.queryAllAgencyAndProcode()
      this.registerCodeList = result.data
      this.fundCodeList = result.data
    },
    queryAgency (fundCode) {
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/queryTrans/queryAgencyByFundCode',
        data: this.$qs.stringify({'fundCode': this.dataForm.fundCode})
      }).then((result) => {
        this.dataForm.registerCode = result.data
      })
    },
    queryFundCode (registerCode) {
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/queryTrans/queryFundCodeByAgency',
        data: this.$qs.stringify({'registerCode': this.dataForm.registerCode})
      }).then((result) => {
        this.dataForm.fundCode = result.data[0].fundCode
        this.fundCodeList = result.data
      })
    }
  }
}
</script>

<style scoped>

</style>
