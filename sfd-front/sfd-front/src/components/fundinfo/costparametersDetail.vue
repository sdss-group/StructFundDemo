<template>
<el-dialog v-if='dialogFormVisible' v-bind:title="title" :visible.sync="dialogFormVisible" width="60%">
  <el-form ref='dataForm' :model="data" :inline="true" v-bind:disabled="isDisabled" label-width="180px">
    <el-row style="margin-top: 15px">
      <el-col :span="8" >
        <el-form-item label="登记机构" prop="registerCode"
        :rules="{ required: true, message: '登记机构不能为空', trigger: 'blur'}">
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
            <el-select v-model="data.registerCode" style="width:203px" v-bind:disabled="!(type=='add')">
            <el-option
              v-for="item in $param.registerCode"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <!-- <el-form-item label="产品代码" prop="fundCode"
      :rules="{ required: true, message: '产品代码不能为空', trigger: 'blur'}">
          <el-input  v-model="data.fundCode" v-bind:disabled="!(type=='add')" />
      </el-form-item> -->
        <el-form-item label="产品代码" prop="fundCode"
        :rules="{ required: true, message: '产品代码不能为空', trigger: 'blur'}">
            <!-- <el-input  v-model="data.registerCode" v-bind:disabled="!(type=='add')" /> -->
            <el-select v-model="data.fundCode" style="width:203px" v-bind:disabled="!(type=='add')">
            <el-option
              v-for="item in $param.fundCode"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="认购费支付方式" prop="paymentMethod"
      :rules="{ required: true, message: '认购费支付方式不能为空', trigger: 'blur'}">
       <el-input  v-model="data.paymentMethod" v-bind:disabled="!(type=='add')&&!(type=='modify')" />
      </el-form-item>
      </el-col>
    </el-row>

    <el-row style="margin-top: 15px">
      <el-col :span="8" >
        <el-form-item label="认购费率" prop="subscriptionRates"
      :rules="{ required: true, message: '认购费率不能为空', trigger: 'blur'}">
               <el-input-number  v-model="data.subscriptionRates" v-bind:disabled="!(type=='add')&&!(type=='modify')" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="申购费支付方式" prop="subscriptionFee"
      :rules="{ required: true, message: '申购费支付方式不能为空', trigger: 'blur'}">
    <el-input  v-model="data.subscriptionFee" v-bind:disabled="!(type=='add')&&!(type=='modify')" />
      </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="申购费率" prop="subscriptionRate"
    :rules="{ required: true, message: '申购费率不能为空', trigger: 'blur'}">
          <el-input-number  v-model="data.subscriptionRate" v-bind:disabled="!(type=='add')&&!(type=='modify')" />
      </el-form-item>
      </el-col>
    </el-row>

    <el-row style="margin-top: 15px">
      <el-col :span="8" >
        <el-form-item label="赎回费支付方式" prop="redemptionFee"
      :rules="{ required: true, message: '赎回费支付方式不能为空', trigger: 'blur'}">
               <el-input  v-model="data.redemptionFee" v-bind:disabled="!(type=='add')&&!(type=='modify')" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="赎回费率" prop="redemptionRate"
      :rules="{ required: true, message: '赎回费率不能为空', trigger: 'blur'}">
       <el-input-number  v-model="data.redemptionRate" v-bind:disabled="!(type=='add')&&!(type=='modify')" />
      </el-form-item>
      </el-col>
      <el-col :span="8">
       <el-form-item label="销售费支付方式" prop="salesFee"
       :rules="{ required: true, message: '请选择销售费支付方式', trigger: 'blur'}">
              <el-select v-model="data.salesFee" style="width:203px" v-bind:disabled="!(type=='add')&&!(type=='modify')" >
                <el-option
                v-for="item in this.$param.salesFee"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
       
      </el-col> 
    </el-row>

    <el-row style="margin-top: 15px">
      <el-col :span="8" >
        <el-form-item label="销售手续费率" prop="salesProcedureRate"
         :rules="{ required: true, message: '销售手续费率不能为空', trigger: 'blur'}">
                   <el-input-number  v-model="data.salesProcedureRate" v-bind:disabled="!(type=='add')&&!(type=='modify')" />
    </el-form-item>
      </el-col>
      <el-col :span="8">
       <el-form-item label="管理费支付方式" prop="managementFeePayment"
        :rules="{ required: true, message: '请选择管理费支付方式', trigger: 'blur'}">
              <el-select v-model="data.managementFeePayment" style="width:203px" v-bind:disabled="!(type=='add')&&!(type=='modify')">
                <el-option
                v-for="item in this.$param.managementFeePayment"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="管理费率" prop="managementFeeRate"
      :rules="{ required: true, message: '管理费率不能为空', trigger: 'blur'}">
               <el-input-number  v-model="data.managementFeeRate" v-bind:disabled="!(type=='add')&&!(type=='modify')" />

      </el-form-item>
      </el-col>
    </el-row>

    <el-row style="margin-top: 15px">
      <el-col :span="8" >
        <el-form-item label="超额业绩报酬支付方式" prop="excessPerformanceCompenstation"
            :rules="{ required: true, message: '超额业绩报酬支付方式不能为空', trigger: 'blur'}">

                      <el-input  v-model="data.excessPerformanceCompenstation" v-bind:disabled="!(type=='add')&&!(type=='modify')" />

        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="分成比例" prop="proportion"
      :rules="{ required: true, message: '分成比例不能为空', trigger: 'blur'}">
                      <el-input-number  v-model="data.proportion" v-bind:disabled="!(type=='add' )&&!(type=='modify')" />

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
      type: ''
    }
  },
  methods: {
    show (data, type) {
      this.dialogFormVisible = true
      this.data = data
      this.type = type
      if (type == 'detail') {
        this.title = '费用参数信息查看'
        this.isDisabled = true
      } else if (type == 'add') {
        this.title = '费用参数信息新增'
        this.isDisabled = false
      } else if (type == 'modify') {
        this.title = '费用参数信息修改'
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
            this.addCostParam()
          } else if (this.type == 'modify') {
            this.modifyCostParam()
          }
          this.dialogFormVisible = false
        }
      })
    },
    addCostParam () {
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/costParameters/costAdd',
        data: this.$qs.stringify(this.data)
      }).then((response) => {
         this.$message.success(response.data.result)
      }).catch((error) => {
        console.log(error) // 请求失败返回的数据
        this.$message.error(error)
      })
    },
    modifyCostParam () {
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/costParameters/costUpdate',
        data: this.$qs.stringify(this.data)
      }).then((response) => {
    this.$message.success(response.data.result)
      }).catch((error) => {
        console.log(error) // 请求失败返回的数据
        this.$message.error(error)
      })
    },
    checkInvestTerm () {
      if (isNaN(this.data.investTerm)) {
        this.data.investTerm = ''
      } else if (this.data.investTerm >= 3000) {
        this.data.investTerm = ''
      }
    }
  }
}
</script>
