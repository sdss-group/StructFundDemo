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
          <el-tag v-if="scope.row.lotStatus === '0'" size="medium">初始化</el-tag>
          <el-tag v-if="scope.row.lotStatus === '1'" size="medium" type="info">正在执行</el-tag>
          <el-tag v-if="scope.row.lotStatus === '2'" size="medium" type="success">执行成功</el-tag>
          <el-tag v-if="scope.row.lotStatus === '3'" size="medium" type="danger">执行失败</el-tag>
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
        prop="operaTime"
        header-align="center"
        align="center"
        label="操作时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="medium" @click="addOrUpdateHandle(scope.row.registerCode,scope.row.lotStatus)"><i class="el-icon-edit"></i></el-button>
          <el-button type="text" size="medium" @click="ifDelete(scope.row.lotStatus,scope.row.registerCode)"><i class="el-icon-delete"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页-->
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import Config from '@/Config'
  import qs from 'qs'
  import AddOrUpdate from './fundLotAddOrUpdate'

  export default {
    data () {
      return {
        dataForm: {
          registerCode: '',
          fundCode: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
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
          url: 'http://' + Config.ip + ':' + Config.port + '/fundLot/listFundLot',
          data: qs.stringify(this.dataForm)
        }).then((response) => {
          this.dataList = response.data
          // this.totalPage = 10
          var j = JSON.stringify(response)
         // console.log(j.toString())
        }).catch((error) => {
          console.log(error)
        })
        this.dataListLoading = false
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (registerCode, lotStatus) {
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
          this.$refs.addOrUpdate.init(registerCode)
        })
      },
      // 重置
      resetForm (formName) {
        this.$refs[formName].resetFields()
        this.getDataList()
      },
      // 判断状态如果非初始化，则不能删除
      ifDelete (status, id) {
        let statuss = status ? [status] : this.dataListSelections.map(item => {
          return item.lotStatus
        })
        let deleteFlag = 0
        statuss.forEach((item) => {
          if (item === '1' || item === '2' || item === '3') {
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
          this.deleteHandle(id)
        }
      },
      // 删除
      deleteHandle (id) {
        let ids = id ? [id] : this.dataListSelections.map(item => {
          return item.registerCode
        })
        this.$confirm(`确定对[ 注册机构 = ${ids.join('，')} ]进行[${ids.length === 1 ? ' 删除 ' : ' 批量删除 '}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$ajax({
            method: 'post',
            url: 'http://' + Config.ip + ':' + Config.port + '/fundLot/delete',
            data: qs.stringify({'ids': ids}, {arrayFormat: 'repeat'}),
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
          }).catch((error) => {
            console.log(error)
          })
        })
      },
      // 登记机构
      initFundCusttype () {
        this.registerCodeList = []
        this.$ajax({
          method: 'post',
          url: 'http://' + Config.ip + ':' + Config.port + '/fundLot/listFundCusttype '
        }).then((response) => {
          this.registerCodeList = response.data
          var j = JSON.stringify(response)
          console.log('登记机构:' + j.toString())
        }).catch((error) => {
          console.log(error)
        })
      }
    }
  }
</script>

<style scoped>

</style>
