<template>
  <div class="mod-config">
    <!--表格菜单-->
    <el-form :inline="true">
      <el-form-item label="登记机构" prop="registerCode">
        <el-select v-model="registerCode" filterable clearable>
          <el-option
            v-for="item in registerCodeList"
            :key="item.registerCode"
            :label="item.registerName"
            :value="item.registerCode"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="产品代码">
        <el-select filterable clearable v-model="fundCode">
          <el-option
            v-for="(item,index) in fundCodeList"
            :key="index"
            :label="item.fundCode"
            :value="item.fundCode"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()" icon="el-icon-search" circle>查询</el-button>
        <el-button type="primary" @click="resetForm()">重置</el-button>
        <el-button type="success" @click="addAndDelete()">提交</el-button>
      </el-form-item>
    </el-form>
    <div class="checkBoxbody">
      <el-checkbox-group v-model="checkList">
        <el-checkbox
          class="checkBoxOuter"
          v-for="(id) in allTypeid"
          :key="id"
          :label="id"
        >{{allType[id]}}</el-checkbox>
      </el-checkbox-group>
    </div>
  </div>
</template>

<script>
import { queryRegList, queryfundList } from "../../common/req";

export default {
  data() {
    return {
      registerCode: "",
      fundCode: "",

      fundCodeList: [],
      registerCodeList: [],
      allType: this.$param.custType,
      allTypeid: Object.keys(this.$param.custType),
      checkList: [],
      
    };
  },
  created() {
    this.initFundCusttype();
  },
  //监听
  watch: {
    //监听registerCode
    registerCode: async function(newValue, oldValue) {
      if (newValue == "") {
        this.fundCode = "";
        this.fundCodeList = [];
        return;
      }
      const response = (await queryfundList(newValue)).data;
      this.fundCodeList = response;
      if (!response == []) {
        this.fundCode = response[0].fundCode;
      }
    }
  },
  methods: {
    //点击查询按钮,查询客户群
    getDataList() {
      //如果registerCode或fundCode为空
      if (this.registerCode == "" || this.fundCode == "") {
        this.$alert("请选择登记机构和产品代码", "提示", {
          confirmButtonText: "确定"
        });
        return;
      }
      //根据registerCode字段和fundCode字段发送查询请求,会得到一个数组,数组元素为数字?
      this.$ajax({
        method: "post",
        url:
          "http://" +
          this.$Config.ip +
          ":" +
          this.$Config.port +
          "/fundCustType/getCustTypeList",
        data: this.$qs.stringify({
          registerCode: this.registerCode,
          fundCode: this.fundCode
        })
      })
        .then(response => {
          this.checkList = response.data;
          
        })
        .catch(error => {
          console.log(error);
        });
    },

    // 新增 / 删除 / 修改
    addAndDelete() {
      //如果registerCode或fundCode为空
      if (this.registerCode == "" || this.fundCode == "") {
        this.$alert("请选择登记机构和产品代码", "提示", {
          confirmButtonText: "确定"
        });
        return;
      }

      //封装提交参数
     
      const param = {
        registerCode: this.registerCode,
        fundCode: this.fundCode,
        checked:this.checkList
      };

      //发送提交请求
      //是否要提交？
      this.$confirm("此操作将提交修改, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          //发送请求
          this.$ajax({
            method: "post",
            url:
              "http://" +
              this.$Config.ip +
              ":" +
              this.$Config.port +
              "/fundCustType/updateByRegAndFund",
            data: this.$qs.stringify(param, { indices: false })
          })
            .then(result => {
              this.getDataList();
              this.$message({
                type: "success",
                message: "提交成功!"
              });

            })
            .catch(error => {
              this.$message({
                type: "error",
                message: "提交失败!"
              });
              console.log(error);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消"
          });
        });

     

      
    },
    // 重置
    resetForm() {
      this.registerCode = "";
      this.fundCode = "";
      this.checkList = [];
      
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
.checkBoxbody {
  margin-left: 25%;
  margin-right: 20%;
}
.checkBoxOuter {
  text-align: left;
  width: 30%;
}
</style>
