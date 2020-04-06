<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible" width="700px">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="110px">
        <el-form-item label="登记机构" prop="registerCode">
        <el-select v-model="dataForm.registerCode" :disabled="isDisabled"  filterable placeholder="请选择">
          <el-option v-for="item in registerCodeList"
            :key="item.index"
            :label="item.registerName"
            :value="item.registerCode">
            <span style="float: left">{{ item.registerName}}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="产品代码" prop="fundCode">
        <el-select v-model="dataForm.fundCode" :disabled="isDisabled" filterable placeholder="请选择" >
          <el-option v-for="item in fundCodeList"
            :key="item.index"
            :label="item.fundCode"
            :value="item.fundCode">
            <span style="float: left">{{ item.fundCode }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="产品批次" prop="lotCode">
        <el-input v-model="dataForm.lotCode" :disabled="isDisabled" maxlength="2"></el-input>
      </el-form-item>
      <el-form-item label="批次优先级" prop="lotType">
        <el-input v-model="dataForm.lotType" maxlength="1"></el-input>
      </el-form-item>
      <el-form-item label="批次发起时间" prop="startTime">
        <el-time-picker v-model="dataForm.startTime" format="HH:mm:ss" value-format="HH:mm:ss" :picker-options="{  selectableRange: '00:00:00 - 23:59:59' }">
        </el-time-picker>
      </el-form-item>
      <el-input v-show="false" v-model="dataForm.registerName"></el-input>
      <!--<el-form-item label="批次状态" prop="lotStatus">
        <el-select v-model="dataForm.lotStatus" clearable>
          <el-option v-for="item in this.$param.lotStatus" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>-->
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
      dataForm: {
        id: 0,
        registerCode: '',
        fundCode: '',
        lotCode: '',
        lotType: '',
        startTime: ''
      },
      isDisabled: false,
      registerCodeList: [],
      fundCodeList: [],
      dataRule: {
        registerCode: [
          {required: true, message: '请选择登记机构', trigger: 'blur'}
        ],
        fundCode: [
          {required: true, message: '请选择产品代码', trigger: 'blur'}
        ],
        lotCode: [
          {required: true, message: '请输入产品批次', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              if (/^\d{1,2}$/.test(value) === false) {
                callback(new Error('请输入正确产品批次'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }
        ],
        lotType: [
          {required: true, message: '请输入批次优先级', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              if (/^\d{1}$/.test(value) === false) {
                callback(new Error('请输入正确批次优先级'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }
        ],
        startTime: [
          {required: true, message: '请输入批次发起时间', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    init (item) {
      this.initAgencyAndProcode()
      this.dataForm.id = typeof item !== 'undefined' ? 1 : 0
      this.isDisabled = false
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.dataForm.registerCode = item.registerCode
          this.dataForm.fundCode = item.fundCode
          this.dataForm.lotCode = item.lotCode
          this.dataForm.lotType = item.lotType
          this.dataForm.startTime = item.startTime
          this.dataForm.registerName = item.registerName
          this.isDisabled = true
        }
      })
    },
    // 初始化注册机构和产品代码
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
            url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/fundLot/' + (!this.dataForm.id ? 'insert' : 'update'),
            data: this.$qs.stringify(this.dataForm)
          }).then((res) => {
            if (res.data.result === 1) {
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
                message: res.data.result,
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
    margin-left: -50%
  }
  .el-date-editor{
    width: 50%;
    margin-left: -50%
  }
</style>
