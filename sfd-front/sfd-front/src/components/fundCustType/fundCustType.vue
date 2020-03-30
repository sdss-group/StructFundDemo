<template>
  <div class="mod-config">
    <!--表格菜单-->
    <el-form :inline="true" :model="dataForm" ref="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item label="登记机构" prop="registerCode">
        <el-select v-model="dataForm.registerCode" filterable clearable>
          <el-option
            v-for="item in registerCodeList"
            :key="item.registerCode"
            :label="item.registerCode"
            :value="item.registerCode"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="产品代码" prop="fundCode">
        <el-input v-model="dataForm.fundCode" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button type="primary" @click="resetForm()">重置</el-button>
        <el-button type="primary" @click="addAndDelete()">修改</el-button>
      </el-form-item>
    </el-form>

    <el-checkbox-group v-model="checkList">
      <el-checkbox v-for="(id) in allTypeid" :key="id" :label="id">{{allType[id]}}</el-checkbox>
    </el-checkbox-group>
  </div>
</template>

<script>

import {queryRegList} from "../../common/req"

export default {
  data() {
    return {
      dataForm: {
        registerCode: "",
        fundCode: ""
      },
      registerCodeList: [],
      allType: this.$param.custType,
      allTypeid: Object.keys(this.$param.custType),
      checkList: [],
      checkListCopy: []
    };
  },
  created() {
    this.initFundCusttype();
  },
  methods: {
    //点击查询按钮,查询客户群
    getDataList() {
      //根据registerCode字段和fundCode字段发送查询请求,会得到一个数组,数组元素为数字?
      this.$ajax({
        method: "post",
        url:
          "http://" +
          this.$Config.ip +
          ":" +
          this.$Config.port +
          "/fundCustType/getCustTypeList",
        data: this.$qs.stringify(this.dataForm)
      })
        .then(response => {
          this.checkList = response.data;
          this.checkListCopy = JSON.parse(JSON.stringify(this.checkList));
        })
        .catch(error => {
          console.log(error);
        });
    },

    // 新增 / 删除 / 修改
    addAndDelete() {
      const _arr1Set = new Set(this.checkList), //修改后的客户群
        _arr2Set = new Set(this.checkListCopy); //原客户群
      //求交集
      let intersection = this.checkList.filter(item => _arr2Set.has(item));
      const _arr3Set = new Set(intersection);

      console.log("交集", intersection);
      //求新增的客户群=交集在修改后的客户群的差集
      //即交集中没有而修改后的客户群有的
      let toAdd = this.checkList.filter(item => !_arr3Set.has(item));

      //求删除的客户群=交集在修改前的客户群的差集
      //即交集里没有而原客户群有的
      let toDelete = this.checkListCopy.filter(item => !_arr3Set.has(item));

      // console.log("需要添加", toAdd);
      // console.log("需要删除", toDelete);
      //封装添加客户群参数
      const paramAdd = {
        registerCode: this.dataForm.registerCode,
        fundCode: this.dataForm.fundCode,
        toAdd
      };
      //封装删除客户群参数
      const paramDelete = {
        registerCode: this.dataForm.registerCode,
        fundCode: this.dataForm.fundCode,
        toDelete
      };

      //增加
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundCustType/insertCustTypeList',
        data: this.$qs.stringify(paramAdd,{ indices: false })
        
      }).then((result) => {
      }).catch((error) => {
        console.log(error)
      })

      //删除
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundCustType/deleteCustTypeList',
        data: this.$qs.stringify(paramDelete,{ indices: false })
        
      }).then((result) => {
        this.getDataList();
      }).catch((error) => {
        console.log(error)
      })

      

      // this.$ajax({
      //   method: "post",
      //   //headers: { "Content-Type": "application/json;charset=UTF-8" },
      //   url:"http://" +this.$Config.ip + ":" +this.$Config.port +"/fundCustType/insertCustTypeList",
      //   data: JSON.stringify(paramAdd),
      //   traditional: true
      // })
      //   .then(response => {
      //     this.$message({
      //       message: "操作成功",
      //       type: "success",
      //       duration: 1500,
      //       onClose: () => {
      //         this.getDataList();
      //       }
      //     });
      //   })
      //   .catch(error => {
      //     console.log(error);
      //   });


    },
    // 重置
    resetForm() {
      this.dataForm.registerCode='';
      this.dataForm.fundCode='';
      this.checkList=[];
      this.checkListCopy=[]
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
