<template>
  <div class="mod-config">
    <!--表格菜单-->
    <el-form :inline="true"  :model="dataForm" ref="dataForm" >
      <el-form-item label="登记机构" prop="registerCode">
        <el-select v-model="dataForm.registerCode" filterable clearable>
          <el-option
            v-for="item in registerCodeList"
            :key="item.registerCode"
            :label="item.registerName"
            :value="item.registerCode"
          > </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="产品代码" prop="fundCode">
        <el-input v-model="dataForm.fundCode" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList(1)">查询</el-button>
        <el-button type="primary" @click="resetForm('dataForm')">重置</el-button>
        <el-button type="primary" @click="addOrUpdateHandle(1,null)">新增</el-button>

        <el-button
          type="danger"
          @click="ifDelete(0)"
          :disabled="dataListSelections.length <= 0"
        >批量删除</el-button>
      </el-form-item>
    </el-form>
    <!--表格-->
    <el-table
      :data="dataList"
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;"
    >
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="registerCode" header-align="center" align="center" label="登记机构"></el-table-column>
      <el-table-column prop="fundCode" header-align="center" align="center" label="产品代码"></el-table-column>
      <el-table-column prop="delayDateAllot" header-align="center" align="center" label="认购到账天数"></el-table-column>
      <el-table-column prop="delayDatePurse" header-align="center" align="center" label="申购到账天数"></el-table-column>
      <el-table-column prop="delayDateRedeem" header-align="center" align="center" label="赎回到账天数"></el-table-column>
      <el-table-column prop="confRedeemDays" header-align="center" align="center" label="赎回确认天数"></el-table-column>
      <el-table-column prop="delayDateAdvEnd" header-align="center" align="center" label="提前终止到账天数"></el-table-column>
      <el-table-column prop="delayDateEnd" header-align="center" align="center" label="正常终止到账天数"></el-table-column>
      <el-table-column prop="delayDateChange" header-align="center" align="center" label="转换到期天数"></el-table-column>
      <!-- moneyTypeAllot[scope.row.moneyTypeAllot] -->

      <el-table-column prop="moneyTypeAllot" header-align="center" align="center" label="认购到款方式">
        <template slot-scope="scope">
          <!-- <el-tag  size="medium"> -->
          {{moneyTypeAllot[scope.row.moneyTypeAllot]}}
          <!-- </el-tag> -->
        </template>
      </el-table-column>

      <el-table-column prop="delayDateBonus" header-align="center" align="center" label="分红到账天数"></el-table-column>
      <el-table-column prop header-align="center" align="center" label="操作人"></el-table-column>
      <el-table-column prop="timestampU" header-align="center" align="center" label="更新时间"></el-table-column>



      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="medium" @click="addOrUpdateHandle(0,scope.row)">
            <i class="el-icon-edit"></i>
          </el-button>
          <el-button type="text" size="medium" @click="ifDelete(scope.row)">
            <i class="el-icon-delete"></i>
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页-->

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="dataForm.currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="dataForm.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalPieces"
    ></el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from "./saleBillDateAddOrUpdate";
import {queryRegList, queryfundList} from "../../common/req"

export default {
  data() {
    return {

      totalPieces:0,

      dataForm: {
        registerCode: "",
        fundCode: "",
        currentPage: 1,
        pageSize:10
      },

      moneyTypeAllot: this.$param.moneyTypeAllot,
      dataList: [],

      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      registerCodeList: []
    };
  },
  components: {
    AddOrUpdate
  },
  created() {
    this.getDataList();
    this.initFundCusttype();
  },
  methods: {
    getDataList(uniqueQuery) {

      //如果是点击查询，不是点击分页触发的查询
      if(uniqueQuery){
        this.dataForm.currentPage=1
      }
      //this.dataListLoading = true;
      this.$ajax({
        method: "post",
        url:
          "http://" +
          this.$Config.ip +
          ":" +
          this.$Config.port +
          "/saleBillDate/listSaleBillDate",
        data: this.$qs.stringify(this.dataForm)
      })
        .then(response => {
          this.dataList = response.data.content;
          this.totalPieces=response.data.totalPieces;

        })
        .catch(error => {
          console.log(error);
        });
     // this.dataListLoading = false;
    },
    // 分页相关

    handleSizeChange(val) {
     // console.log(`每页 ${val} 条`);
      this.dataForm.pageSize=val;
      this.getDataList();
    },
    handleCurrentChange(val) {
      //console.log(`当前页: ${val}`);
      this.dataForm.currentPage=val;
      this.getDataList();
    },

    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val;
    },
    // 新增 / 修改
    addOrUpdateHandle(isadd, item) {
      // 判断什么情况下不能修改
      //todo
      //console.log(item);

      this.addOrUpdateVisible = true;

      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(isadd, item);
      });
    },
    // 重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.getDataList();
    },
    // 判断状态如果非初始化，则不能删除
    ifDelete(item) {
      //console.log(item);

      // console.log(this.dataListSelections);

      this.deleteHandle(item);
    },
    // 删除
    deleteHandle(item) {
      let items = item ? [item] : this.dataListSelections;
      //提示信息
      let tip="确认进行批量删除？"
      if(items.length==1){
        tip="确认对登记机构为"+items[0].registerCode+",产品代码为"+items[0].fundCode+"进行删除？"
      }
      this.$confirm(
        tip,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      ).then(() => {
        this.$ajax({
          method: "post",

          headers: { "Content-Type": "application/json;charset=UTF-8" },
          url:
            "http://" +
            this.$Config.ip +
            ":" +
            this.$Config.port +
            "/saleBillDate/delete",
          //data: this.$qs.stringify({'items': items}, {arrayFormat: 'repeat'}),
          data: JSON.stringify(items),
          traditional: true
        })
          .then(response => {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.getDataList();
              }
            });
          })
          .catch(error => {
            console.log(error);
          });
      });
    },
    // 登记机构
    async initFundCusttype() {
      this.registerCodeList = [];
      this.registerCodeList = (await queryRegList()).data;
    }
  }
};
</script>

<style scoped>
</style>
