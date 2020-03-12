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
      <el-form-item label="产品批次" prop="lotCode">
        <el-input v-model="dataForm.lotCode" placeholder="产品批次" maxlength="2"></el-input>
      </el-form-item>
      <el-form-item label="批次发起时间" prop="startTime">
        <el-time-picker v-model="dataForm.startTime" placeholder="批次发起时间" format="HH:mm:ss"
                        value-format="HH:mm:ss" :picker-options="{  selectableRange: '00:00:00 - 23:59:59' }"
        ></el-time-picker>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import Config from '@/Config'
  import qs from 'qs'
    export default {
      data () {
        return {
          visible: false,
          dataForm: {
            id: 0,
            registerCode: '',
            fundCode: '',
            lotCode: '',
            startTime: '',
            isDisabled: false
          },
          registerCodeList: [],
          fundCodeList: [],
          dataRule: {
            registerCode: [
              { required: true, message: '登记机构', trigger: 'blur' }
            ],
            fundCode: [
              { required: true, message: '产品代码', trigger: 'blur' }
            ],
            lotCode: [
              { required: true, message: '产品批次', trigger: 'blur' }
            ],
            startTime: [
              { required: true, message: '批次发起时间', trigger: 'blur' }
            ]
          }
        }
      },
      methods: {
        init (id) {
          this.initFundCusttype()
          this.dataForm.id = id || 0
          this.isDisabled = false
          this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            if (this.dataForm.id) {
              this.$ajax({
                method: 'post',
                url: 'http://' + Config.ip + ':' + Config.port + '/fundLot/getOne',
                data: qs.stringify({'registerCode': this.dataForm.id})
              }).then((response) => {
                this.dataForm.registerCode = response.data.registerCode
                this.dataForm.fundCode = response.data.fundCode
                this.dataForm.lotCode = response.data.lotCode
                this.dataForm.startTime = response.data.startTime
                // 登记机构及产品代码不可更改
                this.isDisabled = true
              }).catch((error) => {
                console.log(error)
              })
            }
          })
        },
        // 初始化注册机构代码和产品代码
        initFundCusttype () {
          this.registerCodeList = []
          this.fundCodeList = []
          this.$ajax({
            method: 'get',
            url: 'http://' + Config.ip + ':' + Config.port + '/fundLot/listFundCusttype '
          }).then((response) => {
            this.registerCodeList = response.data
            this.fundCodeList = response.data
          }).catch((error) => {
            console.log(error)
          })
        },
        // 表单提交
        dataFormSubmit () {
          this.$refs['dataForm'].validate((valid) => {
            if (valid) {
              this.$ajax({
                method: 'post',
                url: 'http://' + Config.ip + ':' + Config.port + '/fundLot/' + (!this.dataForm.id ? 'insert' : 'update'),
                data: qs.stringify(this.dataForm)
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
