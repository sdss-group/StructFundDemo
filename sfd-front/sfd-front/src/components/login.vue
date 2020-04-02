<template>
  <div class="login">
    <h1>{{ title }}</h1>
  <el-row :gutter="20">
  <el-col :span="10"><div class="grid-content bg-purple"></div></el-col>
  <el-col :span="4"><div class="grid-content bg-purple" id="mainContent">
    <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="80px" size="mini">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="loginForm.password" type="password"></el-input>
      </el-form-item>
      <el-form-item label="验证码" >
         <el-input ></el-input>
      </el-form-item>
         <el-image  src="url"  fit="fill"></el-image>
      <br/><br/>
         <el-checkbox label="记住密码" v-model="loginForm.isRemember"></el-checkbox>
      <br/><br/>
        <el-button type="primary" @click="submit()">登录</el-button>
        <el-button @click="resetForm('loginForm')">注册</el-button>

    </el-form>
    </div></el-col>
  <el-col :span="10"><div class="grid-content bg-purple"></div></el-col>
</el-row>
  </div>

</template>

<script>

export default {
  name: 'login',
  data () {
    return {
      title: '账号登录',
      loginForm: {
        username: '',
        password: '',
        isRemember: false
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名!', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    submit () {
      this.$ajax({
        method: 'post',
        url: 'http://' + this.$Config.ip + ':' + this.$Config.port + '/login',
        data: this.$qs.stringify(this.loginForm)
      }).then((response) => {
        console.log(response) // 请求成功返回的数据
        this.$router.push({ path: '/index' })
      }).catch((error) => {
        console.log(error) // 请求失败返回的数据
        this.$message.error(error)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
<style>
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  #mainContent {
    border-style: solid;
    padding: 15%;
    padding-left: 14%
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
