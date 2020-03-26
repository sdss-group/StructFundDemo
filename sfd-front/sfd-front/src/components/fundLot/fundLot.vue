<template>
  <div class="mod-config">
    <!--表格菜单-->
    <el-form :inline="true" :model="dataForm" ref="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item label="登记机构" prop="registerCode">
        <el-select v-model="dataForm.registerCode" filterable clearable>
          <el-option v-for="item in registerCodeList" :key="item.registerCode" :label="item.registerCode"
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
        <el-button type="primary" @click="addOrUpdateHandle()">新增</el-button>
       <!-- <el-button type="primary" @click="addOrUpdateHandle()">修改</el-button>-->
        <el-button type="danger" @click="ifDelete()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
        prop="fundName"
        header-align="center"
        align="center"
        label="产品名称">
      </el-table-column>
      <el-table-column
        prop="lotCode"
        header-align="center"
        align="center"
        label="产品批次">
      </el-table-column>
      <el-table-column
        prop="startTime"
        header-align="center"
        align="center"
        label="批次发起时间">
      </el-table-column>
      <el-table-column
        prop="lotStatus"
        header-align="center"
        align="center"
        label="批次状态">
        <template slot-scope="scope">
          <!--<el-tag v-if="lotStatus.get(scope.row.lotStatus)" size="medium" :type="lotStatus.get(scope.row.lotStatus).type">
            {{ lotStatus.get(scope.row.lotStatus) }}
          </el-tag>-->
          <el-tag v-if="lotStatus[scope.row.lotStatus]" size="medium"
                  :type='lotStatus[scope.row.lotStatus].type'>
            {{lotStatus[scope.row.lotStatus].label}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="operator"
        header-align="center"
        align="center"
        label="操作人">
      </el-table-column>
      <el-table-column
        prop="authorizer"
        header-align="center"
        align="center"
        label="授权人">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="medium" @click="addOrUpdateHandle(scope.row.registerCode,scope.row.fundCode,scope.row.lotCode,scope.row.lotStatus)"><i class="el-icon-edit"></i></el-button>
          <el-button type="text" size="medium" @click="ifDelete(scope.row)"><i class="el-icon-delete"></i></el-button>
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
import AddOrUpdate from './fundLotAddOrUpdate'
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
    this.initFundCusttype()
  },
  methods: {
    getDataList () {
      this.dataListLoading = true
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundLot/queryFundLot',
        data: this.$qs.stringify(this.dataForm)
      }).then((result) => {
        this.dataList = result.data.dataList
        this.totalRows = result.data.totalRows
      })
      this.dataListLoading = false
    },
    handleCurrentChange (current) {
      this.dataForm.currentPage = current
      this.getDataList()
    },
    // 多选
    selectionChangeHandle (val) {
      this.dataListSelections = val
    },
    // 新增 / 修改
    addOrUpdateHandle (registerCode, fundCode, lotCode, lotStatus) {
      // 非初始化状态不能修改
      if (lotStatus === '1' || lotStatus === '2' || lotStatus === '3') {
        this.$message({
          showClose: true,
          message: '非初始化状态不允许修改!',
          type: 'error'
        })
        return 0
      }
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(registerCode, fundCode, lotCode)
      })
    },
    // 重置
    resetForm (formName) {
      this.$refs[formName].resetFields()
      this.getDataList()
    },
    // 判断状态如果非初始化，则不能删除
    ifDelete (row) {
      let rows = row ? [row] : this.dataListSelections.map(item => {
        return item
      })
      let deleteFlag = 0
      rows.forEach((item) => {
        if (item.lotStatus === '1' || item.lotStatus === '2' || item.lotStatus === '3') {
          deleteFlag++
        }
      })
      if (deleteFlag > 0) {
        this.$message({
          showClose: true,
          message: '非初始化状态，不允许删除!',
          type: 'error'
        })
        return 0
      } else {
        this.deleteHandle(rows)
      }
    },
    // 删除
    deleteHandle (rows) {
      this.$confirm(`确定对所选项进行[${rows.length === 1 ? ' 删除 ' : ' 批量删除 '}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax({
          method: 'post',
          headers: {'Content-Type': 'application/json;charset=UTF-8'},
          url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundLot/delete',
          data: JSON.stringify(rows),
          traditional: true
        }).then((response) => {
          this.$message({
            message: '操作成功',
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.getDataList()
            }
          })
        })
      })
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
