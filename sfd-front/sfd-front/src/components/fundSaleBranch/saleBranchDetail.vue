<template>
<el-dialog v-if='dialogFormVisible' v-bind:title="title" :visible.sync="dialogFormVisible" width="60%">
  <el-form ref='dataForm' :model="data" :inline="true" v-bind:disabled="isDisabled" label-width="180px">
    <el-row style="margin-top: 15px">
      <el-col :span="8" >
        <el-form-item label="登记机构代码" prop="registerCode"
        :rules="{ required: true, message: '登记机构不能为空', trigger: 'blur'}">
            <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="理财产品代码" prop="fundCode"
      :rules="{ required: true, message: '产品代码不能为空', trigger: 'blur'}">
          <el-input  v-model="data.fundCode" v-bind:disabled="!(type=='add')" />
      </el-form-item>
      </el-col>
      <el-col :span="8">
       <el-form-item label="理财产品名称" prop="fundName"
      :rules="{ required: true, message: '产品名称不能为空', trigger: 'blur'}">
          <el-input  v-model="data.fundName" v-bind:disabled="!(type=='add')" />
      </el-form-item>
      </el-col>
      <el-col :span="30">
      <el-form-item label="允许销售" prop="transferType"
      :rules="{ message: '', trigger: 'blur'}"> 
  <el-checkbox-group v-model="data.orgCodes" @change="handleCheckedCitiesChange">
    <el-checkbox v-for="(city,index) in branchList" :label="city.orgCode" :key="index">
     {{index+1}}   {{city.orgCode}}  {{city.orgName}}
     </el-checkbox>
  </el-checkbox-group>
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
  data () {
    return {
      dialogFormVisible: false,
      title: '',
      data: [],
      formLabelWidth: '120px',
      isDisabled: true,
      type: '',
      checkAll: false,
      branchList: [],
      checkedCities: [],
      isIndeterminate: true,
      orgCode:'',
      orgName:'',
      registerCode:'',
      fundCode:'',
      fundName:'',
      status:'',
      branceName:'',
      perSale:'',
    }
  },
  methods: {
    show (data, type) {
      this.checkedCities=[]
      this.dialogFormVisible = true
      this.data = data
      this.findAllBranch()
       for (var i=0;i<this.data.orgAmts.length;i++)
        { 
       this.checkedCities.push(this.data.orgAmts[i].orgCode);
        }

         for (var i=0;i<this.checkedCities.length;i++)
        { 
         if(this.checkedCities[i]=='0111111'){
           for (var i=0;i<this.branchList.length;i++)
           { 
          this.checkedCities.push(this.branchList[i].orgCode);
            }
        }
        }
        this.data.orgCodes=this.checkedCities
      this.type = type
      if (type == 'detail') {
        this.title = '产品销售分行信息查看'
        this.isDisabled = true
      } else if (type == 'add') {
        this.title = '产品销售分行信息新增'
        this.isDisabled = false
      } else if (type == 'modify') {
        this.title = '产品销售分行信息修改'
        this.isDisabled = false
      }
    },
    close () {
      this.dialogFormVisible = false
    },
    submit (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.type == 'add') {
            this.addFundSale()
          } else if (this.type == 'modify') {
            this.modifyFundSale()
          }
          this.dialogFormVisible = false
        }
      })
    },
    addFundSale () {
      alert(this.data.subsStart)
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundSale/addFundSaleInfo',
        data: this.$qs.stringify(this.data)
      }).then((response) => {
        this.$message.success('cg')
      }).catch((error) => {
        console.log(error) // 请求失败返回的数据
        this.$message.error(error)
      })
    },
     modifyFundSale () {
      this.$ajax({
        method: 'post',
        headers: {'Content-Type': 'application/json;charset=UTF-8'},
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/saleBranchDate/update',
        data: JSON.stringify(this.data),
        traditional: true
      }).then((response) => {
        this.$message.success('修改成功')
      }).catch((error) => {
        console.log(error) // 请求失败返回的数据
        this.$message.error(error)
      })
    },


     findAllBranch () {
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/saleBranchDate/findAllBranch',
      }).then((response) => {
        this.branchList = response.data.dataList
      }).catch((error) => {
        console.log(error) // 请求失败返回的数据
        this.$message.error(error)
      })
    },
    // modifyFundSale () {
    //   alert('modifyFundSale')
    // },
    checkInvestTerm () {
      if (isNaN(this.data.investTerm)) {
        this.data.investTerm = ''
      } else if (this.data.investTerm >= 3000) {
        this.data.investTerm = ''
      }
    },
    handleCheckAllChange(val) {
        this.checkedCities = val ? cityOptions : [];
        this.isIndeterminate = false;
      },
      handleCheckedCitiesChange(value) {
        let checkedCount = value.length;
        this.checkAll = checkedCount === this.branchList.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.branchList.length;
      }
  }
}
</script>
