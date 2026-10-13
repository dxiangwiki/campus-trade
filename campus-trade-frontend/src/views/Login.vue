<template>
  <div class="login-wrapper">
    <el-card class="box">
      <h2 style="text-align:center;margin-bottom:20px">🎓 校园二手交易</h2>
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="用户名"
                    prefix-icon="el-icon-user" size="medium" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" placeholder="密码"
                    prefix-icon="el-icon-lock" size="medium"
                    @keyup.enter.native="handleLogin" />
        </el-form-item>
        <el-button type="primary" style="width:100%" size="medium"
                   @click="handleLogin">登 录</el-button>
        <div style="text-align:center;margin-top:12px">
          没有账号？
          <el-link type="primary" @click="$router.push('/register')">去注册</el-link>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { login } from '@/api/user'

export default {
  data() {
    return {
      form: { username: 'admin', password: '123456' },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    handleLogin() {
      this.$refs.form.validate(async valid => {
        if (!valid) return
        const user = await login(this.form)
        localStorage.setItem('user', JSON.stringify(user))
        this.$message.success('登录成功！')
        this.$router.push('/products')
      })
    }
  }
}
</script>

<style scoped>
.login-wrapper {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}
.box { width: 380px; }
</style>