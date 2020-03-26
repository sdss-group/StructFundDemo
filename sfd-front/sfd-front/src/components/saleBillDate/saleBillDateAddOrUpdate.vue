<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
      <el-form-item label="登记机构" prop="registerCode">
        <el-select v-model="dataForm.registerCode" :disabled="isDisabled"  filterable placeholder="请选择">
          <el-option
            v-for="item in registerCodeList"
            :key="item.registerCode"
            :label="item.registerCode"
            :value="item.registerCode">
            <span style="float: left">{{ item.registerCode }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="产品代码" prop="fundCode">
        <el-select v-model="dataForm.fundCode" :disabled="isDisabled" filterable placeholder="请选择" >
          <el-option
            v-for="item in fundCodeList"
            :key="item.fundCode"
            :label="item.fundCode"
            :value="item.fundCode">
            <span style="float: left">{{ item.fundCode }}</span>
          </el-option>
        </el-select>
      </el-form-item>


      <el-form-item label="认购到账天数" prop="delayDateAllot">
        <el-input v-model="dataForm.delayDateAllot" placeholder="认购到账天数" maxlength="2"></el-input>
      </el-form-item>

      <el-form-item label="申购到账天数" prop="delayDatePurse">
        <el-input v-model="dataForm.delayDatePurse" placeholder="申购确认天数" maxlength="2"></el-input>
      </el-form-item>


      <el-form-item label="赎回到账天数" prop="delayDateRedeem">
        <el-input v-model="dataForm.delayDateRedeem" placeholder="赎回到账天数" maxlength="2"></el-input>
      </el-form-item>

      <el-form-item label="赎回确认天数" prop="confRedeemDays">
        <el-input v-model="dataForm.confRedeemDays" placeholder="赎回确认天数" maxlength="2"></el-input>
      </el-form-item>

      <el-form-item label="提前终止到账天数" prop="delayDateAdvEnd">
        <el-input v-model="dataForm.delayDateAdvEnd" placeholder="提前终止到账天数" maxlength="2"></el-input>
      </el-form-item>
      <el-form-item label="正常终止到账天数" prop="delayDateEnd">
        <el-input v-model="dataForm.delayDateEnd" placeholder="正常终止到账天数" maxlength="2"></el-input>
      </el-form-item>

      <el-form-item label="转换到期天数" prop="delayDateChange">
        <el-input v-model="dataForm.delayDateChange"  placeholder="到期确认天数" maxlength="2"></el-input>
      </el-form-item>

      <!-- <el-form-item label="认购到款方式" prop="moneyTypeAllot">
        <el-input v-model="dataForm.moneyTypeAllot" placeholder="认购到款方式1或2" maxlength="2"></el-input>
      </el-form-item> -->

      <el-form-item label="认购到款方式" prop="moneyTypeAllot">
        <el-select v-model="dataForm.moneyTypeAllot" >
          <el-option :label=" moneyTypeAllot['1']" value="1">
            <span style="float: left">每天T+N到</span>
          </el-option>
          <el-option :label=" moneyTypeAllot['2']" value="2">
            <span style="float: left">募集结束日+N到账</span>
          </el-option>
       </el-select>
      </el-form-item>

      


      <el-form-item label="分红到账天数" prop="delayDateBonus">
        <el-input v-model="dataForm.delayDateBonus" placeholder="分红到账天数" maxlength="2"></el-input>
      </el-form-item>

    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data () {
    return {
      visible: false,
      //是否是添加页面的标志
      isadd:1,
      moneyTypeAllot: this.$param.moneyTypeAllot,
      dataForm: {
        
        registerCode: '',
        fundCode: '',
        distributorCode:'',
        delayDateAllot:'',
        delayDatePurse:'',
        delayDateRedeem:'',
        confRedeemDays:'',
        delayDateAdvEnd:'',
        delayDateEnd:'',
        delayDateChange:'',
        moneyTypeAllot:'',
        delayDateBonus:'',
        
      },
      isDisabled: false,
      registerCodeList: [],
      fundCodeList: [],
      dataRule: {
        registerCode: [
          { required: true, message: '请选择登记机构', trigger: 'blur' }
        ],
        fundCode: [
          { required: true, message: '请选择产品代码', trigger: 'blur' }
        ],
        delayDateAllot: [
          { required: true, message: '请填写认购到账天数', trigger: 'blur' }
        ],
        delayDatePurse: [
          { required: true, message: '请填写转换到期天数', trigger: 'blur' }
        ],
        delayDateRedeem: [
          { required: true, message: '请填写赎回到账天数', trigger: 'blur' }
        ],
        confRedeemDays: [
          { required: true, message: '请填写赎回确认天数', trigger: 'blur' }
        ],
        delayDateAdvEnd: [
          { required: true, message: '请填写提前终止到账天数', trigger: 'blur' }
        ],
        delayDateChange: [
          { required: true, message: '', trigger: 'blur' }
        ],
        delayDateEnd: [
          { required: true, message: '请填写正常终止到账天数', trigger: 'blur' }
        ],
        moneyTypeAllot: [
          { required: true, message: '请填写认购到款方式', trigger: 'blur' }
        ],
        delayDateBonus: [
          { required: true, message: '请填写分红到账天数', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    init (item) {
      
    
      this.initFundCusttype()
      
      this.isDisabled = false
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (item) {
          this.$ajax({
            method: 'post',
            headers:{'Content-Type':'application/json;charset=UTF-8'},
            url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/saleBillDate/getOne',
            data:JSON.stringify(item)
          }).then((response) => {
            this.dataForm.registerCode = response.data.registerCode
            this.dataForm.fundCode = response.data.fundCode
            this.dataForm.delayDateAllot = response.data.delayDateAllot
            this.dataForm.delayDatePurse = response.data.delayDatePurse
            this.dataForm.delayDateRedeem = response.data.delayDateRedeem
            this.dataForm.confRedeemDays = response.data.confRedeemDays
            this.dataForm.delayDateAdvEnd = response.data.delayDateAdvEnd
            this.dataForm.delayDateEnd = response.data.delayDateEnd
            this.dataForm.delayDateChange = response.data.delayDateChange
            this.dataForm.moneyTypeAllot = response.data.moneyTypeAllot
            this.dataForm.delayDateBonus = response.data.delayDateBonus
            this.dataForm.distributorCode=response.data.distributorCode
            // 登记机构及产品代码不可更改
            this.isDisabled = true
            this.isadd=0
          }).catch((error) => {
            console.log(error)
          })
        }
      })
    },
    // 初始化注册机构代码和产品代码
    async initFundCusttype () {
      this.registerCodeList = []
      this.fundCodeList = []
      let res = await this.$req.listFundCusttype()
      this.registerCodeList = res.data
      this.fundCodeList = res.data
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$ajax({
            method: 'post',
            headers:{'Content-Type':'application/json;charset=UTF-8'},
            url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/saleBillDate/' + (this.isadd ? 'insert' : 'update'),
            data: JSON.stringify(this.dataForm)
          }).then((response) => {
            if (response.data === 1) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 500,
                onClose: () => {
                  this.visible = false
                  // 刷新页面
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message({
                message: '操作失败',
                type: 'error',
                duration: 1500
              })
            }
          })
        }
      })
    }
  }
}
</script>
