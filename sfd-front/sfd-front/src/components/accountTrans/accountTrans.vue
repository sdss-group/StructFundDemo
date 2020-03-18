<template>
  <div class="mod-config">
    <!--表格菜单-->
    <el-form :inline="true" :rules="dataRule" :model="dataForm" ref="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item label="登记机构" prop="registerCode">
        <el-select v-model="dataForm.registerCode" filterable clearable>
          <el-option v-for="item in registerCodeList" :key="item.registerCode" :label="item.registerCode"
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
    <!--表格-->
    <el-table
      :data="dataList"
      v-loading="dataListLoading"
      style="width: 100%;"
      id="out-table">
      <el-table-column
        prop="registerCode"
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
        prop=""
        header-align="center"
        align="center"
        label="交易帐户开户申请笔数">
      </el-table-column>
      <el-table-column
        prop=""
        header-align="center"
        align="center"
        label="理财账户开户申请笔数">
      </el-table-column>
      <el-table-column
        prop=""
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
      dataListLoading: false,
      registerCodeList: [],
      dataRule: {
        registerCode: [
          { required: true, message: '请选择登记机构', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    this.initFundCusttype()
  },
  methods: {
    getDataList () {
      this.$refs['dataForm'].validate((valid) => {
        this.dataListLoading = true
        this.$ajax({
          method: 'post',
          url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/queryTrans/accountTrans',
          data: this.$qs.stringify(this.dataForm)
        }).then((result) => {
          this.dataList = result.data.dataList
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
    },
    // 导出
    exportExcel () {
      var wb = XLSX.utils.table_to_book(document.querySelector('#out-table'))
      var wbout = XLSX.write(wb, {
        bookType: 'xlsx',
        bookSST: true,
        type: 'array'
      })
      try {
        FileSaver.saveAs(
          new Blob([wbout], {type: 'application/octet-stream'}),
          '账户交易.xlsx'
        )
      } catch (e) {
        if (typeof console !== 'undefined') {
          console.log(e, wbout)
        }
      }
      return wbout
    },
    // 登记机构
    async initFundCusttype () {
      this.registerCodeList = []
      this.registerCodeList = (await this.$req.listFundCusttype()).data
    }
  }
}
</script>

<style scoped>

</style>
