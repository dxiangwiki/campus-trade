<template>
  <div class="login-wrapper">
    <el-card class="box">
      <h2 style="text-align:center;margin-bottom:20px">📝 注册账号</h2>
      <el-form :model="form" :rules="rules" ref="form" label-width="70px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="4-20位" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" type="password" placeholder="至少6位" />
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" placeholder="可选" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone" placeholder="可选" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width:100%" @click="handleRegister">注 册</el-button>
        </el-form-item>
        <div style="text-align:center">
          已有账号？
          <el-link type="primary" @click="$router.push('/login')">去登录</el-link>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { register } from '@/api/user'

export default {
  data() {
    return {
      form: { username: '', password: '', nickname: '', phone: '' },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 4, max: 20, message: '长度 4-20 位', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, message: '至少 6 位', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleRegister() {
      this.$refs.form.validate(async valid => {
        if (!valid) return
        const user = await register(this.form)
        localStorage.setItem('user', JSON.stringify(user))
        this.$message.success('注册成功！')
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
.box { width: 420px; }
</style>