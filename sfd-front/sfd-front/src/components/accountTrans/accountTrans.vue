<template>
  <div class="mod-config">
    <!--表格菜单-->
    <el-form :inline="true" :rules="dataRule" :model="dataForm" ref="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item label="登记机构" prop="registerCode">
        <el-select v-model="dataForm.registerCode" filterable clearable>
          <el-option v-for="item in registerCodeList" :key="item.index" :label="item.registerName"
                     :value="item.registerCode">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button type="primary" @click="resetForm('dataForm')">重置</el-button>
        <el-button type="primary" :disabled="isDisabled" @click="exportExcel()">导出</el-button>
      </el-form-item>
    </el-form>
    <!--展示表格-->
    <el-table
      :data="dataList"
      v-loading="dataListLoading"
      style="width: 100%;">
      <el-table-column
        prop="registerName"
        header-align="center"
        align="center"
        label="登记机构">
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
        prop="transAccount"
        header-align="center"
        :formatter="amtFormat"
        align="center"
        label="交易帐户开户申请笔数">
      </el-table-column>
      <el-table-column
        prop="fundAccount"
        header-align="center"
        :formatter="amtFormat"
        align="center"
        label="理财账户开户申请笔数">
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
      <el-table-column prop="registerName" header-align="center" align="center" label="登记机构"></el-table-column>
      <el-table-column prop="regionCode" header-align="center" align="center" label="分行代码"></el-table-column>
      <el-table-column prop="orgName" header-align="center" align="center" label="分行名称"></el-table-column>
      <el-table-column prop="transAccount" header-align="center" align="center" label="交易帐户开户申请笔数"></el-table-column>
      <el-table-column prop="fundAccount" header-align="center" align="center" label="理财账户开户申请笔数"></el-table-column>
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
        currentPage: 1,
        pageSize: 10
      },
      isDisabled: true,
      totalRows: 0,
      dataList: [],
      dataListOut: [],
      dataListLoading: false,
      registerCodeList: []
    }
  },
  created () {
    this.getDataList()
    this.initAgencyAndProcode()
  },
  methods: {
    getDataList () {
      if (this.dataForm.currentPage > 1 && this.dataForm.registerCode !== '') {
        this.dataForm.currentPage = 1
      }
      this.getDataListPage()
    },
    getDataListPage () {
      this.dataListLoading = true
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/queryTrans/accountTrans',
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
    },
    handleCurrentChange (current) {
      this.dataForm.currentPage = current
      this.getDataListPage()
    },
    // 重置
    resetForm (formName) {
      this.$refs[formName].resetFields()
      this.dataList = []
      this.dataListOut = []
      this.dataForm.currentPage = 1
      this.dataForm.pageSize = 10
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
          '账户类交易' + time.toLocaleDateString() + '.xlsx'
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
      this.registerCodeList = (await this.$req.queryAllAgencyAndProcode()).data
    },
    amtFormat (row, column, cellValue) {
      if (cellValue !== '' && cellValue !== null) {
        cellValue += ''
        if (!cellValue.includes('.')) cellValue += '.'
        return cellValue.replace(/(\d)(?=(\d{3})+\.)/g, function ($0, $1) {
          return $1 + ','
        }).replace(/\.$/, '')
      }
    }
  }
}
</script>

<style scoped>

</style>
