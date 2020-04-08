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
      <el-form-item label="转托管方式" prop="transferType"
      :rules="{ required: true, message: '请选择转托管方式', trigger: 'blur'}">
        <el-select v-model="data.transferType" style="width:203px">
          <el-option
          v-for="item in this.$param.transferType"
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
        <el-form-item label="是否收费" prop="charge"
        :rules="{ required: true, message: '请选择是否收费', trigger: 'blur'}">
          <el-select v-model="data.charge" style="width:203px">
            <el-option
            v-for="item in this.$param.charge"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="核算方式" prop="checkType"
      :rules="{ required: true, message: '请选择核算方式', trigger: 'blur'}">
        <el-select v-model="data.checkType" style="width:203px">
          <el-option
          v-for="item in this.$param.checkType"
          :key="item.value"
          :label="item.label"
          :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="是否延时入账" prop="delayedEntry"
      :rules="{ required: true, message: '请选择是否延时入账', trigger: 'blur'}">
        <el-select v-model="data.delayedEntry" style="width:203px">
          <el-option
          v-for="item in this.$param.delayedEntry"
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
        <el-form-item label="投资期限" prop="investTerm"
        :rules="{ required: true, message: '例:2284代表284天,1003代表3个月,0002代表2年', trigger: 'blur'}">
          <el-input v-model="data.investTerm" maxlength=4 minlength=4 @change="checkInvestTerm"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="定期定额申购交易标志" prop="periodSubsFlag">
        <el-select v-model="data.periodSubsFlag" style="width:203px">
          <el-option
          v-for="item in this.$param.periodSubsFlag"
          :key="item.value"
          :label="item.label"
          :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="定期定额申购扣款失败次数" prop="failTimes">
        <el-input-number v-model="data.failTimes" :controls="false" :min="0" :max="100" :step=1 style="width:203px" step-strictly></el-input-number>
      </el-form-item>
      </el-col>
    </el-row>

    <el-row style="margin-top: 15px">
      <el-col :span="8" >
        <el-form-item label="认购期扣账方式" prop="subsCapType"
        :rules="{ required: true, message: '请选择认购期扣账方式', trigger: 'blur'}">
          <el-select v-model="data.subsCapType" style="width:203px">
            <el-option
            v-for="item in this.$param.subsCapType"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="申购期扣账方式" prop="purchCapType"
      :rules="{ required: true, message: '请选择申购期扣账方式', trigger: 'blur'}">
        <el-select v-model="data.purchCapType" style="width:203px">
          <el-option
          v-for="item in this.$param.purchCapType"
          :key="item.value"
          :label="item.label"
          :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="产品风险等级" prop="fundLevel"
      :rules="{ required: true, message: '请选择产品风险等级', trigger: 'blur'}">
        <el-select v-model="data.fundLevel" style="width:203px">
          <el-option
          v-for="item in this.$param.fundLevel"
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
        <el-form-item label="最低募集金额" prop="minBala"
        :rules="{ required: true, message: '请输入最低募集金额', trigger: 'blur'}">
          <el-input-number v-model="data.minBala" :controls="false" :min="0" :max="999999999999" :step=1 style="width:203px" step-strictly></el-input-number>
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="最高募集金额" prop="maxBala"
      :rules="{ required: true, message: '请输入最高募集金额', trigger: 'blur'}">
        <el-input-number v-model="data.maxBala" :controls="false" :min="0" :max="999999999999" :step=1 style="width:203px" step-strictly></el-input-number>
      </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="收益类型" prop="incomeType"
      :rules="{ required: true, message: '请选择收益类型', trigger: 'blur'}">
        <el-select v-model="data.incomeType" style="width:203px">
          <el-option
          v-for="item in this.$param.incomeType"
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
        <el-form-item label="销售对象" prop="saleObject"
        :rules="{ required: true, message: '请选择销售对象', trigger: 'blur'}">
          <el-select v-model="data.saleObject" style="width:203px">
            <el-option
            v-for="item in this.$param.saleObject"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="额度控制属性" prop="amtFlag"
      :rules="{ required: true, message: '请选择额度控制属性', trigger: 'blur'}">
        <el-select v-model="data.amtFlag" style="width:203px">
          <el-option
          v-for="item in this.$param.amtFlag"
          :key="item.value"
          :label="item.label"
          :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="非工作日是否允许交易" prop="fundCtrlFlag"
      :rules="{ required: true, message: '请选择非工作日是否允许交易', trigger: 'blur'}">
        <el-select v-model="data.fundCtrlFlag" style="width:203px">
          <el-option
          v-for="item in this.$param.fundCtrlFlag"
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
        <el-form-item label="交易起始时间" prop="tradeStart"
        :rules="{ required: true, message: '请输入交易起始时间', trigger: 'blur'}">
          <el-time-picker v-model="data.tradeStart" style="width:203px" value-format="HHmmss">
          </el-time-picker>
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="交易结束时间" prop="tradeEnd"
      :rules="{ required: true, message: '请输入交易结束时间', trigger: 'blur'}">
        <el-time-picker v-model="data.tradeEnd"
        style="width:203px" value-format="HHmmss">
        </el-time-picker>
      </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="非目标客户最低投资金额" prop="untargetBalance"
      :rules="{ required: true, message: '请输入非目标客户最低投资金额', trigger: 'blur'}">
        <el-input-number v-model="data.untargetBalance" :controls="false" :min="0" :max="99999999" :step=1 style="width:203px" step-strictly></el-input-number>
      </el-form-item>
      </el-col>
    </el-row>

    <el-row style="margin-top: 15px">
      <el-col :span="8" >
        <el-form-item label="认购开始时间" prop="subsStart"
        :rules="{ required: true, message: '请输入认购开始时间', trigger: 'blur'}">
          <el-time-picker v-model="data.subsStart" style="width:203px" value-format="HHmmss">
          </el-time-picker>
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="认购结束时间" prop="subsEnd"
      :rules="{ required: true, message: '请输入认购结束时间', trigger: 'blur'}">
        <el-time-picker v-model="data.subsEnd" value-format="HHmmss"
        style="width:203px">
        </el-time-picker>
      </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="销售手续费率" prop="saleRateFee"
      :rules="{ required: true, message: '请输入销售手续费率', trigger: 'blur'}">
        <el-input-number v-model="data.saleRateFee" :controls="false" :min="0" :max="1" :step=0.001 style="width:203px" step-strictly></el-input-number>
      </el-form-item>
      </el-col>
    </el-row>

    <el-row style="margin-top: 15px">
      <el-col :span="8">
      <el-form-item label="业绩比较基准值" prop="performNum"
      :rules="{ required: true, message: '请输入业绩比较基准值', trigger: 'blur'}">
        <el-input  v-model="data.performNum" />
      </el-form-item>
      </el-col>
      <el-col :span="8" >
        <el-form-item label="预计客户最高年收益率" prop="maxRatio"
        :rules="{ required: true, message: '请输入预计客户最高年收益率', trigger: 'blur'}">
         <el-input-number v-model="data.maxRatio" :controls="false" :min="data.minRatio" :max="1" :step=0.0001 style="width:203px" step-strictly></el-input-number>
        </el-form-item>
      </el-col>
      <el-col :span="8">
      <el-form-item label="预计客户最低年收益率" prop="minRatio"
      :rules="{ required: true, message: '请输入预计客户最低年收益率', trigger: 'blur'}">
        <el-input-number v-model="data.minRatio" :controls="false" :min="0" :max="data.maxRatio" :step=0.0001 style="width:203px" step-strictly></el-input-number>
      </el-form-item>
      </el-col>
    </el-row>

    <el-row style="margin-top: 15px">
      <el-col :span="8">
      <el-form-item label="是否允许实时赎回" prop="fastFlag"
      :rules="{ required: true, message: '请选择是否允许实时赎回', trigger: 'blur'}">
        <el-select v-model="data.fastFlag" style="width:203px">
          <el-option
          v-for="item in this.$param.fastFlag"
          :key="item.value"
          :label="item.label"
          :value="item.value">
          </el-option>
        </el-select>
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
        this.title = '产品销售参数信息查看'
        this.isDisabled = true
      } else if (type == 'add') {
        this.title = '产品销售参数信息新增'
        this.isDisabled = false
      } else if (type == 'modify') {
        this.title = '产品销售参数信息修改'
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
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundSale/addFundSaleInfo',
        data: this.$qs.stringify(this.data)
      }).then((response) => {
        if (response) {
          this.$message({
            showClose: true,
            message: '操作成功',
            type: 'success'
          })
        } else {
          this.$message({
            showClose: true,
            message: '操作异常',
            type: 'error'
          })
        }
      }).catch((error) => {
        console.log(error) // 请求失败返回的数据
        this.$message({
          showClose: true,
          message: error,
          type: 'error'
        })
      })
    },
    modifyFundSale () {
      console.log(this.$qs.stringify(this.data))
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundSale/modifyFundSaleInfo',
        data: this.$qs.stringify(this.data)
      }).then((response) => {
        if (response) {
          this.$message({
            showClose: true,
            message: '操作成功',
            type: 'success'
          })
        } else {
          this.$message({
            showClose: true,
            message: '操作异常',
            type: 'error'
          })
        }
      }).catch((error) => {
        console.log(error) // 请求失败返回的数据
        this.$message({
          showClose: true,
          message: error,
          type: 'error'
        })
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
