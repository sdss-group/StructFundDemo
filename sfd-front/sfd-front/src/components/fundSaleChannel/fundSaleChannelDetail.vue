<template>
  <el-dialog
    v-if="dialogFormVisible"
    v-bind:title="title"
    :visible.sync="dialogFormVisible"
    width="60%"
  >
    <el-form
      ref="dataForm"
      :model="data"
      :inline="true"
      v-bind:disabled="isDisabled"
      label-width="180px"
    >
      <el-row style="margin-top: 15px">
        <el-col :span="8">
          <el-form-item
            label="登记机构代码"
            prop="registerCode"
            :rules="{ required: true, message: '登记机构不能为空', trigger: 'blur'}"
          >
            <el-input v-model="data.registerCode"  />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item
            label="理财产品代码"
            prop="fundCode"
            :rules="{ required: true, message: '产品代码不能为空', trigger: 'blur'}"
          >
            <el-input v-model="data.fundCode" v-bind:disabled="!(type=='add')" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item
            label="理财产品名称"
            prop="fundName"
            :rules="{ required: true, message: '产品名称不能为空', trigger: 'blur'}"
          >
            <el-input v-model="data.fundName"  />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row style="margin-top: 15px">
        <el-col :span="8">
          <el-form-item
            label="个人网银"
            prop="personalBank"
            :rules="{ required: true, message: '必须勾选', trigger: 'blur'}"
          >
            <el-radio v-model="data.personalBank" label="1"  border>是</el-radio>
            <el-radio v-model="data.personalBank" label="2"  border>否</el-radio>
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item
            label="电话银行"
            prop="telephoneBank"
            :rules="{ required: true, message: '必须勾选', trigger: 'blur'}"
          >
            <el-radio v-model="data.telephoneBank" label="1"  border>是</el-radio>
            <el-radio v-model="data.telephoneBank" label="2"  border>否</el-radio>
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item
            label="手机银行"
            prop="mobilephoneBank"
            :rules="{ required: true, message: '必须勾选', trigger: 'blur'}"
          >
           <el-radio v-model="data.mobilephoneBank" label="1"  border>是</el-radio>
            <el-radio v-model="data.mobilephoneBank" label="2"  border>否</el-radio>
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
          </el-form-item>
        </el-col>
      </el-row>

      <el-row style="margin-top: 15px">
        <el-col :span="8">
          <el-form-item
            label="门户网站"
            prop="webPortal"
            :rules="{ required: true, message: '必须勾选', trigger: 'blur'}"
          >
            <el-radio v-model="data.webPortal" label="1"  border>是</el-radio>
            <el-radio v-model="data.webPortal" label="2"  border>否</el-radio>
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item
            label="多媒体"
            prop="multiMedia"
            :rules="{ required: true, message: '必须勾选', trigger: 'blur'}"
          >
            <el-radio v-model="data.multiMedia" label="1"  border>是</el-radio>
            <el-radio v-model="data.multiMedia" label="2"  border>否</el-radio>
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item
            label="ITM"
            prop="itm"
            :rules="{ required: true, message: '必须勾选', trigger: 'blur'}"
          >
           <el-radio v-model="data.itm" label="1"  border>是</el-radio>
            <el-radio v-model="data.itm" label="2"  border>否</el-radio>
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
          </el-form-item>
        </el-col>
      </el-row>

      <el-row style="margin-top: 15px">
        <el-col :span="8">
          <el-form-item
            label="家易通"
            prop="jiayitong"
            :rules="{ required: true, message: '必须勾选', trigger: 'blur'}"
          >
           <el-radio v-model="data.jiayitong" label="1"  border>是</el-radio>
            <el-radio v-model="data.jiayitong" label="2"  border>否</el-radio>
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item
            label="柜面"
            prop="counter"
            :rules="{ required: true, message: '必须勾选', trigger: 'blur'}"
          >
            <el-radio v-model="data.counter" label="1"  border>是</el-radio>
            <el-radio v-model="data.counter" label="2"  border>否</el-radio>
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item
            label="低柜"
            prop="lowCabinet"
            :rules="{ required: true, message: '必须勾选', trigger: 'blur'}"
          >
            <el-radio v-model="data.lowCabinet" label="1"  border>是</el-radio>
            <el-radio v-model="data.lowCabinet" label="2"  border>否</el-radio>
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
          </el-form-item>
        </el-col>
      </el-row>

      <el-row style="margin-top: 15px">
        <el-col :span="8">
          <el-form-item
            label="自助通"
            prop="zizhutong"
            :rules="{ required: true, message: '必须勾选', trigger: 'blur'}"
          >
            <el-radio v-model="data.zizhutong" label="1"  border>是</el-radio>
            <el-radio v-model="data.zizhutong" label="2"  border>否</el-radio>
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item
            label="智能柜"
            prop="intelligenceCabinet"
            :rules="{ required: true, message: '必须勾选', trigger: 'blur'}"
          >
            <el-radio v-model="data.intelligenceCabinet" label="1"  border>是</el-radio>
            <el-radio v-model="data.intelligenceCabinet" label="2"  border>否</el-radio>
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div v-if="!isDisabled" slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="submit('dataForm')">确 定</el-button>
    </div>
  </el-dialog>
</template>
<script>
export default {
  data() {
    return {
      dialogFormVisible: false,
      title: "",
      data: [],
      formLabelWidth: "120px",
      isDisabled: true,
      type: "",
      registerCode:"",
      fundCode:"",
      fundName:"",
      multiMedia:"",
      personalBank:"",
      telephoneBank:"",
      webPortal:"",
      itm:"",
      jiayitong:"",
      counter:"",
      lowCabinet:"",
      zizhutong:"",
      intelligenceCabinet:"",
      mobilephoneBank:""
    };
  },
  methods: {
    show(data, type) {
      this.dialogFormVisible = true;
      this.data = data;
      this.type = type;
      if (type == "detail") {
        this.title = "产品销售渠道查看";
        this.isDisabled = true;
      } else if (type == "add") {
        this.title = "产品销售渠道新增";
        this.isDisabled = false;
      } else if (type == "modify") {
        this.title = "产品销售渠道修改";
        this.isDisabled = false;
      }
    },
    close() {
      this.dialogFormVisible = false;
    },
    submit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.type == "add") {
            this.addFundSaleChannel();
          } else if (this.type == "modify") {
            this.modifyFundSaleChannel();
          }
          this.dialogFormVisible = false;
        }
      });
    },
    addFundSaleChannel() {
      this.$ajax({
        method: "post",
        headers: {'Content-Type': 'application/json;charset=UTF-8'},
        url:
          "http://" +
          this.$Config.ip +
          ":" +
          this.$Config.port +
          "/fundSaleChannel/addFundSaleChannel",
        // data: this.$qs.stringify(this.data)
        data: JSON.stringify(this.data),
      })
        .then(response => {
          if (response) {
            this.$message({
              showClose: true,
              message: "操作成功",
              type: "success"
            });
          } else {
            this.$message({
              showClose: true,
              message: "操作异常",
              type: "error"
            });
          }
        })
        .catch(error => {
          console.log(error); // 请求失败返回的数据
          this.$message({
            showClose: true,
            message: error,
            type: "error"
          });
        });
    },
    modifyFundSaleChannel() {
      console.log(this.$qs.stringify(this.data));
      this.$ajax({
        method: "post",
        headers: {'Content-Type': 'application/json;charset=UTF-8'},
        url:
          "http://" +
          this.$Config.ip +
          ":" +
          this.$Config.port +
          "/fundSaleChannel/editFundSaleChannel",
          data: JSON.stringify(this.data),
      })
        .then(response => {
          if (response) {
            this.$message({
              showClose: true,
              message: "操作成功",
              type: "success"
            });
          } else {
            this.$message({
              showClose: true,
              message: "操作异常",
              type: "error"
            });
          }
        })
        .catch(error => {
          console.log(error); // 请求失败返回的数据
          this.$message({
            showClose: true,
            message: error,
            type: "error"
          });
        });
    },
    checkInvestTerm() {
      if (isNaN(this.data.investTerm)) {
        this.data.investTerm = "";
      } else if (this.data.investTerm >= 3000) {
        this.data.investTerm = "";
      }
    }
  }
};
</script>
