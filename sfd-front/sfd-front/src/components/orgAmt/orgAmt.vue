<template>
  <div class="mod-config">
    <!--表格菜单-->
    <el-form :inline="true" :model="dataForm" ref="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item label="登记机构" prop="registerCode">
        <el-select v-model="dataForm.registerCode" filterable clearable>
          <el-option v-for="item in registerCodeList" :key="item.index" :label="item.registerName"
                     :value="item.registerCode">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="产品代码" prop="fundCode">
        <el-input v-model="dataForm.fundCode" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button type="primary" @click="resetForm('dataForm')">重置</el-button>
      </el-form-item>
    </el-form>
    <!--表格-->
    <el-table
      :data="dataList"
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="fundCode"
        header-align="center"
        align="center"
        label="产品代码">
      </el-table-column>
      <el-table-column
        prop="fundName"
        header-align="center"
        align="center"
        label="产品名称">
      </el-table-column>
      <el-table-column
        prop="registerName"
        header-align="center"
        align="center"
        label="管理人名称">
      </el-table-column>
      <el-table-column
        v-if="false"
        prop="level"
        header-align="center"
        align="center"
        label="级别">
      </el-table-column>
      <el-table-column
        v-if="false"
        prop="orgCode"
        header-align="center"
        align="center"
        label="分行代码">
      </el-table-column>
      <el-table-column
        prop="registerCode"
        header-align="center"
        align="center"
        label="中登登记代码">
      </el-table-column>
      <el-table-column
        prop="totalAmt"
        header-align="center"
        :formatter="amtFormat"
        align="center"
        label="全行额度">
      </el-table-column>
      <el-table-column
        prop="saledAmt"
        header-align="center"
        :formatter="amtFormat"
        align="center"
        label="全行已售额度">
      </el-table-column>
      <el-table-column
        prop="restAmt"
        header-align="center"
        :formatter="amtFormat"
        align="center"
        label="全行未售额度">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="medium" @click="addOrUpdateHandle(scope.row)"><i class="el-icon-edit"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页-->
    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="dataForm.currentPage"
      layout="total, prev, pager, next, jumper"
      :total="totalRows">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from './orgAmtAddOrUpdate'

export default {
  data () {
    return {
      dataForm: {
        registerCode: '',
        fundCode: '',
        currentPage: 1,
        pageSize: 10
      },
      totalRows: 0,
      lotStatus: this.$param.lotStatus,
      dataList: [],
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      registerCodeList: []
    }
  },
  components: {
    AddOrUpdate
  },
  created () {
    this.getDataList()
    this.initAgency()
  },
  methods: {
    getDataList () {
      if (this.dataForm.currentPage > 1 && (this.dataForm.registerCode !== '' || this.dataForm.fundCode !== '')) {
        this.dataForm.currentPage = 1
      }
      this.getDataListPage()
    },
    getDataListPage () {
      this.dataListLoading = true
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/orgAmt/queryOrgAmt',
        data: this.$qs.stringify(this.dataForm)
      }).then((result) => {
        this.dataList = result.data.dataList
        this.totalRows = result.data.totalRows
      })
      this.dataListLoading = false
    },
    handleCurrentChange (current) {
      this.dataForm.currentPage = current
      this.getDataListPage()
    },
    // 多选
    selectionChangeHandle (val) {
      this.dataListSelections = val
    },
    // 修改
    addOrUpdateHandle (row) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(row)
      })
    },
    // 重置
    resetForm (formName) {
      this.$refs[formName].resetFields()
      this.dataForm.currentPage = 1
      this.dataForm.pageSize = 10
      this.getDataListPage()
    },
    // 登记机构
    async initAgency () {
      this.registerCodeList = []
      this.registerCodeList = (await this.$req.queryRegList()).data
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
