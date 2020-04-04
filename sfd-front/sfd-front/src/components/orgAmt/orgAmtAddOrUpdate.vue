<template>
  <el-dialog :title="'总行销售额度维护'" :close-on-click-modal="false"
             :visible.sync="visible" width="700px">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="100px">
      <el-form-item label="登记机构" prop="registerCode">
      <el-select v-model="dataForm.registerCode" :disabled="isDisabled"  filterable placeholder="请选择">
          <el-option
            v-for="item in registerCodeList"
            :key="item.index"
            :label="item.registerName"
            :value="item.registerCode">
            <span style="float: left">{{ item.registerName }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="产品代码" prop="fundCode">
        <el-select v-model="dataForm.fundCode" :disabled="isDisabled" filterable placeholder="请选择">
          <el-option
            v-for="item in fundCodeList"
            :key="item.index"
            :label="item.fundCode"
            :value="item.fundCode">
            <span style="float: left">{{ item.fundCode }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="产品名称" prop="fundName">
        <el-input  v-model="dataForm.fundName" :disabled="isDisabled"/>
      </el-form-item>
      <el-form-item label="级别" prop="level">
        <el-radio-group v-model="dataForm.level" size="mini" :disabled="isDisabled">
          <el-radio :label="0">总行</el-radio>
          <el-radio :label="1">分行</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="销售额度" prop="totalAmt">
        <el-input class="amt" v-model="dataForm.totalAmt" placeholder="请输入额度数值"/>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">关闭</el-button>
      <el-button type="primary" @click="dataFormSubmit()">提交</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data () {
    return {
      visible: false,
      dataForm: {
        id: 0,
        registerCode: '',
        fundCode: '',
        fundName: '',
        level: '0',
        totalAmt: ''
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
        totalAmt: [
          {required: true, message: '请输入销售额度', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              if (/^[1-9]\d{1,13}(\.\d{1,2})?$/.test(value) === false) {
                callback(new Error('请输入正确额度数值'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }
        ]
      }
    }
  },
  methods: {
    init (row) {
      this.initAgencyAndProcode()
      this.dataForm.id = (typeof row !== 'undefined' && row.registerCode) || 0
      this.isDisabled = false
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.dataForm.registerCode = row.registerCode
          this.dataForm.fundCode = row.fundCode
          this.dataForm.fundName = row.fundName
          this.dataForm.orgCode = row.orgCode
          this.dataForm.level = row.level === '0' ? 0 : 1
          this.dataForm.totalAmt = row.totalAmt
          this.isDisabled = true
        }
      })
    },
    // 初始化注册机构代码和产品代码
    async initAgencyAndProcode () {
      this.registerCodeList = []
      this.fundCodeList = []
      let result = await this.$req.queryAllAgencyAndProcode()
      this.registerCodeList = result.data
      this.fundCodeList = result.data
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$ajax({
            method: 'post',
            url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/orgAmt/update',
            data: this.$qs.stringify(this.dataForm)
          }).then((response) => {
            if (response.data === 1) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message({
                message: '维护异常',
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
<style scoped>
  .el-select{
    width: 50%;
    margin-left:-50%
  }
  .el-radio-group{
    width: 50%;
    margin-left:-65%
  }
</style>
