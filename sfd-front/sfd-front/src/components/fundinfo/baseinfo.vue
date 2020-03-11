<template >
  <div>
    <hr />
    <div class="block">
        <el-form ref="form" :inline="true" :model="form" label-width="80px" size="medium">
            <el-row>
            <el-form-item label="登记机构" prop="registerCode">
                <el-input  :value="form.registerCode" readonly />
            </el-form-item>
            <el-form-item label="产品代码" prop="fundcode">
                <el-input  v-model="form.fundcode"/>
            </el-form-item>
            </el-row>
            <el-row>
            <el-form-item label="产品类型" prop="fundType">
                <el-select v-model="form.fundType" style="width:203px">
                    <el-option
                    v-for="item in fundType"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="查询条件">
                <el-input />
            </el-form-item>
            </el-row>
            <el-row>
            <el-form-item label="认购起日" prop="ipoStartDate">
                <el-date-picker style="width:203px"
                v-model="form.ipoStartDate"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyyMMdd">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="认购止日" prop="ipoEndDate">
                <el-date-picker style="width:203px"
                v-model="form.ipoEndDate"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyyMMdd">
                </el-date-picker>
            </el-form-item>
            </el-row>
            <el-row>
            <el-form-item>
                <el-button type="primary" plain>查询</el-button>
                <el-button @click="resetForm('form')" >重置</el-button>
            </el-form-item>
            </el-row>
    </el-form>
  </div>
  <hr/>
  <div class="block">
    <el-table :data="fundList" border style="width: 100%">
    <el-table-column fixed prop="registerCode" label="登记机构" width="150"></el-table-column>
<el-table-column>
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        <el-button type="text" size="small">编辑</el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>
  </div>
</template>

<script>
import param from '../../common/param'
export default {
  name: 'baseinfo',
  data () {
    return {
      form: {
        registerCode: 'TA',
        fundcode: '',
        fundType: '',
        ipoStartDate: '',
        ipoEndDate: ''

      },
      fundType: param.fundType,
      fundList: [
        {registerCode: '123'}
      ]
    }
  },
  methods: {
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
