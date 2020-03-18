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
        <el-button type="danger" @click="ifDelete(0)" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
        prop="delayDateAllot"
        header-align="center"
        align="center"
        label="认购到账天数">
      </el-table-column>
      <el-table-column
        prop=""
        header-align="center"
        align="center"
        label="申购确认天数">
      </el-table-column>
      <el-table-column
        prop="delayDateRedeem"
        header-align="center"
        align="center"
        label="赎回到账天数">
      </el-table-column>
      <el-table-column
        prop="confRedeemDays"
        header-align="center"
        align="center"
        label="赎回确认天数">
      </el-table-column>
      <el-table-column
        prop="delayDateAdvEnd"
        header-align="center"
        align="center"
        label="提前终止到账天数">
      </el-table-column>
      <el-table-column
        prop="delayDateEnd"
        header-align="center"
        align="center"
        label="正常终止到账天数">
      </el-table-column>
      <el-table-column
        prop=""
        header-align="center"
        align="center"
        label="到期确认天数">
      </el-table-column>
      <!-- moneyTypeAllot[scope.row.moneyTypeAllot] -->
      
        <el-table-column
          prop="moneyTypeAllot"
          header-align="center"
          align="center"
          label="认购到款方式">
          <template slot-scope="scope">
            <!-- <el-tag  size="medium"> -->
            {{moneyTypeAllot[scope.row.moneyTypeAllot]}}
          <!-- </el-tag> -->
          </template>
        </el-table-column>
      
      

      <el-table-column
        prop="delayDateBonus"
        header-align="center"
        align="center"
        label="分红到账天数">
      </el-table-column>
      <el-table-column
        prop=""
        header-align="center"
        align="center"
        label="操作人">
      </el-table-column>
      <el-table-column
        prop="timestampU"
        header-align="center"
        align="center"
        label="更新时间">
      </el-table-column>




      <!-- <el-table-column
        prop="
        "
        header-align="center"
        align="center"
        label="批次状态">
        <template slot-scope="scope">
          <el-tag v-if="lotStatus[scope.row.lotStatus]" size="medium"
                  :type='lotStatus[scope.row.lotStatus].type'>
            {{lotStatus[scope.row.lotStatus].label}}
          </el-tag>
        </template>
      </el-table-column> -->
      
      
      
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="medium" @click="addOrUpdateHandle(scope.row)"><i class="el-icon-edit"></i></el-button>
          <el-button type="text" size="medium" @click="ifDelete(scope.row)"><i class="el-icon-delete"></i></el-button>
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
 import AddOrUpdate from './saleBillDateAddOrUpdate'

export default {
  data () {
    return {
      dataForm: {
        registerCode: '',
        fundCode: ''
      },
      
      moneyTypeAllot: this.$param.moneyTypeAllot,
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
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/saleBillDate/listSaleBillDate',
        data: this.$qs.stringify(this.dataForm)
      }).then((response) => {
        this.dataList = response.data;
        
        // this.totalPage = 10
        //  var j = JSON.stringify(response)
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
    addOrUpdateHandle (item) {
      // 判断什么情况下不能修改
      //todo
      console.log(item);


      this.addOrUpdateVisible = true

      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(item)
      })
    },
    // 重置
    resetForm (formName) {
      this.$refs[formName].resetFields()
      this.getDataList()
    },
    // 判断状态如果非初始化，则不能删除
    ifDelete (item) {
      console.log(item);
     
      console.log(this.dataListSelections);

      this.deleteHandle(item);


      
    },
    // 删除
    deleteHandle (item) {
      let items = item ? [item] : this.dataListSelections;
     
      this.$confirm(`确定对[ 注册机构 = ${items.join('，')} ]进行[${items.length === 1 ? ' 删除 ' : ' 批量删除 '}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax({
          method: 'post',
          
          headers:{'Content-Type':'application/json;charset=UTF-8'},
          url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/saleBillDate/delete',
          //data: this.$qs.stringify({'items': items}, {arrayFormat: 'repeat'}),
          data:JSON.stringify(items),
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
    async initFundCusttype () {
      this.registerCodeList = []
      this.registerCodeList = (await this.$req.listFundCusttype()).data
    }
  }
}
</script>

<style scoped>

</style>
